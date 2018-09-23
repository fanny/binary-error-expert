package detection;

import java.util.Arrays;

public class ParityVerifier {

	private Parity parity;

	private static final int ZERO = 0;
	private static final int ONE = 1;

	private static final String CORRECT = "correct";
	private static final String INCORRECT = "incorrect";

	public ParityVerifier(Parity parity) {
		this.parity = parity;
	}

	public String verifyData(int[] data) {
		return "According to " + this.parity.toString() + " parity, the given data ("
				+ Arrays.toString(data) + ") is " + this.getParityMessage(data) + "!";
	}

	private int getParityValue(int[] data) {
		int parityValue = ZERO;

		for (int index = 0; index < data.length; index++) {
			parityValue = (parityValue ^ data[index]);
		}

		return parityValue;
	}

	private boolean verifyParity(int[] data) {
		if (this.parity.equals(Parity.ODD)) {
			return (this.getParityValue(data) == ZERO);
		}

		return (this.getParityValue(data) == ONE);
	}

	private String getParityMessage(int[] data) {
		if (this.verifyParity(data)) {
			return CORRECT;
		}

		return INCORRECT;
	}

}
