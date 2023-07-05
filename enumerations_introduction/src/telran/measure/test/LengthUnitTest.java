package telran.measure.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.measure.Length;
import telran.measure.LengthUnit;

class LengthUnitTest {
	Length len3Km = new Length(3,LengthUnit.KM);
	Length len10Sm = new Length(10,LengthUnit.SM);
	Length len100Mm = new Length(100,LengthUnit.MM);
	Length len5Arshin = new Length(5,LengthUnit.ARSHIN);
	Length len12Foot = new Length(12.5f,LengthUnit.FOOT);

	@Test
	void testToString() {
		assertEquals("3.0KM", len3Km.toString());
		assertEquals("5.0ARSHIN", len5Arshin.toString());
		assertEquals("12.5FOOT", len12Foot.toString());
	}
	
	@Test
	void testEquals() {
		assertEquals(len10Sm, len100Mm);
		assertNotEquals(len12Foot, len5Arshin);
		assertNotEquals(len12Foot, null);
		assertNotEquals(len12Foot, 12);
	}
	
	@Test
	void testCompareTo() {
	assertTrue(len3Km.compareTo(len10Sm) > 0);
	assertTrue(len5Arshin.compareTo(len3Km)<0);
	assertFalse(len12Foot.compareTo(len10Sm) ==0);
	}
	
	@Test
	void testConvert() {
		Length newLen = len5Arshin.convert(LengthUnit.INCH);
		System.out.print(newLen.toString() + "\n");
		Length newLen1 = len3Km.convert(LengthUnit.SM);
		System.out.print(newLen1.toString()+ "\n");
	assertEquals(300000.0, newLen1.getAmount());
	assertEquals(len10Sm.getUnit(), newLen1.getUnit());
	
	}
	
	@Test
	void testBetween() {
		Length l = LengthUnit.SM.between(len12Foot, len5Arshin);
		System.out.print(l.toString()+ "\n");
		assertEquals(l.getUnit(), LengthUnit.SM);
	
		Length ll = LengthUnit.MM.between(len10Sm, len100Mm);
		System.out.print(ll.toString()+ "\n");
		assertEquals(ll.getUnit(), LengthUnit.MM);
		assertEquals(0., ll.getAmount());
	
	}
}
