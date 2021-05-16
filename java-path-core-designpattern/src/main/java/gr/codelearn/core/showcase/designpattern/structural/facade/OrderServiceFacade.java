package gr.codelearn.core.showcase.designpattern.structural.facade;

import gr.codelearn.core.showcase.designpattern.structural.facade.service.InventoryService;
import gr.codelearn.core.showcase.designpattern.structural.facade.service.PaymentService;
import gr.codelearn.core.showcase.designpattern.structural.facade.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//concrete facade
public class OrderServiceFacade {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceFacade.class);

	public boolean placeOrder(int pId) {
		//this method has other various services within it (inventory, payment, shipping)
		boolean orderFulfilled = false;

		Product product = new Product();
		//checks if product is available
		if (InventoryService.isAvailable(product)) {
			logger.info("Product with ID: {} is available.", product.getId());
			//paying service
			boolean paymentConfirmed = PaymentService.makePayment();

			if (paymentConfirmed) {
				logger.info("Payment confirmed...");
				//shipping service
				ShippingService.shipProduct(product);
				logger.info("Product shipped...");
				orderFulfilled = true;
			}

		}
		return orderFulfilled;
	}
}
