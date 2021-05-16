package gr.codelearn.core.showcase.exception;

import gr.codelearn.core.showcase.exception.exceptions.NumberBelowZeroException;
import gr.codelearn.core.showcase.exception.exceptions.NumberIsZeroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ExceptionsMechanismTester {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionsMechanismTester.class);

	public static void main(String[] args) {
		//the flow of the program is to enter a number, and the user will be able to see how different methods
		//handle exceptions
		//there are cases where inputing anything but a number will crash the program, so comment out the ones
		//that stop the normal flow of the program (different cases may crash the program, such "input not a number"
		//etc.
		String userInput = readUserInput();
		int result = 0; //-1 exception caught, 0 not yet defined, >0 user input
		logger.info("Input read");
		//the following part throws an exception which stops the program, so it has been commented out
		//logger.info("--------------------------------------------------------");
		//result = unhandledException(userInput);
		//logger.info("Returned: {}", result);
		//logger.info("--------------------------------------------------------");
		result = tryCatch(userInput);
		logger.info("Returned: {}", result);
		logger.info("--------------------------------------------------------");
		result = tryCatchFinally(userInput);
		logger.info("Returned: {}", result);
		logger.info("--------------------------------------------------------");
		result = tryCatchFinallySpecialCase(userInput);
		logger.info("Returned: {}", result);
		logger.info("--------------------------------------------------------");
		try {
			result = throwException(userInput);
		} catch (Exception e) {
			logger.info("Exception caught in main()");
			result = -1;
		}
		logger.info("Returned: {}", result);
		//the following part throws an exception which stops the program, so it has been commented out
		//logger.info("--------------------------------------------------------");
		//try {
		//result = throwCustomException(userInput);
		//} catch (NumberBelowZeroException e) {
		//logger.info("Exception caught in main()");
		//logger.info("Exception message: {}", e.getMessage());
		//result = -1;
		//}
		//logger.info("Returned: {}", result);
		//logger.info("--------------------------------------------------------");
		try {
			result = throwMultipleCustomExceptions(userInput);
		} catch (NumberBelowZeroException | NumberIsZeroException e) {
			logger.info("Exception caught in main()");
			logger.info("Exception message: {}", e.getMessage());
			result = -1;
		}
		logger.info("Returned: {}", result);
		logger.info("--------------------------------------------------------");
		result = tryWithMultipleCatchesMultipleExceptions(userInput);
		logger.info("Returned: {}", result);
		logger.info("--------------------------------------------------------");
		result = tryWithOneCatchMultipleExceptions(userInput);
		logger.info("Returned: {}", result);
		logger.info("--------------------------------------------------------");
	}

	public static String readUserInput() {
		//reads user input
		Scanner myScanner = new Scanner(System.in);  //Create a Scanner object
		logger.info("Enter a number");
		return myScanner.nextLine();  //Read user input
	}

	public static int unhandledException(String userInput) {
		//program will crash if user input is not a number
		logger.info("unhandledException()");
		logger.info("Attempting to parse");
		int i = Integer.parseInt(userInput);
		logger.info("Parse successful, returning {}", i);
		return i;
	}

	public static int tryCatch(String userInput) {
		//handles wrong user input with a generic catch clause
		logger.info("tryCatch()");
		try {
			logger.info("In try");
			int i = Integer.parseInt(userInput);
			logger.info("In try, returning {}", i);
			return i;
		} catch (Exception e) {
			logger.info("In catch, returning -1");
			return -1;
		}
	}

	public static int tryCatchFinally(String userInput) {
		//handles wrong user input with a generic catch clause and with the addition of a finally block
		logger.info("tryCatchFinally()");
		try {
			logger.info("In try");
			int i = Integer.parseInt(userInput);
			logger.info("In try, returning {}", i);
			return i;
		} catch (Exception e) {
			logger.info("In catch, returning -1");
			return -1;
		} finally {
			logger.info("In finally");
		}
	}

	//***OPTIONAL***
	public static int tryCatchFinallySpecialCase(String userInput) {
		//handles wrong user input with a generic catch clause and with the addition of a finally block
		//wrong way to handle errors (return statement in finally block is possible but should not exist)
		logger.info("tryCatchFinallySpecialCase()");
		try {
			logger.info("In try");
			int i = Integer.parseInt(userInput);
			logger.info("In try, returning {}", i);
			return i;
		} catch (Exception e) {
			logger.info("In catch, returning -1");
			return -1;
        /*
        If the finally block completes normally, then the try statement
        completes  abruptly for reason R.
        if the finally block completes abruptly for reason S, then the try
        statement  completes abruptly for reason S (and reason R is
        discarded).
        documentation: https://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.20.2
        */
		} finally {
			logger.info("In finally, returning 999");
			return 999;
		}
	}

	public static int throwException(String userInput) throws Exception {
		//handles wrong user input by throwing the exception to the method caller
		logger.info("throwsException()");
		logger.info("Attempting to parse");
		int i = Integer.parseInt(userInput);
		logger.info("Parse successful, returning {}", i);
		return i;
	}

	public static int throwCustomException(String userInput) throws NumberBelowZeroException {
		//handles wrong user input by throwing a custom exception to the method caller (below 0)
		//custom exceptions are usually business rules/logic
		logger.info("throwsCustomException()");
		logger.info("Attempting to parse");
		int i = Integer.parseInt(userInput);
		if (i < 0) {
			throw new NumberBelowZeroException("Number should not be below 0.");
		}
		logger.info("Parse successful, returning {}", i);
		return i;
	}

	public static int throwMultipleCustomExceptions(String userInput)
			throws NumberIsZeroException, NumberBelowZeroException {
		//handles wrong user input by throwing two custom exceptions to the method caller (below 0 and equal to 0)
		logger.info("throwsMultipleCustomExceptions()");
		logger.info("In try");
		int i = 0;
		//the following try/catch is not required by the compiler, but is done in case the user inputs a letter etc.
		try {
			i = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			//in case this prints, know that the execution of the program flows normally and it doesn't stop here
			e.printStackTrace();
		}
		if (i == 0) {
			throw new NumberIsZeroException("Number should not 0.");
		}
		if (i < 0) {
			throw new NumberBelowZeroException("Number should not be below 0.");
		}
		logger.info("In try, returning {}", i);
		return i;
	}

	public static int tryWithMultipleCatchesMultipleExceptions(String userInput) {
		//handles wrong user input with multiple specific catch clauses
		logger.info("tryWithMultipleCatchesMultipleExceptions()");
		try {
			logger.info("In try");
			int i = Integer.parseInt(userInput);
			if (i == 0) {
				throw new NumberIsZeroException("Number should not 0.");
			}
			if (i < 0) {
				throw new NumberBelowZeroException("Number should not be below 0.");
			}
			logger.info("In try, returning {}", i);
			return i;
		} catch (NumberFormatException e) {
			logger.info("In NumberFormatException catch, returning -1");
			return -1;
		} catch (NumberBelowZeroException e) {
			logger.info("In NumberBelowZeroException catch, returning -1");
			logger.info("Exception message: {}", e.getMessage());
			return -1;
		} catch (NumberIsZeroException e) {
			logger.info("In NumberIsZeroException catch, returning -1");
			logger.info("Exception message: {}", e.getMessage());
			return -1;
		}
	}

	public static int tryWithOneCatchMultipleExceptions(String userInput) {
		//handles wrong user input with one catch clause which contains multiple exceptions (business rules and built-in)
		logger.info("tryWithOneCatchMultipleExceptions()");
		try {
			logger.info("In try");
			int i = Integer.parseInt(userInput);
			if (i == 0) {
				throw new NumberIsZeroException("Number should not 0.");
			}
			if (i < 0) {
				throw new NumberBelowZeroException("Number should not be below 0.");
			}
			logger.info("In try, returning {}", i);
			return i;
		} catch (NumberFormatException | NumberBelowZeroException | NumberIsZeroException e) {
			logger.info("In multiple exception catch, returning -1");
			logger.info("Exception message: {}", e.getMessage());
			return -1;
		}
	}

}
