package gr.codelearn.core.showcase.io;

import gr.codelearn.core.showcase.io.domain.Directory;
import gr.codelearn.core.showcase.io.util.MockCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static gr.codelearn.core.showcase.io.util.Util.createDataFilesDirectory;
import static gr.codelearn.core.showcase.io.util.Util.efficientlyBuildString;

public class ByteIO {
	private static final Logger logger = LoggerFactory.getLogger(ByteIO.class);

	public static void main(String[] args) {
		createDataFilesDirectory();
		List<String> randomCelebrityList = MockCreation.createRandomCelebritiesList(100);
		writeBytesToFile(randomCelebrityList);
		readBytesFromFile();
		writeBytesToMultipleFiles(randomCelebrityList);
		readBytesFromFileWithBuffer();
		writeBytesToFileWithBuffer(randomCelebrityList);
		printBytesFormattedToNewFile(randomCelebrityList);
		writePrimitiveValuesToFileIndependently();
		readPrimitiveValuesFromFileIndependently();
		replaceByteChunksInFile(99);
		copyBytesFromFileToFile();
	}

	private static void writeBytesToFile(List<String> data) {
		// Writes bytes to a single file
		try (FileOutputStream out = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + "byte_sample_text.txt")) {

			String dataStr = efficientlyBuildString(data);
			out.write(dataStr.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readBytesFromFile() {
		// Reads bytes from a single file
		logger.info("Reading text from a file:");
		try (FileInputStream in = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + "byte_sample_text.txt")) {
			String[] data = transformBytesToString(in.readAllBytes());
			for (String datum : data) {
				logger.info("{}", datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeBytesToMultipleFiles(List<String> data) {
		// Writes bytes to two files with the use of an output array
		try (FileOutputStream fout1 = new FileOutputStream(
				Directory.FILE_DIRECTORY.getPath() + "byte_multiple_files1.txt");
			 FileOutputStream fout2 = new FileOutputStream(
					 Directory.FILE_DIRECTORY.getPath() + "byte_multiple_files2.txt");
			 ByteArrayOutputStream bout = new ByteArrayOutputStream()) {

			String dataStr = efficientlyBuildString(data);

			bout.write(dataStr.getBytes());
			bout.writeTo(fout1);
			bout.writeTo(fout2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readBytesFromFileWithBuffer() {
		// Buffer allows Java to do I/O operations on more than one byte at a time, which increases performance
		logger.info("Reading bytes from file with buffer:");
		try (FileInputStream fin = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + "byte_sample_text.txt");
			 BufferedInputStream bin = new BufferedInputStream(fin)) {

			String[] data = transformBytesToString(bin.readAllBytes());
			for (String datum : data) {
				logger.info("{}", datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeBytesToFileWithBuffer(List<String> data) {
		// Buffer allows Java to do I/O operations on more than one byte at a time, which increases performance
		try (FileOutputStream fout = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + "byte_buffered.txt");
			 BufferedOutputStream bout = new BufferedOutputStream(fout)) {

			String dataStr = efficientlyBuildString(data);
			bout.write(dataStr.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printBytesFormattedToNewFile(List<String> data) {
		// Writes a set of bytes (characters) in a formatted manner to a new file
		try (FileOutputStream fout = new FileOutputStream(
				Directory.FILE_DIRECTORY.getPath() + "byte_print_formatted.txt");
			 PrintStream pout = new PrintStream(fout)) {

			for (String datum : data) {
				datum += ";";
				pout.println(datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writePrimitiveValuesToFileIndependently() {
		// Writes primitive Java data types to a file in a machine-independent way
		double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
		int[] units = {12, 8, 13, 29, 50};
		String[] descs = {"Java T-shirt", "Java Mug", "Duke Juggling Dolls", "Java Pin", "Java Key Chain"};

		try (FileOutputStream fout = new FileOutputStream(
				Directory.FILE_DIRECTORY.getPath() + "byte_invoice_data(primitive_values).txt");
			 DataOutputStream dout = new DataOutputStream(fout)) {

			for (int i = 0; i < prices.length; i++) {
				dout.writeDouble(prices[i]);
				dout.writeInt(units[i]);
				dout.writeUTF(descs[i]);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readPrimitiveValuesFromFileIndependently() {
		// Reads primitive Java data types from a file which were written in a machine-independent way
		logger.info("Reading primitive types from a file:");
		try (FileInputStream fin = new FileInputStream(
				Directory.FILE_DIRECTORY.getPath() + "byte_invoice_data(primitive_values).txt");
			 DataInputStream din = new DataInputStream(fin)) {
			double price;
			int unit;
			String desc;
			double total = 0.0;

			while (din.available() > 0) {
				price = din.readDouble();
				unit = din.readInt();
				desc = din.readUTF();
				logger.info("You ordered {} units of {} at ${}", unit, desc, price);
				total += unit * price;
			}
			logger.info("Your total was: {}", total);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String[] transformBytesToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		// Each datum is automatically converted into a small-int (character in reality), so we save all characters
		// to a string builder, so that we can do operations on it
		for (byte datum : data) {
			stringBuilder.append((char) datum);
		}
		// Convert string builder to string and then separate each name with the use of the ";" separator
		return stringBuilder.toString().split(";");
	}

	private static void replaceByteChunksInFile(int maxNumber) {
		// Replace bytes in a file randomly using the RandomAccessFile class in other words create a file that
		// contains a set of sequential numbers and replace a number in the file.
		// RandomAccessFile uses a "pointer" to track how many bytes have been read: each time bytes are read
		// the pointer increases by the number of bytes read

		String filePath = Directory.FILE_DIRECTORY.getPath() + "byte_sequential_numbers(replace_byte_chunks).txt";

		try (FileOutputStream fout = new FileOutputStream(filePath); PrintStream pout = new PrintStream(fout)) {
			// fill in an array from 1 to maxNumber
			int[] sequentialNumbers = IntStream.range(1, maxNumber + 1).toArray();
			for (int sequentialNumber : sequentialNumbers) {
				pout.println(sequentialNumber);
			}

			// Generate a random number between 1 and maxNumber
			int randomNumber = ThreadLocalRandom.current().nextInt(1, maxNumber + 1);
			RandomAccessFile raf = new RandomAccessFile(new File(filePath), "rw");

			// readLine returns null if end of file, so we are "forced" to check for strings
			String numberStr;
			String randomNumberStr = String.valueOf(randomNumber);

			// Replace our number with number of available bytes
			byte[] byteArray;
			while ((numberStr = raf.readLine()) != null) {
				logger.info("number: {}", numberStr);
				logger.info("bytes: {}", numberStr.getBytes().length);
				logger.info("pointer: {}", raf.getFilePointer());
				logger.info("random number: {}", randomNumberStr);
				if (numberStr.equals(randomNumberStr)) {
					if (randomNumber == 1) {
						// If the random number is 1 and we subtract bytes, then Java will throw an exception
						raf.seek(0);
						byteArray = new byte[]{2, 2};
					}
					// Lines are read as characters, therefore some characters may be 1 byte long the new line (\r\n)
					// for the specific file consists of 2 bytes
					else if (randomNumber < 10) {
						raf.seek(raf.getFilePointer() - 3);
						byteArray = new byte[]{2, 2};
					} else {
						// Pointer increases by 4 each time a new line is read for "numbers" with 2 digits therefore,
						// if we want to read the current integer number, we need to subtract 4 bytes
						raf.seek(raf.getFilePointer() - 4);
						byteArray = new byte[]{2, 2, 2};
					}

					raf.write(byteArray);
					// No point in iterating over the rest of the numbers
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 ******************************BONUS METHODS******************************
	 */

	private static void copyBytesFromFileToFile() {
		// Copies bytes from one stream to another essentially, copies a file

		try (InputStream in = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + "byte_sample_text.txt");
			 OutputStream out = new FileOutputStream(
					 Directory.FILE_DIRECTORY.getPath() + "byte_sample_text_copied.txt")) {

			// transferTo is a new method, added in JDK9+
			in.transferTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
