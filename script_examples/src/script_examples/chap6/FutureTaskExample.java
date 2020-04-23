package script_examples.chap6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

	public static void main(String[] args) {
		List<FutureTask<Integer>> tasks = new ArrayList();

		FutureTask<Integer> task;

		for (int i = 0; i < 100; i++) {
			task = new FutureTask<Integer>(new CallableExample());
			tasks.add(task);
			new Thread(task).start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int dones = 0, cancel = 0;

		for (int i = 0; i < tasks.size(); i++) {
			task = tasks.get(i);
			if (task.isDone()) {
				dones++;
			} else {
				task.cancel(true);
				cancel++;
			}
		}
		System.out.println("done: " + dones + " canccelled: " + cancel);
	}

}
