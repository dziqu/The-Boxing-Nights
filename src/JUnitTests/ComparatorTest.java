package JUnitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Interfaces.Collator;

public class ComparatorTest {

	@Test
	public void porównajStringiTest() { 
		Integer stringiSąRówne = 0;
		Integer otrzymanyRezultat = Collator.collateTo("aab", "aab");
		assertEquals(stringiSąRówne, otrzymanyRezultat);
	}
	
	@Test
	public void porównajLiczyCałkowiteTest() {
		Integer liczbySąRówne = 0;
		Integer otrzymanyRezultat = Collator.collateTo(10, 10);
		assertEquals(liczbySąRówne, otrzymanyRezultat);
	}
}
