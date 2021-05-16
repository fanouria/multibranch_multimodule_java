package gr.codelearn.core.showcase.designpattern.behavioral.command.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//receiver
public class Car {
	private static final Logger logger = LoggerFactory.getLogger(Car.class);

	public void move() {
		/* code to start moving*/
		logger.info("Car is moving");
	}

	public void stop() {
		/* code to stop moving*/
		logger.info("Car has stopped");
	}
}
