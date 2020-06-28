package a7.t2;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import util.Util;

public class CountPrimesMain {
	public static void main(String[] args) {
		//Standard Config erstellen
		Config akkaConf = ConfigFactory.load();
		//Erstelle ein ActorSystem
		ActorSystem system = ActorSystem.create("System", akkaConf);
		//Erstelle einen Actor helloAkka der dann später den GreeterAkka erstellt
		ActorRef countPrimes = system.actorOf(Props.create(CountPrimes.class), "CountPrimes");
		//Damit am Anfang keine Nachrichten verloren gehen
		Util.sleep(1000);
		//Fahre das System herunter
		system.terminate();
	}
}
