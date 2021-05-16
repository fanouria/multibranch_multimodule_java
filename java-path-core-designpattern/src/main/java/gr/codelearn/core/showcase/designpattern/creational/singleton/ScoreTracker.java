package gr.codelearn.core.showcase.designpattern.creational.singleton;

public class ScoreTracker {

	private static ScoreTracker instance = null;
	private int score;

	public ScoreTracker() {
		//initial score is always 0
		this.score = 0;
	}

	public static ScoreTracker getInstance() {
		//method which can be used universally to return the same ScoreTracker object
		if (instance == null) {
			instance = new ScoreTracker();
		}
		return instance;
	}

	public int increaseScoreByNumber(int number) {
		return score += number;
	}

	public int getScore() {
		return score;
	}
}
