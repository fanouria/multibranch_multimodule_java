package gr.codelearn.core.showcase.designpattern.behavioral.observer;

import gr.codelearn.core.showcase.designpattern.behavioral.observer.domain.Bidder;
import gr.codelearn.core.showcase.designpattern.behavioral.observer.domain.Observable;
import gr.codelearn.core.showcase.designpattern.behavioral.observer.domain.Observer;
import gr.codelearn.core.showcase.designpattern.behavioral.observer.domain.Product;

//client/system
public class Main {
	public static void main(String[] args) {

		Observable product = new Product("Fridge", 350);

		Observer bidder1 = new Bidder("Artemis Triss");
		Observer bidder2 = new Bidder("Zeus Hamilton");
		Observer bidder3 = new Bidder("Ana Sabine");

		product.registerObserver(bidder1);
		product.registerObserver(bidder2);
		product.registerObserver(bidder3);
		product.setBidAmount(bidder1, 375);
		product.removeObserver(bidder2);
		product.setBidAmount(bidder3, 400);

	}
}
