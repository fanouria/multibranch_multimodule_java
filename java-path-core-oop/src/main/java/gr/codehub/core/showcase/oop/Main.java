package gr.codehub.core.showcase.oop;

import gr.codehub.core.showcase.oop.animal.Animal;
import gr.codehub.core.showcase.oop.animal.Bird;
import gr.codehub.core.showcase.oop.animal.GermanShepherd;
import gr.codehub.core.showcase.oop.animal.Size;
import gr.codehub.core.showcase.oop.bike.domain.ElectricBike;
import gr.codehub.core.showcase.oop.bike.domain.MountainBike;
import gr.codehub.core.showcase.oop.bike.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		//create bird instance
		Bird bird = new Bird("Sparrow", 1, 2);
		//set number of wings
		bird.setNumOfWings(0);
		//get number of wings
		logger.info("NumOfWings:{} ", bird.getNumOfWings());
		bird.fly();
		//call to string method
		logger.info("{} ", bird);

		//create german shepherd instance with non-default constructor
		GermanShepherd theGermanShepherd = new GermanShepherd("Roki", 5, "brown", Size.LARGE);
		logger.info("{} ", theGermanShepherd);

		//create german shepherd instance with default constructor
		GermanShepherd myGermanShepherd = new GermanShepherd();
		myGermanShepherd.bark();
		logger.info("{} ", myGermanShepherd.getSize());
		myGermanShepherd.setSize(Size.LARGE);
		logger.info("{} ", myGermanShepherd.getSize());

		//create an animal array
		Animal[] animals = new Animal[3];

		//add each animal in the array
		animals[0] = bird;
		animals[1] = theGermanShepherd;
		animals[2] = myGermanShepherd;

		//iterate animal array
		for (Animal animal : animals) {
			animal.eat("bone");
			animal.makeSound();
			logger.info("{} ", animal);
		}

		//create electric bike instance
		ElectricBike electricBike = new ElectricBike("ElBike", "Yellow", 2);
		//create mountain bike instance
		MountainBike mountainBike = new MountainBike("MntBike", "Green", 13, 3);
		//create bike service instance
		BikeService bikeService = new BikeService();

		//electric bike operations
		bikeService.increaseTyrePressure(electricBike);
		bikeService.increaseTyrePressure(electricBike);
		bikeService.rotate(electricBike);
		bikeService.cycle(electricBike);
		bikeService.brake(electricBike);
		bikeService.printBikeDetails(electricBike);

		//mountain bike operations
		bikeService.increaseTyrePressure(mountainBike);
		bikeService.gearUp(mountainBike);
		bikeService.gearUp(mountainBike);
		bikeService.cycle(mountainBike);
		bikeService.printBikeDetails(mountainBike);
	}
}
