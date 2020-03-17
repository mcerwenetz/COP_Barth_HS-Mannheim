package classes;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.*;
import org.junit.runner.notification.*;

import static org.junit.Assert.*;


public class PointTest {
	private  Point pt;
	@Before
	public void setUp() {
		pt = new Point();
	}
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testNoPoints() {
		int start = Point.getNoPoints();
		pt = new Point();
		pt = new Point();
		pt = new Point();
		int got=Point.getNoPoints()-start;
		assertEquals(3, got);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThrowsExcception() throws Exception {
		try {
			pt.setX(-42);
		} finally {
			//space for cleanup
		}
	}
}
