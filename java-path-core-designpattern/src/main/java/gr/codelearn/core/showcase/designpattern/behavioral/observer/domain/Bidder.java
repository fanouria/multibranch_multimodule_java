package gr.codelearn.core.showcase.designpattern.behavioral.observer.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//concrete observer
public class Bidder implements Observer {
	private static final Logger logger = LoggerFactory.getLogger(Bidder.class);

	String bidderName;

	public Bidder(String bidderName) {
		this.bidderName = bidderName;
	}

	@Override
	public void update(Observer observer, String productName, double bidAmount) {
		logger.info("Hello {}! New bid of amount {} has been placed on {} by {}", bidderName, bidAmount, productName,
				observer);
	}

	//toString is used here instead of "getBiddername" because another class that implements observer may not have such
	//a name, and even having that increases coupling
	@Override
	public String toString() {
		return bidderName;
	}
}
