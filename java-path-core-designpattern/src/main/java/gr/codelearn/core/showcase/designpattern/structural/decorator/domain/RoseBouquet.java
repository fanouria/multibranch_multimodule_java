package gr.codelearn.core.showcase.designpattern.structural.decorator.domain;

//concrete component
public class RoseBouquet extends FlowerBouquet {

	public RoseBouquet() {
		description = "Rose bouquet";
	}

	public double cost() {
		return 12.0;
	}
}
