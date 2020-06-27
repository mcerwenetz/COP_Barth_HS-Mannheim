package a7.t1;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import util.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class HelloAkkaMain {
	
	public static void main(String[] args) {
		Config akkaConf = ConfigFactory.load();
		ActorSystem system = ActorSystem.create("System", akkaConf);
		ActorRef helloAkka = system.actorOf(Props.create(HelloAkka.class), "HelloAkka");
		Util.sleep(1000);
		system.terminate();
	}

}
