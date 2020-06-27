package a7.t1;

import akka.actor.AbstractActor;

public class Greeter extends AbstractActor {
	
	public static enum MSG {
		GREET, DONE;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.matchEquals(MSG.GREET, msg -> {
					System.out.println("HelloAkka");
					getSender().tell(MSG.DONE, getSelf());
				})
				.build();
	}

}
