package tests.correction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import correction.Hamming;

class HammingTest {
	
	private Hamming hamming;

	@BeforeEach
	void setUp() throws Exception {
		this.hamming = new Hamming(8);
	}

	@Test
	void testFixTenBit() {
		int[] data = { 0,1,1,1,0,0,1,0,1,1,1,0};
		int[] expectedValue = {0,1,1,1,0,0,1,0,1,0,1,0};
		
		assertArrayEquals(expectedValue, hamming.fixData(data));
		
	}

}
