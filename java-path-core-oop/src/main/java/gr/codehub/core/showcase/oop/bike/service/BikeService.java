package gr.codehub.core.showcase.oop.bike.service;

import gr.codehub.core.showcase.oop.bike.domain.Bike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BikeService extends AbstractBikeService implements Transmission, WheelRotation {
	private static final Logger logger = LoggerFactory.getLogger(BikeService.class);

	public void increaseTyrePressure(Bike bike) {
		int tyrePressure = bike.getTyrePressure();
		if (tyrePressure < 3) {
			bike.setTyrePressure(tyrePressure + 1);
			logger.info("Tyre pressure increased to {}.", bike.getTyrePressure());
		}
	}

	public void decreaseTyrePressure(Bike bike) {
		int tyrePressure = bike.getTyrePressure();
		if (tyrePressure > 0) {
			bike.setTyrePressure(tyrePressure - 1);
			logger.info("Tyre pressure decreased to {}.", bike.getTyrePressure());
		}
	}

	public void printBikeDetails(Bike bike) {
		logger.info("Bike details: {}", bike);
	}

	@Override
	public void gearUp(Bike bike) {
		int currentGear = bike.getCurrentGear();
		if (currentGear < bike.getNumberOfGears()) {
			logger.info("Changing gear from {} to {}.", currentGear, currentGear + 1);
			bike.setCurrentGear(currentGear + 1);
		}
	}

	@Override
	public void gearDown(Bike bike) {
		int currentGear = bike.getCurrentGear();
		if (currentGear > 0) {
			logger.info("Changing gear from {} to {}.", currentGear, currentGear - 1);
			bike.setCurrentGear(currentGear - 1);
		}
	}

	@Override
	public void chainRingUp(Bike bike) {
		logger.info("Chain ring up.");
	}

	@Override
	public void chainRingDown(Bike bike) {
		logger.info("Chain ring down.");
	}

	@Override
	public void cycle(Bike bike) {
		logger.info("{} cycles and sounds like {}.", bike.getName(), bike.cyclingSound());
	}

	@Override
	public void rotate(Bike bike) {
		logger.info("{} wheels go round and round!", bike.getName());
	}
}
