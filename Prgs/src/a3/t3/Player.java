package a3.t3;

import java.util.Random;

public class Player {
	Integer id;
	Integer Score;
	Random random = new Random();
	Integer roundsWon=0;
	
	
	public Integer getScore() {
		return Score;
	}


	public void incRoundsWon() {
		roundsWon++;
	}


	public Player(Integer id) {
		super();
		this.id = id;
		Score=0;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	public void roll() {
		Score=random.nextInt(6);
	}

}
