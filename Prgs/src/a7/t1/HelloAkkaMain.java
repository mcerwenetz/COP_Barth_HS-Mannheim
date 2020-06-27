package a7.t1;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import util.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class HelloAkkaMain {
	
	public static void main(String[] args) {
		//Standard Config erstellen
		Config akkaConf = ConfigFactory.load();
		//Erstelle ein ActorSystem
		ActorSystem system = ActorSystem.create("System", akkaConf);
		//Erstelle einen Actor helloAkka der dann später den GreeterAkka erstellt
		ActorRef helloAkka = system.actorOf(Props.create(HelloAkka.class), "HelloAkka");
		Util.sleep(1000);
		//Fahre das System herunter
		system.terminate();
	}

}
