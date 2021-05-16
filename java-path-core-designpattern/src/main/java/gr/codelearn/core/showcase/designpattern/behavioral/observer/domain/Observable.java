package gr.codelearn.core.showcase.designpattern.behavioral.observer.domain;

//subject
public interface Observable {
	void registerObserver(Observer observer);

	void removeObserver(Observer observer);

	void notifyObservers();

	void setBidAmount(Observer observer, double newBidAmount);
}
