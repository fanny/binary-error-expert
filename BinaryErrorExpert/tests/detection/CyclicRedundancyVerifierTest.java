package detection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Converter;

class CyclicRedundancyVerifierTest {

	private CyclicRedundancyVerifier crcDegreeTwo;
	private CyclicRedundancyVerifier crcDegreeFour;
	private CyclicRedundancyVerifier crcDegreeFive;
	
	@BeforeEach
	void setUp() {
		crcDegreeTwo = new CyclicRedundancyVerifier(new int[] { 1, 0, 1 });
		crcDegreeFour = new CyclicRedundancyVerifier(new int[] {1, 0, 1, 1});
		crcDegreeFour = new CyclicRedundancyVerifier(new int[] { 0, 1, 1, 0 });
		crcDegreeFive = new CyclicRedundancyVerifier(new int[] { 1, 0, 0, 1, 1 });
	}
	
	
	@Test
	void testIncorrectDataDegreeFour10011101011() {
		int[] data = { 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1 };
		String message = "According to CRC code, the given data " 
						+ Converter.convertToString(data)
						+ " is incorrect!";
		assertEquals(message, crcDegreeFour.verifyData(data));
	}
	
	@Test
	void testCorrectDataDegreeFive110011001() {
		int[] data = {1, 1, 0, 0, 1, 1, 0, 0, 1};
		String message = "According to CRC code, the given data " 
				+ Converter.convertToString(data)
				+ " is correct!";
		
		assertEquals(message, crcDegreeTwo.verifyData(data));
		
	}
	
	@Test
	void testCorrectDataDegreeTwo1010111101() {
		int[] data = { 1, 0, 1, 0, 1, 1, 1, 1, 0, 1 };
		String message = "According to CRC code, the given data " 
						+ Converter.convertToString(data)
						+ " is correct!";
		assertEquals(message, crcDegreeTwo.verifyData(data));
	}
	
	@Test
	void testIncorrectDataDegreeFour110001100110() {
		int[] data = { 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0 };
		String message = "According to CRC code, the given data " + Converter.convertToString(data)
		+ " is incorrect!";
		assertEquals(message, crcDegreeFour.verifyData(data));
	}
	
	@Test
	void testCorrectDataDegreeFive11010110111110() {
		int[] data = { 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0 };
		String message = "According to CRC code, the given data " + Converter.convertToString(data)
		+ " is correct!";
		assertEquals(message, crcDegreeFive.verifyData(data));
	}


}
