package correction;

import java.util.Arrays;

import detection.Verifier;
import util.Bit;
import util.Converter;
import util.Response;

public class Hamming implements Verifier, Corrector {

	private int bits;

	public Hamming(int bits) {
		this.bits = bits;
	}

	public String verifyData(int[] data) {
		return "According to Hamming code, the given data " + Converter.convertToString(data) + 
				" is " + this.getVerdict(data) + "!";
	}

	private int getExtraBitValue(int position, int[] data) {
		int bitValue = Bit.ZERO.getValue();

		for (int i = position - 1; i < data.length;) {
			int j = i;
			while (j < i + position && j < data.length) {
				bitValue = (bitValue ^ data[j++]);
			}
			i = j + position;
		}

		return bitValue;
	}

	private int[] getExtraBitsValues(int[] data) {
		int[] k = new int[getExtraBitsQuantity()];
		int parityIndex = 0;

		for (int i = 0; i < data.length; i++) {
			if (isPowerOfTwoIndex(i + 1)) {
				k[parityIndex++] = getExtraBitValue(i + 1, data);
			}
		}

		return k;
	}

	private boolean verifyExtraBits(int[] data) {
		int[] expectedParityBits = new int[getExtraBitsQuantity()];
		int[] parityBits = this.getExtraBitsValues(data);
		Arrays.fill(expectedParityBits, Bit.ZERO.getValue());
		System.out.println(Arrays.toString(parityBits));
		return (Arrays.equals(parityBits, expectedParityBits));
	}

	private String getVerdict(int[] data) {
		if (this.verifyExtraBits(data)) {
			return Response.CORRECT.toString();
		}

		return Response.INCORRECT.toString();
	}

	@Override
	public int[] fixData(int[] data) {
		int index = 0;
		int[] parityBits = getExtraBitsValues(data);

		for (int i = 0; i < parityBits.length; i++) {
			int bit = parityBits[i];
			index += (Math.pow(2, i) * bit);
		}
		
		data[index - 1] = data[index - 1] ^ Bit.ONE.getValue();
		
		return data;
	}
	
	private static boolean isPowerOfTwoIndex(int index) {
		return ((index & (index - 1)) == 0);
	}

	private int getExtraBitsQuantity() {
		int quantity = 0;
		for (int i = 0; i < this.bits; i++) {
			if (isPowerOfTwoIndex(i + 1)) {
				quantity++;
			}
		}

		return quantity;
	}

}
