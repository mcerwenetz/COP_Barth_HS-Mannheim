package a0.t1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloTest {

	@Test
	void testmsg() {
		Hello h = new Hello();
		assertEquals("Hallo Java Welt", h.getMsg());
	}

}
