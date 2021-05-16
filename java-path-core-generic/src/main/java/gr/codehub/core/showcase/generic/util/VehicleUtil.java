package gr.codehub.core.showcase.generic.util;

import gr.codehub.core.showcase.generic.domain.Vehicle;

import java.util.List;

public class VehicleUtil {
	public static double averageTopSpeed(List<? extends Vehicle> vehicles) {
		if (!vehicles.isEmpty()) {
			//@formatter:off
			return vehicles
					.stream()
					.mapToDouble(Vehicle::getTopSpeed)
					.average()
					.getAsDouble();
			//@formatter:on
		} else {
			return 0;
		}
	}
}
