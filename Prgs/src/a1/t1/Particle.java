package a1.t1;

public class Particle extends AbstractParticle {
	
	private Thread particle_thread = new Thread();
	
	private volatile boolean keepalive = true;
	
	public Particle() {
		cont();
	}
	

	@Override
	public void pause() {
		keepalive=false;
		try {
			particle_thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void cont() {
		keepalive=true;
		particle_thread = new Thread() {
				public void run() {
					while (keepalive) {
						move();
						try {
							Thread.sleep(ParticleApp.MSPERFRAME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
		particle_thread.start();
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
