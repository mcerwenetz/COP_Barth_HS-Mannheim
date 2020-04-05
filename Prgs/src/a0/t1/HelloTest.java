package a0.t1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class HelloTest {

	@Test
	void testmsg() {
		Hello h = new Hello();
		assertEquals("Hallo Java Welt", h.getMsg());
	}

}
