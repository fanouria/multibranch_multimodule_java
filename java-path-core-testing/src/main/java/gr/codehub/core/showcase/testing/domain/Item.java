package gr.codehub.core.showcase.testing.domain;

import java.util.Objects;

public class Item {
	private String name;
	private double price;
	private double quantity;

	public Item(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		if (Double.compare(item.price, price) != 0) return false;
		return Objects.equals(name, item.name);
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = name != null ? name.hashCode() : 0;
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Item{" + "name='" + name + '\'' + ", price=" + price + ", quantity=" + quantity + '}';
	}
}
