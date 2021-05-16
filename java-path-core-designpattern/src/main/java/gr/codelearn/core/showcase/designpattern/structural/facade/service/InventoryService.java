package gr.codelearn.core.showcase.designpattern.structural.facade.service;

import gr.codelearn.core.showcase.designpattern.structural.facade.Product;

//service/subsystem
public class InventoryService {

	public static boolean isAvailable(Product product) {
		/*Code that checks warehouse database for product availability*/
		return true;
	}

}
