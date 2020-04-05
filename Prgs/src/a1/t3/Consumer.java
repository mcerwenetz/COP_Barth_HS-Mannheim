package a1.t3;


public class Consumer {
	int thread_count;
	Thread[] threads;
	Object lock = new Object();
	int sum=0;
	ConsumeNumbers c = new ConsumeNumbers();
	
	Runnable runnable = () -> {
		synchronized (lock) {
			System.out.println(c.next());
		}
	};
	
	public Consumer() {
		thread_count=1;
		threads = new Thread[thread_count];
		threads[0] = new Thread(runnable);
	}
	
	public Consumer(int thread_count){
		this.thread_count=thread_count;
		threads = new Thread[thread_count];
		
		for (int i = 0; i < thread_count; i++) {
			threads[i] = new Thread(runnable);
		}
		for(Thread thread : threads) {
			thread.start();
		}
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Consumer consumer = new Consumer(2);
		System.out.println(consumer.sum);
		
	}

}
