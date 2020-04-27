package a3.t2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BenchMap {
	
	public static void main(String[] args) {
		Map<Integer, Integer> synchronizedHashMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
		
		Random random=new Random();
		List<Integer> intList = new ArrayList<Integer>();
		int size=1_000_000;
		
		CountDownLatch latch = new CountDownLatch(size);
		ExecutorService threadpool = Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Void> service = new ExecutorCompletionService<>(threadpool);
		
		for (int i = 0; i < size; i++) {
			intList.add(random.nextInt());
		}
		
		
		util.Util.resetTime();
		fillMap(intList, synchronizedHashMap, service, latch);
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emptyMap(intList, synchronizedHashMap);
		Long timeSynchronizedMap = util.Util.getTimeMilis();
		
		util.Util.resetTime();
		fillMap(intList, concurrentHashMap, service, latch);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		emptyMap(intList, concurrentHashMap);
		Long timeConcurrentHashMap = util.Util.getTimeMilis();
		
		
		System.out.println("This took " + timeSynchronizedMap + "ms on the synchronized HashMap");
		System.out.println("This took " + timeConcurrentHashMap + "ms on the concurrent HashMap");
		
	}

	private static void emptyMap(List<Integer> intList, Map<Integer, Integer> map) {
		for (Integer integer : intList) {
			map.remove(integer);
		}
	}

	private static void fillMap(List<Integer> intList, Map<Integer, Integer> map, ExecutorCompletionService<Void> service, CountDownLatch latch) {
		Random random = new Random();
		for (Integer integer : intList) {
			Callable<Void> callable = () -> {
				map.put(integer, intList.get(random.nextInt(intList.size()-1)));
				latch.countDown();
				return null;
			};
			service.submit(callable);
		}
	}

}
