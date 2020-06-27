package a7.t1;

import akka.actor.AbstractActor;

public class Greeter extends AbstractActor {
	
	public static enum MSG {
		GREET, DONE;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				//check ob die Message ne Anweisung zum Greeten is
				//Falls ja, wende UnitApply msg an
				.matchEquals(MSG.GREET, msg -> {
					//ausgabe
					System.out.println("HelloAkka");
					//Rückmeldung an den Sender
					getSender().tell(MSG.DONE, getSelf());
				})
				.build();
	}

}
