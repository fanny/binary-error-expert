package tests.correction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import correction.Hamming;
import util.Converter;

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
	
	@Test
	void testFixTwoBit() {
		int[] data = {1,1,1,1,1,0,0,0,1,1,0,0};
		int[] expectedValue = {1,0,1,1,1,0,0,0,1,1,0,0};

		assertArrayEquals(expectedValue, hamming.fixData(data));
	}
	
	@Test
	void testFixSixBit() {
		int[] data = {0,0,0,0,1,0,0,0,1,0,1,0};
		int[] expectedValue = {0,0,0,0,1,0,1,0,1,0,1,0};

		assertArrayEquals(expectedValue, hamming.fixData(data));
		
	}
	
	@Test
	void testCorrectData() {
		int[] data = { 0,1,0,1,0,1,1,0,0,0,1,1};
		
		String message = "According to Hamming code, the given data " + 
				Converter.convertToString(data) + 
				" is correct!";
		assertEquals(message, hamming.verifyData(data));
	}
	
	

}
