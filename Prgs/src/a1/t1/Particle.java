package a1.t1;

public class Particle extends AbstractParticle {
	
	Object lock = new Object();
	
	private Thread particle_thread = new Thread(){
		public void run() {
			while (keepalive) {
				synchronized (lock) {
					while(paused) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				move();
				try {
					Thread.sleep(ParticleApp.MSPERFRAME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
		
	private volatile boolean keepalive = true;
	private volatile boolean paused = false;
	
	public Particle() {
		particle_thread.start();
	}
	

	@Override
	public void pause() {
		paused = true;

	}

	@Override
	public void cont() {
		paused = false;
		synchronized (lock) {
			lock.notify();
		}
	}

	@Override
	public void stop() {
		keepalive=false;
		try {
			particle_thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
