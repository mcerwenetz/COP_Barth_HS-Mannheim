package a3.t3;

public class Game {
	Player player1;
	Player player2;
	Player winner;
	Boolean gameFinished=Boolean.FALSE;
	
	public Game(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}



	public Player getWinner() {
		return winner;
	}


	public void setWinner(Player winner) {
		this.winner = winner;
	}


	public void startGame() {
		gameFinished=false;
		
		while (gameFinished==false) {
			player1.roll();
			player2.roll();
			if (player1.getScore()>player2.getScore()){
				winner=player1;
				gameFinished=true;
			}else if (player2.getScore() > player1.getScore()) {
				winner=player2;
				gameFinished=true;
			}
		}
		winner.incRoundsWon();
	}

}
