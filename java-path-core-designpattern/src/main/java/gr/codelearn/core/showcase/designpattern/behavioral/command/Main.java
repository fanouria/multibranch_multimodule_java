package gr.codelearn.core.showcase.designpattern.behavioral.command;

import gr.codelearn.core.showcase.designpattern.behavioral.command.command.CarMoveCommand;
import gr.codelearn.core.showcase.designpattern.behavioral.command.command.CarStopCommand;
import gr.codelearn.core.showcase.designpattern.behavioral.command.domain.Car;
import gr.codelearn.core.showcase.designpattern.behavioral.command.service.RemoteControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//client
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//common for both car or any other similar class
		RemoteControl remoteControl = new RemoteControl();
		Car car = new Car();

		//invoking requests to a car
		logger.info("Setting on/off ButtonPressed on RemoteControl for Car");
		CarMoveCommand carMoveCommand = new CarMoveCommand(car);
		remoteControl.onButtonPressed(carMoveCommand);
		CarStopCommand carStopCommand = new CarStopCommand(car);
		remoteControl.offButtonPressed(carStopCommand);
		logger.info("Testing undoButtonPressed() on RemoteControl for Car");
		remoteControl.undoButtonPressed();

	}
}
