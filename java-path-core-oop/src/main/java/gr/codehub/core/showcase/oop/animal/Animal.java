package gr.codehub.core.showcase.oop.animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Animal {
	private static final Logger logger = LoggerFactory.getLogger(Animal.class);

	private String name;
	private int age;

	public Animal() {
	}

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void makeSound() {
		logger.info("Making sound");
	}

	public void sleep() {
		logger.info("Sleeping");
	}

	public void eat() {
		logger.info("Eating");
	}

	public void eat(String food) {
		logger.info("Eating {}.", food);
	}

	@Override
	public String toString() {
		return "Animal{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}
