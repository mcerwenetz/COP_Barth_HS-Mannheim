package a7.t1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class HelloAkka extends AbstractActor {
	
	/*
	 * Vor dem start müssen verschiedene Sachen festgelegt werden
	 */
	@Override
	public void preStart() throws Exception {
		//Props sind die Properties eines Actors
		Props props = Props.create(Greeter.class);
		//Actor Reference um mit ihm quatschen zu können
		ActorRef greeter = context().actorOf(props,"greeter");
		//Sag dem greeter er soll greeten
		greeter.tell(Greeter.MSG.GREET, getSelf());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				//Warte bis der greeter "Done" zurück schickt
		.matchEquals(Greeter.MSG.DONE, msg -> {
			//Gib dann aus dass der Greeter fertig ist
			System.out.println("Greeter is fertig");
			//Beende den HelloAkkaActor
			getContext().stop(getSelf());
		})
		.build();
	}

}
