package util;

public enum Bit {
	ZERO(0),
	ONE(1);
	
	private int bit;
	
	Bit(int bit) {
		this.bit = bit;
	}
	
	public int getValue() {
		return this.bit;
	}

}
