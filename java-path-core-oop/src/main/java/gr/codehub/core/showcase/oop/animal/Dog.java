package gr.codehub.core.showcase.oop.animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dog extends Animal {
	private static final Logger logger = LoggerFactory.getLogger(Dog.class);

	private String eyeColor;

	public Dog() {
	}

	public Dog(String name, int age, String eyeColor) {
		super(name, age);
		this.eyeColor = eyeColor;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public void bark() {
		logger.info("Woof");
	}

	@Override
	public void makeSound() {
		bark();
	}
}
