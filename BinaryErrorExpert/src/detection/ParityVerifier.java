package detection;

import java.util.Arrays;

import util.*;

public class ParityVerifier implements Verifier{

	private Parity parity;

	public ParityVerifier(Parity parity) {
		this.parity = parity;
	}

	public String verifyData(int[] data) {
		return "According to " + this.parity.toString() + " parity, the given data ("
				+ Arrays.toString(data) + ") is " + this.getParityMessage(data) + "!";
	}

	private int getParityValue(int[] data) {
		int parityValue = Bit.ZERO.getValue();

		for (int bit: data) {
			parityValue = (parityValue ^ bit);
		}

		return parityValue;
	}

	@Override
	public boolean verifyParity(int[] data) {
		if (this.parity.equals(Parity.ODD)) {
			return (this.getParityValue(data) == Bit.ZERO.getValue());
		}

		return (this.getParityValue(data) == Bit.ZERO.getValue());
	}

	@Override
	public String getParityMessage(int[] data) {
		return Verifier.super.getParityMessage(data);
	}

}
