package gr.codehub.core.showcase.generic.domain;

public class PetrolCar extends Car {
	public PetrolCar(String name, String model, double price, int topSpeed) {
		super(name, model, price, topSpeed);
		super.setEmissionPenalty(1000);
	}
}
