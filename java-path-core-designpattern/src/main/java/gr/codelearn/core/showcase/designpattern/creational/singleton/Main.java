package gr.codelearn.core.showcase.designpattern.creational.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		ScoreTracker instance = ScoreTracker.getInstance();
		logger.info("{}", instance.getScore());
		instance = ScoreTracker.getInstance();
		instance.increaseScoreByNumber(1);
		logger.info("{}", instance.getScore());
		instance = ScoreTracker.getInstance();
		logger.info("{}", instance.getScore());
	}
}
