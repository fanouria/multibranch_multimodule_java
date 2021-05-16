package gr.codelearn.core.showcase.designpattern.creational.factorymethod.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//concrete product
public class TunaToast extends Toast {
	private static final Logger logger = LoggerFactory.getLogger(TunaToast.class);

	@Override
	public void addIngredients() {
		logger.info("Preparing ingredients for tuna toast");
	}
}
