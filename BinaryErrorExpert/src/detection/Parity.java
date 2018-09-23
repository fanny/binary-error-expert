package detection;

public enum Parity {
	
	ODD("odd"),
	EVEN("even");
	
	private String parity;
	
	Parity(String parity) {
		this.parity = parity;
	}
	
	public String toString() {
		return this.parity;
	}
}
