package gr.codelearn.core.showcase.designpattern.structural.facade;

import java.util.concurrent.ThreadLocalRandom;

//secondary class
public class Product {

	private int id;

	public Product() {
		//let's suppose that the id is received from a database or something similar
		//to simulate this, we create a random number
		this.id = ThreadLocalRandom.current().nextInt();
	}

	public int getId() {
		return id;
	}
}
