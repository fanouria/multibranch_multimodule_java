package gr.codehub.core.showcase.oop.animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bird extends Animal {
	private static final Logger logger = LoggerFactory.getLogger(Bird.class);

	private int numOfWings;

	public Bird() {
	}

	public Bird(String name, int age, int numOfWings) {
		super(name, age);
		this.numOfWings = numOfWings;
	}

	public int getNumOfWings() {
		return numOfWings;
	}

	public void setNumOfWings(int numOfWings) {
		//check for valid number of wings
		if (numOfWings == 2) this.numOfWings = numOfWings;
		else logger.info("I cannot fly with less than two wings!");
	}

	public void fly() {
		logger.info("Fly to the moon!");
	}

}
