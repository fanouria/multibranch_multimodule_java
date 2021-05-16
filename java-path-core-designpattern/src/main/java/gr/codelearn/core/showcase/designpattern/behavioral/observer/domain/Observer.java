package gr.codelearn.core.showcase.designpattern.behavioral.observer.domain;

//observer
public interface Observer {
	void update(Observer observer, String productName, double bidAmount);
}
