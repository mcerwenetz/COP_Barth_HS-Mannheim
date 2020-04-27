package a3.t2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BenchMap {
	
	public static void main(String[] args) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		Map<Integer, Integer> synchronizedHashMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
		
		Random random=new Random();
		List<Integer> intList = new ArrayList<Integer>();
		int size=1_000_000;
		int maxIndex = size-1;

		
		for (int i = 0; i < size; i++) {
			intList.add(random.nextInt());
		}
		
		util.Util.resetTime();
		for (Integer integer : intList) {
			hashMap.put(intList.get(random.nextInt(maxIndex)), intList.get(random.nextInt(maxIndex)));
		}
		
		for (Integer integer : intList) {
			hashMap.remove(intList.get(random.nextInt(maxIndex)));
		}
		Long timeHashmap = util.Util.getTimeMilis();
		System.out.println("This took " + timeHashmap + " on the HashMap");
		
		
		util.Util.resetTime();
		for (Integer integer : intList) {
			synchronizedHashMap.put(intList.get(random.nextInt(maxIndex)), intList.get(random.nextInt(maxIndex)));
		}
		
		for (Integer integer : intList) {
			synchronizedHashMap.remove(intList.get(random.nextInt(maxIndex)));
		}
		Long timeSynchronizedMap = util.Util.getTimeMilis();
		System.out.println("This took " + timeHashmap + " on the synchronized HashMap");
		
		util.Util.resetTime();
		for (Integer integer : intList) {
			concurrentHashMap.put(intList.get(random.nextInt(maxIndex)), intList.get(random.nextInt(maxIndex)));
		}
		
		for (Integer integer : intList) {
			concurrentHashMap.remove(intList.get(random.nextInt(maxIndex)));
		}
		Long timeConcurrentHashMap = util.Util.getTimeMilis();
		System.out.println("This took " + timeHashmap + " on the concurrent HashMap");
		
		
	}

}
