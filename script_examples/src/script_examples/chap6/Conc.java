package script_examples.chap6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import script_examples.Util;

public class Conc {
	public static void main(String[] args) {
		Map<Integer, Integer> shm = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		ConcurrentHashMap<Integer, Integer> chm = new ConcurrentHashMap<Integer, Integer>();
		
		Runnable putIfAbsent = () -> {
			synchronized (shm) {
				if(!shm.containsKey(17)) {
					shm.put(17, 42);
				}
			}
			chm.putIfAbsent(17, 42);
		};
		
		Runnable replace = () -> {
			synchronized (shm) {
				Integer integer = shm.get(17);
				if(integer!= null && integer.equals(Integer.valueOf(42))) {
					shm.put(17, 666);
				}
			}
			chm.replace(17, 666);
		};

		Thread[] put = new Thread[1];
		for (int i = 0; i < put.length; i++) {
			put[i] = new Thread(putIfAbsent);
			put[i].start();
		}
		Util.joinall(put);
		Thread[] replaceThreads = new Thread[1];
		for (int i = 0; i < put.length; i++) {
			replaceThreads[i] = new Thread(replace);
			replaceThreads[i].start();
		}
		Util.joinall(replaceThreads);
	}
}
