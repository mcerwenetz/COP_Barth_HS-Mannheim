package script_examples.chap8;

public class SimulatedCAS {
	private int wert;

	public synchronized int get() {
		return wert;
	}

	public int compareAndSwap(int sollwert, int neuerWert) {
		synchronized (this) {
			// Zwischenspeichern für den return
			// wie bei swap. heißt ja auch so. duh.
			int alter_wert = wert;
			// Compare
			// wenn das der Jedi ist den wir suchen
			if (alter_wert == sollwert) {
				// swap. tausch den wert.
				wert = neuerWert;
			}
			return alter_wert;
		}
	}

	public boolean compareAndSet(int sollwert, int neuerWert) {
		// v hat den alten wert sofern der alte wert der sollwert war
		int v = compareAndSwap(sollwert, neuerWert);
		// Audruck wird true wenn sollwert der alte wert ist.
		return (sollwert == v);
	}

	public static void main(String[] args) {
		//Beispiel tauziehen
		
		SimulatedCAS suCas = new SimulatedCAS();

		Runnable runleft = new Runnable() {

			@Override
			public void run() {
				boolean hatgeklappt = false;
				while (suCas.get() < 100) {
					hatgeklappt = false;
					while (!hatgeklappt) {
						int old = suCas.get();
						System.out.println(old);
						hatgeklappt = suCas.compareAndSet(old, old + 1);
					}
				}
			}
		};

		Runnable runright = new Runnable() {

			@Override
			public void run() {
				boolean hatgeklappt = false;
				while (suCas.get() > -100) {
					hatgeklappt = false;
					while (!hatgeklappt) {
						int old = suCas.get();
						System.out.println(old);
						hatgeklappt = suCas.compareAndSet(old, old - 1);
					}
				}
			}
		};

		new Thread(runleft).start();
		new Thread(runright).start();

	}

}
