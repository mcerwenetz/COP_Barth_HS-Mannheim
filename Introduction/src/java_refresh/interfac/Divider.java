package java_refresh.interfac;
import java.util.concurrent.Callable;

public class Divider implements Runnable, Callable<Integer> {
	private int x,y,result;
	
	public Divider(int x, int y) {
		this.x =x; this.y=y;
		this.result=0;
	}
	
	@Override
	public void run() {
		result = x/y;
	}
	
	public int getResult() {
		return result;
	}
	
	@Override
	public Integer call() {
		return x/y;
	}
}
