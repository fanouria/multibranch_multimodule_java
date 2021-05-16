package gr.codelearn.core.showcase.io;

import gr.codelearn.core.showcase.io.domain.Directory;
import gr.codelearn.core.showcase.io.util.MockCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

import static gr.codelearn.core.showcase.io.util.Util.createDataFilesDirectory;
import static gr.codelearn.core.showcase.io.util.Util.efficientlyBuildString;

public class CharacterIO {
	private static final Logger logger = LoggerFactory.getLogger(CharacterIO.class);

	private static void main(String[] args) {
		createDataFilesDirectory();
		List<String> randomCelebritiesList = MockCreation.createRandomCelebritiesList(100);
		writeTextToFile(randomCelebritiesList);
		readTextFromFile();
		writeTextToMultipleFiles(randomCelebritiesList);
		readTextFromFileWithBuffer();
		writeTextToFileWithBuffer(randomCelebritiesList);
		printTextFormattedToNewFile(randomCelebritiesList);
		convertStackTraceToString();
	}

	private static void writeTextToFile(List<String> data) {
		// Writes text to a single file
		try (FileWriter fw = new FileWriter(Directory.FILE_DIRECTORY.getPath() + "character_sample_text.txt")) {

			String dataStr = efficientlyBuildString(data);
			fw.write(dataStr);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTextFromFile() {
		// Reads text from a single file
		logger.info("Reading text from a file:");
		try (FileReader fr = new FileReader(Directory.FILE_DIRECTORY.getPath() + "byte_sample_text.txt")) {

			// using string builder for efficiency
			StringBuilder stringBuilder = new StringBuilder();

			int character;
			while ((character = fr.read()) != -1) {
				stringBuilder.append((char) character);
			}
			// convert string builder to string and then separate each name with the use of the ";" separator
			String[] data = stringBuilder.toString().split(";");

			for (String datum : data) {
				logger.info("{}", datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTextToMultipleFiles(List<String> data) {
		// Writes text to two files with the use of an character array
		try (FileWriter fw1 = new FileWriter(Directory.FILE_DIRECTORY.getPath() + "character_multiple_files1.txt");
			 FileWriter fw2 = new FileWriter(Directory.FILE_DIRECTORY.getPath() + "character_multiple_files2.txt");
			 CharArrayWriter caw = new CharArrayWriter()) {

			String dataStr = efficientlyBuildString(data);
			caw.write(dataStr);
			caw.writeTo(fw1);
			caw.writeTo(fw2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTextFromFileWithBuffer() {
		// Buffer allows Java to do I/O operations with increased performance
		logger.info("Reading text from file with buffer:");
		try (FileReader fr = new FileReader(Directory.FILE_DIRECTORY.getPath() + "character_sample_text.txt");
			 BufferedReader br = new BufferedReader(fr)) {

			String textLine = br.readLine();
			String[] data = textLine.split(";");

			for (String datum : data) {
				logger.info("{}", datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTextToFileWithBuffer(List<String> data) {
		// Buffer allows Java to do I/O operations with increased performance
		try (FileWriter fw = new FileWriter(Directory.FILE_DIRECTORY.getPath() + "character_buffered.txt");
			 BufferedWriter bw = new BufferedWriter(fw)) {

			String dataStr = efficientlyBuildString(data);
			bw.write(dataStr);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printTextFormattedToNewFile(List<String> data) {
		// Writes a set of bytes (characters) in a formatted manner to a new file
		// Extra help from docs: Prints formatted representations of objects to a text-output stream.
		// This class (PrintWriter) implements all of the print methods found in PrintStream. It does not contain methods
		// for writing raw bytes, for which a program should use unencoded byte streams.
		try (FileWriter fw = new FileWriter(Directory.FILE_DIRECTORY.getPath() + "character_print_formatted.txt");
			 PrintWriter pw = new PrintWriter(fw)) {

			for (String datum : data) {
				datum += ";";
				pw.println(datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void convertStackTraceToString() {
		// Constructs a string from a stack trace normally printed in the console string writer require no closing
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			// Trying to cause an error on purpose
			String randomizedString = MockCreation.randomString();
			FileWriter fw_to_fail = new FileWriter(randomizedString + "/" + randomizedString);
		} catch (IOException e) {
			// Print the stack trace to the print writer(it wraps the string writer sw)
			e.printStackTrace(pw);
			// Which can then be used to log into a file etc.
			String stackTrace = sw.toString();
			logger.info("{}", stackTrace);
		} finally {
			pw.close();
		}
	}

	/*
	 ******************************BONUS METHODS******************************
	 */

	private static void copyTextFromFileToFile() {
		// Copies text from one file to another
		try (FileReader fin = new FileReader("character_sample_text.txt");
			 FileWriter fout = new FileWriter("character_sample_text_copied.txt")) {

			// Similar to the byte method:
			int c;
			while ((c = fin.read()) != -1) {
				fout.write(c);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
