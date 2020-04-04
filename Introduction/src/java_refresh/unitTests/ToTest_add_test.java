package java_refresh.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToTest_add_test {

	@Test
	void test() {
		ToTest test = new ToTest();
		int a = 2;
		int b = 4;
		int correct_result = a+b;
		int result_from_function = test.add(a, b);
		assertEquals(correct_result, result_from_function);
	}

}
