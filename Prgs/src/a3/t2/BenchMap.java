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
		
		for (int i = 0; i < size; i++) {
			intList.add(random.nextInt());
		}
		
		util.Util.resetTime();
		fillMap(intList, hashMap);
		emptyMap(intList, hashMap);
		Long timeHashmap = util.Util.getTimeMilis();
		
		
		util.Util.resetTime();
		fillMap(intList, synchronizedHashMap);
		emptyMap(intList, synchronizedHashMap);
		Long timeSynchronizedMap = util.Util.getTimeMilis();
		
		util.Util.resetTime();
		fillMap(intList, concurrentHashMap);
		emptyMap(intList, concurrentHashMap);
		Long timeConcurrentHashMap = util.Util.getTimeMilis();
		
		
		System.out.println("This took " + timeHashmap + " ms on the HashMap");
		System.out.println("This took " + timeSynchronizedMap + "ms on the synchronized HashMap");
		System.out.println("This took " + timeConcurrentHashMap + "ms on the concurrent HashMap");
		
		
	}

	private static void emptyMap(List<Integer> intList, Map<Integer, Integer> map) {
		for (Integer integer : intList) {
			map.remove(integer);
		}
	}

	private static void fillMap(List<Integer> intList, Map<Integer, Integer> map) {
		Random random = new Random();
		for (Integer integer : intList) {
			map.put(integer, intList.get(random.nextInt(intList.size()-1)));
		}
	}

}
