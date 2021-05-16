package gr.codelearn.core.showcase.designpattern.behavioral.command.command;

import gr.codelearn.core.showcase.designpattern.behavioral.command.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//concrete command
public class CarMoveCommand implements CommandBase {
	private static final Logger logger = LoggerFactory.getLogger(CarMoveCommand.class);

	private Car car;

	public CarMoveCommand(Car car) {
		this.car = car;
	}

	@Override
	public void execute() {
		logger.info("CarMoveCommand.execute(): Invoking move() on Car");
		car.move();
	}

	@Override
	public void undo() {
		logger.info("CarMoveCommand.undo():  Undoing previous action->Invoking stop() on Car");
		car.stop();
	}

}
