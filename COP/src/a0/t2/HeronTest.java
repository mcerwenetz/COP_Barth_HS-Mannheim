package a0.t2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

class HeronTest {

	@Test
	void x_test_input() {
		ByteArrayInputStream in = new ByteArrayInputStream("123\n456".getBytes());
		System.setIn(in);
		Heron h = new Heron();
		h.get_inputs();
		assertEquals(123, h.x);
		assertEquals(456, h.epsilon);
	}
	
	@Test
	void x_test_noinput() {
		ByteArrayInputStream in = new ByteArrayInputStream("\n\n".getBytes());
		System.setIn(in);
		Heron h = new Heron();
		h.get_inputs();
		assertEquals(10.0, h.x);
		assertEquals(10e-7, h.epsilon);
	}
	
	@Test
	void checkSol() {
		Heron h = new Heron();
		h.calcSol();
		assertEquals(2, h.sol);
	}

}
