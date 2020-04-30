package a3.t3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Competition {

	public static void doTournament() {
		final Integer playerNumber = 200;

		List<Player> playerList = new LinkedList<Player>();
		List<Game> games = new LinkedList<Game>();

		ExecutorService es = Executors.newFixedThreadPool(16);
		ExecutorCompletionService<Void> ecs = new ExecutorCompletionService<Void>(es);

		for (int i = 0; i < playerNumber; i++) {
			playerList.add(new Player(i));
		}

		for (int i = 0; i < playerNumber; i++) {
			games.add(new Game(playerList.get(i), playerList.get(++i)));
		}
		for (int i = 0; i < 199; i++) {
			CountDownLatch latch = new CountDownLatch(games.size());

			for (Game game : games) {
				Callable<Void> runGame = () -> {
					game.startGame();
					System.out.println(game.getWinner());
					latch.countDown();
					return null;
				};
				ecs.submit(runGame);
			}

			try {
				latch.await();
			} catch (InterruptedException e) {
			}
		}
		es.shutdown();

		Player tournamentWinner = new Player(playerNumber + 1);
		for (Player player : playerList) {
			if (player.roundsWon > tournamentWinner.roundsWon) {
				tournamentWinner = player;
			}
		}
		System.out.println(
				"Player " + tournamentWinner + " won the game with " + tournamentWinner.roundsWon + " rounds won.");

	}

}
