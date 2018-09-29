package detection;

public class CyclicRedundancyVerifier implements Verifier {
	
	private int generatorPolynomial;
	
	public CyclicRedundancyVerifier(int generatorPolynomial) {
		this.generatorPolynomial = generatorPolynomial;
	}

	@Override
	public String verifyData(int[] data) {
	    return "According to CRC code, the given data "
		    + Converter.convertToString(data) + " is " 
		    + this.getVerdict(data) + "!";
	}

	private String getVerdict(int[] data) {
            if (this.verifyCRC(data)) {
	        return Response.CORRECT.toString();
	    }
	    return Response.INCORRECT.toString();
	}

	
	private boolean verifyCRC(int[] data) {
	    int[] resultPolynomialDivision = this.polynomialDivision(data);
            int[] expectedResultDivision = new int[data.length];
            Arrays.fill(expectedResultDivision, Bit.ZERO.getValue());
	
            return (Arrays.equals(resultPolynomialDivision, expectedResultDivision));
	}
	
	private int[] polynomialDivision(int[] data) {
	    int polynomialDegree = this.generatorPolynomial.length;
	    data = updateCrcBitsToZero(data);
		
	    for(int i = 0; i < data.length; i++){
		int mostSignificativeBit = data[i];
		if(mostSignificativeBit == Bit.ONE.getValue()) {
		    for(int j = 0; j < polynomialDegree; j++){
			data[i+j] ^= this.generatorPolynomial[j];
		    }
		}
	    }

	    return data;
	}

	private int[] updateCrcBitsToZero(int[] data) {
	    int polynomialDegree = this.generatorPolynomial.length
	    int size = data.length;
		
	    for (int i = size - polynomialDegree; i < size; i++){
		data[i] = Bit.ZERO.getValue();
	    }
	
	    return data;
	}	
}
