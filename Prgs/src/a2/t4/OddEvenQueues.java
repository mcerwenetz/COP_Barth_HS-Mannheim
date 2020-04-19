package a2.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import util.Sum;
import util.Util;

public class OddEvenQueues {

	public static void main(String[] args) {
		BlockingQueue<Integer> prodDistQueue = new LinkedBlockingQueue<Integer>();
		BlockingQueue<Integer> oddConsumerQueue = new LinkedBlockingQueue<Integer>();
		BlockingQueue<Integer> evenConsumerQueue = new LinkedBlockingQueue<Integer>();
		AtomicBoolean prodDone = new AtomicBoolean(false);
		AtomicBoolean conDone = new AtomicBoolean(false);
		Sum evensum = new Sum(0);
		Sum oddSum = new Sum(0);

		ProducerThread[] prods = new ProducerThread[10];
		a2.t4.ConsumerThread even = new a2.t4.ConsumerThread(conDone, evenConsumerQueue, evensum);
		a2.t4.ConsumerThread odd = new a2.t4.ConsumerThread(conDone, oddConsumerQueue, oddSum);
		Distributer d = new Distributer(prodDone, prodDistQueue, oddConsumerQueue, evenConsumerQueue);

		for (int i = 0; i < prods.length; i++) {
			prods[i] = new ProducerThread(prodDistQueue, prodDone);
		}

		for (ProducerThread producerThread : prods) {
			producerThread.start();
		}
		d.start();
		even.start();
		odd.start();

		for (ProducerThread producerThread : prods) {
			Util.join(producerThread);
		}
		prodDone.set(true);
		Util.join(d);
		conDone.set(true);
		Util.join(even);
		Util.join(odd);

		System.out.println("Sum of odd: " + odd.sum);
		System.out.println("Sum of even: " + even.sum);

	}

}
