package gr.codehub.core.showcase.testing;

import gr.codehub.core.showcase.testing.domain.Item;
import gr.codehub.core.showcase.testing.service.CartService;
import gr.codehub.core.showcase.testing.service.CartServiceImpl;
import gr.codehub.core.showcase.testing.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		PaymentService paymentService = null;
		CartService cartService = new CartServiceImpl(paymentService);

		cartService.addItem(new Item("Apple", 1, 1));
		cartService.addItem(new Item("Banana", 2, 5));
		cartService.addItem(new Item("Apple", 1, 2));
		cartService.addItem(new Item("Chocolate", 1, 2));
		
		cartService.getCartItems().forEach(i -> logger.info("{}", i));
		logger.info("The total price of products is:{}", cartService.getTotalPrice());
		cartService.removeItem(new Item("Chocolate", 1, 2));
		cartService.getCartItems().forEach(i -> logger.info("{}", i));
		logger.info("The total price of products is:{}", cartService.getTotalPrice());
	}
}
