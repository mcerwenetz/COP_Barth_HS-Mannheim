package inheritance;

import java.util.Random;

public class Rpoint extends Point{
	private Random rand = new Random();
	
	public Rpoint() {
		//calls constructor of Point class
		super();
	}
	
	@Override
	public void movex(int dx)
	{
		if (dx < -10) {
			dx = -10;
		}
		super.movex(dx);
	}
	
	public void jump() {
		this.x = rand.nextInt(100);
		this.y = rand.nextInt(100);
	}
	
	
}
