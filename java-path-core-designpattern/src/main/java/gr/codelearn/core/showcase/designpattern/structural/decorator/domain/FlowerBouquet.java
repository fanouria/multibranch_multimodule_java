package gr.codelearn.core.showcase.designpattern.structural.decorator.domain;

//component
public abstract class FlowerBouquet {

	String description;

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}
