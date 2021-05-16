package gr.codelearn.core.showcase.designpattern.behavioral.command.command;

import gr.codelearn.core.showcase.designpattern.behavioral.command.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//concrete command
public class CarStopCommand implements CommandBase {
	private static final Logger logger = LoggerFactory.getLogger(CarStopCommand.class);

	private Car car;

	public CarStopCommand(Car car) {
		this.car = car;
	}

	@Override
	public void execute() {
		logger.info("CarStopCommand.execute(): Invoking stop() on Car");
		car.stop();
	}

	@Override
	public void undo() {
		logger.info("CarStopCommand.undo(): Undoing previous action-> Invoking move() on Car");
		car.move();
	}
}
