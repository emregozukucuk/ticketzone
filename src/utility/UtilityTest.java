package utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void testReverseDate() {
		String date = "24.03.1996";
		String reversed = Utility.reverseDate(date, "\\.", "-");		
		assertEquals("1996-03-24", reversed);
	}
}
