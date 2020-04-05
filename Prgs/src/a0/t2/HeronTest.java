package a0.t2;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

class HeronTest {

	static double EPS = 0.000001;
	
	@Test
	void x_test_input() {
		ByteArrayInputStream in = new ByteArrayInputStream("123\n456".getBytes());
		System.setIn(in);
		Heron h = new Heron();
		h.get_inputs();
		assertEquals(123, h.x, EPS);
		assertEquals(456, h.epsilon, EPS);
	}
	
	@Test
	void x_test_noinput() {
		ByteArrayInputStream in = new ByteArrayInputStream("\n\n".getBytes());
		System.setIn(in);
		Heron h = new Heron();
		h.get_inputs();
		assertEquals(10.0, h.x, EPS);
		assertEquals(10e-7, h.epsilon, EPS);
	}
	
	@Test
	void checkSol() {
		Heron h = new Heron();
		h.calcSol();
		double sol = h.getSol();
		double sol_twopoint = Math.round(sol*100.0)/100.0;
		assertEquals(3.16, sol_twopoint, EPS);
	}
	
	@Test
	void checkSolNonStandard() {
		Double x = 4.0;
		Double epsilon = 10e-7;
		String arguments = x.toString() + "\n" + epsilon.toString();
		ByteArrayInputStream in = new ByteArrayInputStream(arguments.getBytes());
		System.setIn(in);
		Heron h = new Heron();
		h.get_inputs();
		h.calcSol();
		double sol = h.getSol();
		double sol_twopoint = Math.round(sol*100.0)/100.0;
		assertEquals(Math.sqrt(x), sol_twopoint, EPS);

	}

}
