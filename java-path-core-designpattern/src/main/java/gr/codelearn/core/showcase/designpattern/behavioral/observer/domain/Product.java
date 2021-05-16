package gr.codelearn.core.showcase.designpattern.behavioral.observer.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

//concrete subhect/observable
public class Product implements Observable {
	private static final Logger logger = LoggerFactory.getLogger(Product.class);

	private ArrayList<Observer> observers;
	private String productName;
	private double bidAmount;
	private Observer observer;

	public Product(String productName, double bidAmount) {
		this.productName = productName;
		this.bidAmount = bidAmount;
		observers = new ArrayList<>();
	}

	@Override
	public void setBidAmount(Observer observer, double newBidAmount) {
		if (newBidAmount > bidAmount) {
			this.observer = observer;
			this.bidAmount = newBidAmount;
			notifyObservers();
		} else {
			logger.info("New bid amount cannot be less or equal to current bid amount: {}", this.bidAmount);
		}
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		logger.info("----------------- {} has withdrawn from bidding----------------", observer);
	}

	@Override
	public void notifyObservers() {
		logger.info("-----------------New bid placed----------------");
		for (Observer ob : observers) {
			ob.update(this.observer, this.productName, this.bidAmount);
		}
	}
}
