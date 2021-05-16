package gr.codelearn.core.showcase.io;

import gr.codelearn.core.showcase.io.domain.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StandardInputOutput {
	private static final Logger logger = LoggerFactory.getLogger(StandardInputOutput.class);

	public static void main(String[] args) {
		readUserInputWithBufferedReader();
		// readUserInputWithScanner();
		// The following method will not run through an IDE, but can be tested by being executed from a jar
		// readUserInputWithConsole();
		// The following method requires the CharacterIO main to have run first for it to properly work:
		readFileWithScanner();
	}

	private static void readUserInputWithBufferedReader() {
		// Reads user input with the use of a buffered reader
		logger.info("Requesting input through buffered reader:");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				logger.info("Enter something (q to exit):");
				String input = br.readLine();
				if ("q".equals(input)) {
					logger.info("Breaking!");
					break;
				}
				logger.info("input: {}", input);
				logger.info("-----------");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readUserInputWithScanner() {
		// Reads user input with the use of a scanner
		logger.info("Requesting input through scanner:");
		try (Scanner scanner = new Scanner(System.in)) {

			while (true) {
				logger.info("Enter something (q to exit):");
				String input = scanner.nextLine();
				if ("q".equals(input)) {
					logger.info("Breaking!");
					break;
				}
				logger.info("input : {}", input);
				logger.info("-----------");
			}

		}
	}

	private static void readUserInputWithConsole() {
		// Reads user input with the use of the console, cannot be run through IntelliJ as console is null (needs to
		// be run from JAR executable)
		// doc: https:// docs.oracle.com/javase/7/docs/api/java/io/Console.html
		logger.info("Requesting input through console:");
		while (true) {
			logger.info("Enter something (q to exit):");
			String input = System.console().readLine();
			if ("q".equals(input)) {
				logger.info("Breaking!");
				break;
			}
			logger.info("input: {}", input);
			logger.info("-----------\n");
		}
	}

	private static void readFileWithScanner() {
		// Reads file with the use of the scanner class
		try (FileReader reader = new FileReader(Directory.FILE_DIRECTORY.getPath() + "character_sample_text.txt");
			 Scanner scanner = new Scanner(reader)) {

			while (scanner.hasNext()) {
				logger.info("{}", scanner.nextLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
