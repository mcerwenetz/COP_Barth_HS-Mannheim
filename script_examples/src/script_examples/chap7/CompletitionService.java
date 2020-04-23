package script_examples.chap7;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import script_examples.Util;

public class CompletitionService {

	public static void main(String[] args) {
		
		//Threadpool
		ExecutorService exs = Executors.newCachedThreadPool();

		//Queue die Threadpools Callables zuteilt
		ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<Integer>(exs);

		final Random r = new Random();

		//erstelle hundert Callables mit fortlaufender id
		for (int i = 0; i < 100; i++) {
			final int id = i;
			Callable<Integer> ci = () -> {
				//jedes Callable schläft zwischen 0 und einer Sekunde lang
				Util.sleep(r.nextInt(1000));
				return id;
			};
			//Callable in completitionService werfen (den rest machen exs und cs aus )
			cs.submit(ci);
		}
		
		Util.resetTime();
		
		//entnehme die IDs der ersten 10 fertigen Callables
		for (int i = 0; i < 10; i++) {
//			Future<Integer> fi;
			try {
				//ein Ergebnis der fertigen entnehmen. Await bis eins da ist.
				cs.take();
//				fi = cs.take();
				// System.out.println(fi.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Zeit für die ersten 10 ausgeben
		System.out.println(Util.getTimeMilis() + " msecs nach 10");
		
		//Entnehme die IDs der restlichen 90 Callables
		for (int i = 0; i < 90; i++) {
			try {
				cs.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Zeit für die restlichen 90 ausgeben
		System.out.println(Util.getTimeMilis() + " msecs für den Rest");
		
		//threadpool runterfahren
		exs.shutdown();
	}
}
