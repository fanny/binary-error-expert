package detection;

public class CyclicRedundancyVerifier implements Verifier {
	
	private int generatorPolynomial;
	
	public CyclicRedundancyVerifier(int generatorPolynomial) {
		this.generatorPolynomial = generatorPolynomial;
	}

	@Override
	public String verifyData(int[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private boolean verifyCRC(int[] data) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
