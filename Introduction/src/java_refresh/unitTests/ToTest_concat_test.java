package java_refresh.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToTest_concat_test {

	@Test
	void test() {
		ToTest test = new ToTest();
		String a = "Hallo";
		String b = "World";
		String correct_result = a+b;
//		String correct_result = "Hallo Welt";
		String resultFromFunction = test.concat(a, b);
		assertEquals(correct_result, resultFromFunction);
	}

}
