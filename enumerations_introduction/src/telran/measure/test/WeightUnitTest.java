package telran.measure.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.measure.WeightUnit;

class WeightUnitTest {

	@Test
	void convertTest() {
		assertEquals(10,WeightUnit.GR.convert(WeightUnit.KG, 10000));
	}

}
