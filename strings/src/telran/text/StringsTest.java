package telran.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringsTest {

	@Test
	void testOr() {
		String regex = "gray|grey|griy";
		assertTrue("gray".matches(regex));
		assertTrue("grey".matches(regex));
		assertTrue("griy".matches(regex));
		assertFalse("groy".matches(regex));
	}
	@Test
	void testGrouping() {
		String regex = "gr(a|e|i)y";
		assertTrue("gray".matches(regex));
		assertTrue("grey".matches(regex));
		assertTrue("griy".matches(regex));
		assertFalse("groy".matches(regex));
	}
	@Test
	void testQuantification() {
		String regex = "a?1234";
		assertFalse("aa1234".matches(regex));
		assertTrue("1234".matches(regex));
		regex = "a*nna";
		assertTrue("aaaanna".matches(regex));
		regex = "a+1234";
		assertFalse("1234".matches(regex));
		regex = "a{2}1234";
		assertFalse("a1234".matches(regex));
		//assertFalse("groy".matches(regex));
	}
	
	@Test
	void javaVariableNameTest() {
		String regex = Strings.javaVariableName();
			
		assertTrue("a".matches(regex));
		assertTrue("AA".matches(regex));
		assertTrue("a_b".matches(regex));
		assertTrue("asdhdhl4676923".matches(regex));
		assertTrue("$".matches(regex));
		assertTrue("$_".matches(regex));
		assertTrue("$2".matches(regex));
		assertTrue("__".matches(regex));
				
		assertFalse("_".matches(regex));
		assertFalse("1a".matches(regex));
		assertFalse(" a301".matches(regex));
		assertFalse("a 2".matches(regex));
		assertFalse("a#".matches(regex));
		assertFalse("a11-11".matches(regex));
		assertFalse(" 3".matches(regex));
	}
	
	@Test
	void zero_300Test() {
		String regex = Strings.zero_300();
			
		assertTrue("0".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("23".matches(regex));
		assertTrue("278".matches(regex));
		assertTrue("300".matches(regex));
				
		assertFalse("00".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("301".matches(regex));
		assertFalse("$2".matches(regex));
		assertFalse("03".matches(regex));
		assertFalse("1111".matches(regex));
		assertFalse(" 3".matches(regex));
	}
	
	@Test
	void ipV4octetTest() {
		String regex = Strings.ipV4octet();
			
		assertTrue("000".matches(regex));
		assertTrue("00".matches(regex));
		assertTrue("0".matches(regex));
		assertTrue("99".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("20".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("249".matches(regex));
		assertTrue("255".matches(regex));
		assertTrue("29".matches(regex));
		assertTrue("18".matches(regex));
		
		assertFalse("0000".matches(regex));
		assertFalse(" 3".matches(regex));
		assertFalse(".3".matches(regex));
		assertFalse("-3".matches(regex));
		assertFalse("256".matches(regex));
		assertFalse("262".matches(regex));
		assertFalse("1000".matches(regex));
	}
	
	@Test
	void ipV4Test() {
		String regex = Strings.ipV4();
			
		assertTrue("0.0.0.0".matches(regex));
		assertTrue("123.99.44.23".matches(regex));
		assertTrue("255.255.0.1".matches(regex));
		
		assertFalse("0000".matches(regex));
		assertFalse(".1.2.3.4".matches(regex));
		assertFalse("1.2.3.4.".matches(regex));
		assertFalse("1.3".matches(regex));
		assertFalse("1.2.3.4.5".matches(regex));
		assertFalse("252.1.2$.2".matches(regex));
		
	}
}
