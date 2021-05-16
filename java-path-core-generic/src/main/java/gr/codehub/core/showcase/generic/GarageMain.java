package gr.codehub.core.showcase.generic;

import gr.codehub.core.showcase.generic.domain.ElectricCar;
import gr.codehub.core.showcase.generic.domain.PetrolCar;
import gr.codehub.core.showcase.generic.service.CarGarageService;
import gr.codehub.core.showcase.generic.util.VehicleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarageMain {
	private static final Logger logger = LoggerFactory.getLogger(GarageMain.class);

    public static void main(String[] args) {
		CarGarageService<ElectricCar> electricCarGarage = new CarGarageService<>();
		ElectricCar elc1 = new ElectricCar("Tesla", "P9000", 30000, 280);
		ElectricCar elc2 = new ElectricCar("Tesla", "Roadster", 200000, 320);
		electricCarGarage.addVehicle(elc1);
		electricCarGarage.addVehicle(elc2);

		CarGarageService<PetrolCar> petrolCarGarage = new CarGarageService<>();
		PetrolCar pec1 = new PetrolCar("Mercedes", "c63", 250000, 320);
		PetrolCar pec2 =new PetrolCar("Bugatti", "Veyron", 1000000, 450);
		petrolCarGarage.addVehicle(pec1);
		petrolCarGarage.addVehicle(pec2);

		switch (petrolCarGarage.compareTo(electricCarGarage)){
			case 1:
				logger.info("Petrol garage has the highest value with total value {}.",
						petrolCarGarage.getGarageTotalValue());
				break;
			case -1:
				logger.info("Electric garage has the highest value with total value {}.",
						electricCarGarage.getGarageTotalValue());
				break;
			case 0:
				logger.info("Both garages have equal value.");
				break;
			default:
				logger.info("Error case!");
				break;
		}

		logger.info("Are the car prices equal? {}",
				electricCarGarage.isEqualPriceCar(
						electricCarGarage.getVehicle(0), electricCarGarage.getVehicle(1)
				)
		);

		logger.info("The average top speed of electric cars is: {} km/h",
				VehicleUtil.averageTopSpeed(electricCarGarage.getVehicles())
		);
    }
}
