package gr.codelearn.core.showcase.designpattern.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//client/controller
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		OrderServiceFacade facade = new OrderServiceFacade();
		boolean ordered = facade.placeOrder(9);

		if (ordered) {
			logger.info("The product has been ordered.");
		}
	}
}
