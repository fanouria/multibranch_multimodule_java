package gr.codehub.core.showcase.generic.domain;

public class Vehicle {

	private int topSpeed;
	private String name;
	private String model;

	public Vehicle(int topSpeed, String name, String model) {
		this.topSpeed = topSpeed;
		this.name = name;
		this.model = model;
	}

	public int getTopSpeed() {
		return topSpeed;
	}
 
	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
