package gr.codelearn.core.showcase.designpattern.structural.decorator.domain;

//concrete decorator
public class GlitterDecorator extends FlowerBouquet {

	//object to be decorated
	FlowerBouquet flowerBouquet;

	public GlitterDecorator(FlowerBouquet flowerBouquet) {
		this.flowerBouquet = flowerBouquet;
	}

	public String getDescription() {
		return flowerBouquet.getDescription() + ", paper wrap";
	}

	public double cost() {
		return 3 + flowerBouquet.cost();
	}
}
