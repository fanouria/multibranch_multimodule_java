package gr.codehub.core.showcase.generic.service;

import gr.codehub.core.showcase.generic.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class CarGarageService<T extends Car> implements Comparable<CarGarageService<T>> {

    private double garageTotalValue;
    private List<T> vehicles;
 
	public CarGarageService() {
		vehicles = new ArrayList<>();
		garageTotalValue = 0;
    }

    public void addVehicle(T vehicle){
        setGarageTotalValue(getGarageTotalValue() + vehicle.getPrice());
        vehicles.add(vehicle);
    }

    public T getVehicle(int index){
    	return vehicles.get(index);
	}

	public List<T> getVehicles() {
		return vehicles;
	}

	public void setGarageTotalValue(double garageTotalValue) {
		this.garageTotalValue = garageTotalValue;
	}

	public double getGarageTotalValue() {
        return garageTotalValue;
    }

	public <E extends T> boolean isEqualPriceCar(E c1, E c2){
		return c1.getPrice() == c2.getPrice();
	}

    @Override
    public int compareTo(CarGarageService o) {
        if(this.getGarageTotalValue() > o.getGarageTotalValue())
            return 1;
        else if(this.getGarageTotalValue() < o.getGarageTotalValue())
            return -1;
        else
            return 0;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarGarageService<?> garageService = (CarGarageService<?>) o;

		return Double.compare(garageService.garageTotalValue, garageTotalValue) == 0;
	}

	@Override
	public int hashCode() {
		long temp = Double.doubleToLongBits(garageTotalValue);
		return (int) (temp ^ (temp >>> 32));
	}
}
