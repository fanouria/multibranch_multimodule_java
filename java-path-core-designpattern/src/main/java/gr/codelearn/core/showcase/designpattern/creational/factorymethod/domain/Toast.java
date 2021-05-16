package gr.codelearn.core.showcase.designpattern.creational.factorymethod.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//product
public abstract class Toast {
	private static final Logger logger = LoggerFactory.getLogger(Toast.class);

	//abstract method that differs from toast to toast
	public abstract void addIngredients();

	public void heatToast() {
		//every "toast" has this method
		logger.info("The toast is being heated for 2 minutes.");
	}
}
