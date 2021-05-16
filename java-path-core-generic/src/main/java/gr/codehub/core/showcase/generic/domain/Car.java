package gr.codehub.core.showcase.generic.domain;

public class Car extends Vehicle {

	private double price;
	private double emissionPenalty;

	public Car(String name, String model, double price, int topSpeed) {
		super(topSpeed, name, model);
		this.price = price;
		emissionPenalty = 0;
	}

	public double getPrice() {
		return price + getEmissionPenalty();
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getEmissionPenalty() {
		return emissionPenalty;
	}

	public void setEmissionPenalty(double emissionPenalty) {
		this.emissionPenalty = emissionPenalty;
	}

	@Override
	public String toString() {
		return "Car{" + "name='" + super.getName() + '\'' + ", model='" + super.getModel() + '\'' + ", topSpeed=" +
				super.getTopSpeed() + ", price=" + price + ", emissionPenalty=" + emissionPenalty + '}';
	}
}
