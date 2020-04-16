package a2.t3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConsumerThread extends Thread {
	Lock lock;
	LinkedList<Integer> list;
	Boolean even;
	Condition con;
	Boolean done=false;

	public ConsumerThread(LinkedList<Integer> list, Lock lock, Condition con) {
		this.list=list;
		this.lock=lock;
		this.con=con;
	}


	public Boolean getDone() {
		return done;
	}


	public void setDone(Boolean done) {
		this.done = done;
	}

}
