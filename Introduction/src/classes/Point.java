package classes;

import java.util.Random;

public class Point {
	//noPoints is a class var and not inhertited
	//It is shared by all instances of the class
	//it can by modified. If it was "final" it could not.
	//it exists without an instance of Point
	private static int noPoints=0;
	//protected vars are only usable in a subclass or the instance
	protected int x;
	protected int y;
	
	//getter and setter
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		if (x < 0) {
			throw new IllegalArgumentException("x<0");
		}
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	//Constructor
	public Point() {
		x=0;y=0;
		noPoints++;
	}
	public Point(int x, int y) {
		this.x=x;this.y=y;
		noPoints++;
	}	
	public void movex(int dx) {
		int truedx = dx > 30 ? 30: dx;
		this.x+=truedx;
	}
	//returns private static noPoints
	public static int getNoPoints() {
		return noPoints;
	}
	
	@Override
	public boolean equals(Object o) {
		//If it is not even a point it can not be equal
		if(!(o instanceof Point)) {
			return false;
		}
		//explicit cast
		Point p = (Point) o;
		return x == p.x && y == p.y;
	}
	
	@Override
	public int hashCode() {
		return x^y;
	}
	
	@Override
	public String toString() {
		return "Point("+x+", "+y+")"; 
	}
	//Returns a new random generated point
	public static Point randomPoint() {
		Random rand = new Random();
		int x = rand.nextInt(100);
		int y = rand.nextInt(100);
		return new Point(x,y);
	}
	
	public static void main(String[] args) {
		Point p1 = new Point();
		Point p2 = new Point(17,42);
		p1.movex(17);
		//p3 is reference of p1
		Point p3=p1;
		//also changes x of p1
		p3.movex(25);
		System.out.println(Point.getNoPoints());
		System.out.println(p1 + "\n"+ p2);

	}
}
