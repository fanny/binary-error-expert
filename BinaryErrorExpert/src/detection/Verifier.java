package detection;

import util.Response;

public interface Verifier {

	public boolean verifyParity(int[] data);
	
	public String verifyData(int[] data);
	
	default String getParityMessage(int[] data) {
		if (this.verifyParity(data)) {
			return Response.CORRECT.toString();
		}
		
		return Response.INCORRECT.toString();
	}
	
}
