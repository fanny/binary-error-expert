package detection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import detection.ParityVerifier;
import util.Parity;

public class ParityVerifierTest {
	
	private ParityVerifier oddVerifier;
	private ParityVerifier evenVerifier;
	

	@BeforeEach
	void setUp() {
		oddVerifier = new ParityVerifier(Parity.ODD);
		evenVerifier = new ParityVerifier(Parity.EVEN);
	}
	
	@Test
	void verifyData0() {
		int[] data = { 0 };
		assertEquals("According to odd parity, the given data 0 is incorrect!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 0 is correct!", evenVerifier.verifyData(data));
	}
	
	@Test
	void verifyData1() {
		int[] data = { 1 };
		assertEquals("According to odd parity, the given data 1 is correct!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 1 is incorrect!", evenVerifier.verifyData(data));
	}

	@Test
	void verifyData01() {
		int[] data = { 0, 1 };
		assertEquals("According to odd parity, the given data 01 is correct!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 01 is incorrect!", evenVerifier.verifyData(data));
	}
	
	@Test
	void verifyData101() {
		int[] data = { 1, 0, 1 };
		assertEquals("According to odd parity, the given data 101 is incorrect!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 101 is correct!", evenVerifier.verifyData(data));
	}
	
	@Test
	void verifyData1011() {
		int[] data = { 1, 0, 1, 1 };
		assertEquals("According to odd parity, the given data 1011 is correct!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 1011 is incorrect!", evenVerifier.verifyData(data));
	}
	
	@Test
	void verifyData101011() {
		int[] data = { 1, 0, 1, 0, 1, 1 };
		assertEquals("According to odd parity, the given data 101011 is incorrect!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 101011 is correct!", evenVerifier.verifyData(data));
	}
	
	
	@Test
	void verifyData10110111() {
		int[] data = { 1, 0, 1, 1 , 0, 1, 1 };
		assertEquals("According to odd parity, the given data 1011011 is correct!", oddVerifier.verifyData(data));
		assertEquals("According to even parity, the given data 1011011 is incorrect!", evenVerifier.verifyData(data));
	}

}
