package correction;
import java.util.Arrays;

import detection.Verifier;
import util.Bit;

public class Hamming implements Corrector, Verifier {
	
	private int bits;
	
	public Hamming(int bits) {
		this.bits = bits;
	}
	
	public String verifyData(int[] data) {
		return "The parity, the given data (" + Arrays.toString(data) + ")"
				+ " is " + this.getParityMessage(data) + "!";
	}
	
	@Override
	public String getParityMessage(int[] data) {
		return Verifier.super.getParityMessage(data);
	}
	
	@Override
	public boolean verifyParity(int[] data) {
		int[] expectedParityBits = new int[getQuantityExtraBits()];
		int[] parityBits = this.getParityValues(data);
		Arrays.fill(expectedParityBits, Bit.ZERO.getValue());
		
		return parityBits == expectedParityBits;
	}
	
	private int[] getParityValues(int[] data) {
		int k[] = new int[getQuantityExtraBits()];
		
		for (int i = 0; i < k.length; i++) {
			k[i] = getParityValue(i + 1, data);
		}

		return k;
	}
	
	private int getParityValue(int pos, int[] data) {
		int parityValue = Bit.ZERO.getValue();
		int size = data.length;
		
		for (int i = pos-1; i < size;) {
			int j = i;
			while(j < i + pos && j < size) {
				parityValue = parityValue ^ data[j];
				j++;
			}
			i = j + pos;
		}
		
		return parityValue;
	}

	@Override
	public int[] fixData(int[] data) {
		int index = 0;
		int[] parityBits = getParityValues(data);
		
		for(int i = 0; i < parityBits.length; i++) {
			int bit = parityBits[i];
			index += (Math.pow(2, i+1) * bit);
		}
		data[index-1] = data[index-1] & Bit.ZERO.getValue();
		
		return data;
	}
	
	private int getQuantityExtraBits() {
		int quantity = 0;
		for (int i = 0; i < this.bits; i++) {
			if (isIndexPower2(i+1)) {
				quantity++;
			}
		}
		return quantity;
		
	}
	
	private static boolean isIndexPower2(int index) {
		return (index & (index - 1)) == 0;
	}


}
