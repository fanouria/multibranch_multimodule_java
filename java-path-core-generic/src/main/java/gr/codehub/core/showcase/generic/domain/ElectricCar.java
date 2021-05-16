package gr.codehub.core.showcase.generic.domain;

public class ElectricCar extends Car {
	public ElectricCar(String name, String model, double price, int topSpeed) {
		super(name, model, price, topSpeed);
		super.setEmissionPenalty(0);
	}
}

