package com.acme.eshop.domain;

import java.math.BigDecimal;

public class Product {
	private String serial;
	private String name;
	private BigDecimal price;

	private Product(Builder builder) {
		serial = builder.serial;
		name = builder.name;
		price = builder.price;
	}

	public static Builder builder(String serial, String name, BigDecimal price) {
		return new Builder(serial, name, price);
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" + "serial='" + serial + '\'' + ", name='" + name + '\'' + ", price=" + price + '}';
	}

	public static final class Builder {
		private final String serial;
		private final String name;
		private final BigDecimal price;

		private Builder(String serial, String name, BigDecimal price) {
			this.serial = serial;
			this.name = name;
			this.price = price;
		}

		public Product build() {
			return new Product(this);
		}
	}
}
