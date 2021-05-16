package gr.codelearn.core.showcase.designpattern.behavioral.command.service;

import gr.codelearn.core.showcase.designpattern.behavioral.command.command.CommandBase;

//invoker
public class RemoteControl {

	//remote has three variables:
	//*oncommand which is the command when the on button is pressed
	//*offcommand which is the command when the off button is pressed
	//*undocommand when the action of the on/off button should be undone (undo command depends on which button
	//was pressed last
	CommandBase onCommand, offCommand, undoCommand;

	public void onButtonPressed(CommandBase onCommand) {
		this.onCommand = onCommand;
		onCommand.execute();
		undoCommand = onCommand;
	}

	public void offButtonPressed(CommandBase offCommand) {
		this.offCommand = offCommand;
		offCommand.execute();
		undoCommand = offCommand;
	}

	public void undoButtonPressed() {
		undoCommand.undo();
	}

}
