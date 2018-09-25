package correction;

import java.util.Arrays;

import detection.Verifier;
import util.Bit;

public class Hamming implements Verifier, Corrector {
	
	private int bits;
	
	public Hamming(int bits) {
		this.bits = bits;
	}
	
	public String verifyData(int[] data) {
		return "According to Hamming code, the given data (" + Arrays.toString(data) + ")"
				+ " is " + this.getParityMessage(data) + "!";
	}
	
	@Override
	public String getParityMessage(int[] data) {
		return Verifier.super.getParityMessage(data);
	}
	
	@Override
	public boolean verifyParity(int[] data) {
		int[] expectedParityBits = new int[getExtraBitsQuantity()];
		int[] parityBits = this.getExtraBitsValues(data);
		Arrays.fill(expectedParityBits, Bit.ZERO.getValue());
		
		return parityBits == expectedParityBits;
	}
	

	private int[] getExtraBitsValues(int[] data) {
		int k[] = new int[getExtraBitsQuantity()];
		int parityIndex = 0;
		
		for (int i = 0; i < data.length; i++) {
			if(isPowerOfTwoIndex(i+1)) {
				k[parityIndex++] = getExtraBitValue(i + 1, data);
			}
		}
		return k;
	}
	
	private int getExtraBitValue(int position, int[] data) {
		int bitValue = Bit.ZERO.getValue();
		
		for (int i = position - 1; i < data.length;) {
			int j = i;
			while(j < i + position && j < data.length) {
				bitValue = (bitValue ^ data[j]);
				j++;
			}
			i = j + position;
		}
		
		return bitValue;
	}

	@Override
	public int[] fixData(int[] data) {
		int index = 0;
		int[] parityBits = getExtraBitsValues(data);
		
		for(int i = 0; i < parityBits.length; i++) {
			int bit = parityBits[i];
			index += (Math.pow(2, i) * bit);
		}
		data[index-1] = data[index-1] & Bit.ZERO.getValue();
		
		return data;
	}
	
	private int getExtraBitsQuantity() {
		int quantity = 0;
		for (int i = 0; i < this.bits; i++) {
			if (isPowerOfTwoIndex(i+1)) {
				quantity++;
			}
		}
	
		return quantity;
	}
	
	private static boolean isPowerOfTwoIndex(int index) {
		return ((index & (index - 1)) == 0);
	}

}
