package gr.codelearn.core.showcase.io;

import gr.codelearn.core.showcase.io.domain.Directory;
import gr.codelearn.core.showcase.io.util.MockCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static gr.codelearn.core.showcase.io.util.Util.createDataFilesDirectory;
import static gr.codelearn.core.showcase.io.util.Util.efficientlyBuildString;

public class NIO {
	private static final Logger logger = LoggerFactory.getLogger(NIO.class);

	public static void main(String[] args) throws IOException {
		createDataFilesDirectory();
		List<String> randomCelebrityList = MockCreation.createRandomCelebritiesList(100);
		writeTextToFile(randomCelebrityList);
		readLinesFromFile();
		writeTextToMultipleFiles(randomCelebrityList);
		createDirectoryAndFile();
		deleteDirectoryAndFiles();
		appendOnTopOfAllTXTFiles();
	}

	private static void writeTextToFile(List<String> data) {
		// Wwrites to a file in an efficient manner  using the nio buffered writer
		try (BufferedWriter fw = Files
				.newBufferedWriter(Paths.get(Directory.FILE_DIRECTORY.getPath() + "nio_sample_text.txt"))) {

			String dataStr = efficientlyBuildString(data);
			fw.write(dataStr);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readLinesFromFile() {
		// Reads lines from a file in an efficient manner using the nio buffered reader
		logger.info("Reading lines from a file:");
		try (BufferedReader br = Files
				.newBufferedReader(Paths.get(Directory.FILE_DIRECTORY.getPath() + "nio_sample_text.txt"))) {

			String line = br.readLine();
			String[] data = line.split(";");
			for (String datum : data) {
				logger.info("{}", datum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTextToMultipleFiles(List<String> data) {
		// Writes text to two files
		ArrayList<Path> files = new ArrayList<>();
		Path file1 = Paths.get(Directory.FILE_DIRECTORY.getPath() + "nio_multiples_files1.txt");
		Path file2 = Paths.get(Directory.FILE_DIRECTORY.getPath() + "nio_multiples_files2.txt");
		files.add(file1);
		files.add(file2);

		String dataStr = efficientlyBuildString(data);

		for (Path file : files) {
			try {
				Files.write(file, dataStr.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void createDirectoryAndFile() {
		// Creates a directory in the data files folder called "nio_directory" and adds an empty "nio_test" text file
		try {
			Path directory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "nio_directory");
			Files.createDirectory(directory);
			Path file = Paths.get(directory + "/nio_test.txt");
			Files.createFile(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void deleteDirectoryAndFiles() {
		// Deletes a directory in the data files called "nio_directory" and recursively any first-level files that it
		// includes
		Path directory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "nio_directory");
		try {
			String[] fileList = directory.toFile().list();
			for (String file : fileList) {
				Path filePath = Paths.get(directory + File.separator + file);
				Files.delete(filePath);
			}
			Files.delete(directory);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void appendOnTopOfAllTXTFiles() {
		// Appends a new line to all text files (*.txt) in a directory

		// Defines the directory that contains the text files
		String dir = Directory.FILE_DIRECTORY.getPath();
		Path dirPath = Paths.get(dir);

		try {
			// Go through all files in the directory (tested with .txt files only)
			Files.list(dirPath)
					// Filter only files
					.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".txt")).forEach(filePath -> {
				// we need a channel that tracks byte position so that we can add a line before the whole file.
				// The way to do that is by essentially "recreating" it (copy file contents->add first line->add
				// file contents)
				// If we don't use this method, appending bytes on top replaces the existing ones
				try (SeekableByteChannel sbc = Files
						.newByteChannel(filePath, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
					String firstLine = "The name of the file is: " + filePath.getFileName() + ";";
					// Saves (backups) the contents of the existing file to memory (not efficient for large files)
					byte[] fileContents = Files.readAllBytes(filePath);
					// Adds to the buffer the first line (in byte form) that we want to add
					ByteBuffer bfWrite = ByteBuffer.wrap(firstLine.getBytes());
					// Commits the first line to the file
					sbc.write(bfWrite);
					// Adds to the buffer the contents of the file (sbc knows the current position within the file)
					bfWrite = ByteBuffer.wrap(fileContents);
					sbc.write(bfWrite);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 ******************************BONUS METHODS******************************
	 */

	private static void readAllLinesFromFile() {
		// Reads all lines from a file and adds them to a list (compared to readLinesFromFile())
		try {
			List<String> lines = Files
					.readAllLines(Paths.get((Directory.FILE_DIRECTORY.getPath() + "nio_sample_text.txt")));
			for (String line : lines) {
				logger.info("{}", line);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
