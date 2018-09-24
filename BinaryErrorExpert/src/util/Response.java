package util;

public enum Response {
	
	CORRECT("correct"),
	INCORRECT("incorrect");
	
	private String response;
	
	Response(String response) {
		this.response = response;
	}
	
	public String toString() {
		return this.response;
	}
	
}
