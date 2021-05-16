package gr.codehub.core.showcase.oop.bike.service;

import gr.codehub.core.showcase.oop.bike.domain.Bike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Transmission {
	Logger logger = LoggerFactory.getLogger(Transmission.class);

	void gearUp(Bike bike);

	void gearDown(Bike bike);

	void chainRingUp(Bike bike);

	void chainRingDown(Bike bike);

	default void autoGear() {
		logger.info("Change gear by myself");
	}
}
