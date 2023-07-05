package telran.text.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import telran.text.*;

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
		assertFalse("252^2^2#4".matches(regex));
		
	}
	@Test
	void ipV4_fTest() {
		String regex = Strings.ipV4_f();
			
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
	
	@Test
	void arithmTest() {
		String regex = Strings.arithm();
		
		assertTrue("100-34-21".matches(regex));
		assertTrue("100+34".matches(regex));
		assertTrue("100/20".matches(regex));
		assertTrue("100-34+21".matches(regex));
		assertTrue("100/34*21+23-14".matches(regex));
		assertTrue("-100*34+21".matches(regex));
		assertTrue("100 - 34".matches(regex));
		
		assertFalse("-3".matches(regex));
		assertFalse("1233".matches(regex));
		assertFalse("2 - - -3".matches(regex));
		
	}	
	
	@Test
	void arithmExpressionTest() {

		assertTrue(Strings.isArithmeticExpression("100-34- 21"));
		assertTrue(Strings.isArithmeticExpression("100+34"));
		assertTrue(Strings.isArithmeticExpression("100/20"));
		assertTrue(Strings.isArithmeticExpression("100-34+21"));
		assertTrue(Strings.isArithmeticExpression("100/34*21+23-14"));
		assertTrue(Strings.isArithmeticExpression("-100*34+21"));
		assertTrue(Strings.isArithmeticExpression("100 - 34"));
		assertTrue(Strings.isArithmeticExpression("-3"));
		assertTrue(Strings.isArithmeticExpression("1233"));
		assertTrue(Strings.isArithmeticExpression("a + b"));
		assertTrue(Strings.isArithmeticExpression("aa - bb * cc"));
		assertFalse(Strings.isArithmeticExpression("2 - - -3"));
		assertFalse(Strings.isArithmeticExpression("100/34*21+2^^3-14"));
		assertFalse(Strings.isArithmeticExpression("12=33"));
		assertFalse(Strings.isArithmeticExpression("12 33"));
		assertFalse(Strings.isArithmeticExpression("ass ff"));
		
	}	
	
	@Test
	void computeExpressionTest() {
		assertEquals(12, Strings.computeExpression(" 12 "));
		assertEquals(2, Strings.computeExpression(" 12/ 6"));
		assertEquals(6, Strings.computeExpression("12/2"));
		assertEquals(1008, Strings.computeExpression(" 12*  2 / 3 + 1000 "));
		assertEquals(54, Strings.computeExpression(" 120 / 60 + 100 / 2 + 3  "));
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" 12/ 18 + 100 10"));
	}
	
	@Test
	void computeExpressionVariableTest() {
		HashMap<String, Double> mapOperands;
		mapOperands = new HashMap<>();
		mapOperands.put("a", 100.4);
		mapOperands.put("b", 22.5);
		mapOperands.put("c", 3.);
		mapOperands.put("d", 18.9);
		
		
		
		assertEquals(112.4, Strings.computeExpression(" 12 + a", mapOperands ));
		assertEquals(11.25, Strings.computeExpression("b/2", mapOperands ), 0.01);
		assertEquals(90.5, Strings.computeExpression(" 12*  c / 4 + a -d ", mapOperands ), 0.1);
		assertEquals(47.26666666666667, Strings.computeExpression("a+b+d/c", mapOperands ));
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" 12/ a b + 100 10", mapOperands ));
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" 100 - a10", mapOperands ));
	}
}
