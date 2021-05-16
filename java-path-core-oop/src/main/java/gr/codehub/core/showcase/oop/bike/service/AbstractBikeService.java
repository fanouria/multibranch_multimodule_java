package gr.codehub.core.showcase.oop.bike.service;

import gr.codehub.core.showcase.oop.bike.domain.Bike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBikeService {
	private static final Logger logger = LoggerFactory.getLogger(AbstractBikeService.class);

	public abstract void cycle(Bike bike);
	public final void brake(Bike bike) {
		logger.info("{} stopping!", bike.getName());
	}
}
