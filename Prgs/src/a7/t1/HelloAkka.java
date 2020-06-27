package a7.t1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class HelloAkka extends AbstractActor {
	
	@Override
	public void preStart() throws Exception {
		Props props = Props.create(Greeter.class);
		ActorRef greeter = context().actorOf(props,"greeter");
		greeter.tell(Greeter.MSG.GREET, getSelf());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
		.matchEquals(Greeter.MSG.DONE, msg -> {
			System.out.println("Greeter is fertig");
			getContext().stop(getSelf());
		})
		.build();
	}

}
