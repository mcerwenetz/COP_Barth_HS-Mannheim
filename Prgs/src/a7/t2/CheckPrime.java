package a7.t2;

import util.*;

import akka.actor.AbstractActor;
import akka.actor.Props;

//CheckPrime Actor packt Nachrichten vom PrimeCounter aus und checkt ob's ne Prime is
public class CheckPrime extends AbstractActor {
	
	//Best Practice static Props methode damit man's in der Mutter Klasse nicht machen muss
	public static Props props() {
		return Props.create(CheckPrime.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				//Wenn ne Anfrage kommt
				.match(MsgIsPrime.class, msg -> {
					//Hol dir die Value aus der Anfrage
					long value = msg.getValue();
					//checke mit der Util Klasse ob die Value Prime ist
					boolean prime = Util.isPrime(value);
					//Mach die Antwort fertig
					MsgIsPrimeResult result = new MsgIsPrimeResult(value, prime);
					//Schick die Antwort
					getSender().tell(result, getSelf());
				})
				.build();
	}

}
