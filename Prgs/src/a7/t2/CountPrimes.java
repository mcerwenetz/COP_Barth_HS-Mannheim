package a7.t2;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import akka.routing.RouterConfig;

//Start und Stop Messages
enum Msg {
	START, STOP
};

public class CountPrimes extends AbstractActor {

	//Checkprimes checkt ob eine Zahl eine Prime ist
	ActorRef checkPrime;
	//Outstanding sind die noch zu prüfenden primes
	int outstanding = 0;
	//count sind die anzahl an primes
	int count = 0;
	//time zur performance messong
	long time;

	//range der zu prüfenden zahlen
	int from = 1_000_000, to = 1_001_000;

	@Override
	public void preStart() throws Exception {
		//props vom Checkprime Actor
		Props props = CheckPrime.props();
		//Context des Countprime actors
		ActorContext context = getContext();
		//Round Robin Pool der größe 4 erstellen
		RouterConfig router = new RoundRobinPool(4);
		//Routerprops für den Start der Kinder
		Props rprops = props.withRouter(router);
		//checkprime child erstellen
		checkPrime = context.actorOf(rprops, "checker");
		//kick-off message zum starten an den CountPrime Actor
		getSelf().tell(Msg.START, getSelf());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				//starte
				.matchEquals(Msg.START, msg -> {
					//messe anfangszeit
					time= System.nanoTime();
					//Erniedrige noch zu erledigende Requests um 1
					outstanding = to -from +1;
					//Frag checkprime für jede Zahl
					for (int i = from; i <= to; i++) {
						//Frag ihn mit ner MsgIsPrime
						MsgIsPrime isPrime = new MsgIsPrime(i);
						checkPrime.tell(isPrime, getSelf());
					}
				})
				//stoppe
				.matchEquals(Msg.STOP, msg -> {
					//Ausgabe
					System.out.println(count + " primes counted");
					//Laufzeit
					time = (System.nanoTime() - time ) / 1_000_000;
					System.out.println("took " + time+ " msces");
					//PrimeCounter Actor shutdown
					getContext().stop(getSelf());
				})
				//Analyse der Antwort vom CheckPrime Actor
				.match(MsgIsPrimeResult.class, result -> {
					//Wenn in der Nachricht steh dass es ne Prime ist:
					if(result.isPrime()) {
						//erhöhe die Anzahl der gezählten Primes
						count +=1;
					}
					//Verringere outstanding um 1
					outstanding-=1;
					//Falls keine mehr übrig sind mach die Ausgabe
					if(outstanding == 0) {
						getSelf().tell(Msg.STOP, getSelf());
					}
				})
				.build();
	}
}
