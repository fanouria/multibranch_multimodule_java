package gr.codelearn.core.showcase.io;

import gr.codelearn.core.showcase.io.domain.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

import static gr.codelearn.core.showcase.io.util.Util.createDataFilesDirectory;

public class FileIO {
	private static final Logger logger = LoggerFactory.getLogger(FileIO.class);

	public static void main(String[] args) {
		createDataFilesDirectory();
		listFileAttributesOfDataFilesFolder();
		listFilesAndSubDirectoryOfDataFilesFolder();
		listFilesOfDataFilesFolder();
	}

	private static void listFileAttributesOfDataFilesFolder() {
		// Prints information related to the root folder of the project
		logger.info("Listing file attributes of data files folder:");

		// "." means same folder as the currently running context
		// ".." means the parent folder of the currently running context
		File f = new File(Directory.FILE_DIRECTORY.getPath());

		if (f.exists()) {
			logger.info("Name: {}", f.getName());
			logger.info("Absolute path: {}", f.getAbsolutePath());
			logger.info("Is writable: {}", f.canWrite());
			logger.info("Is readable: {}", f.canRead());
			logger.info("Is File: {}", f.isFile());
			logger.info("Is Directory: {}", f.isDirectory());
			logger.info("Last Modified at: {}", new Date(f.lastModified()));
			logger.info("Length: {} bytes long.", f.length());
		}
	}

	private static void listFilesAndSubDirectoryOfDataFilesFolder() {
		logger.info("Listing files and sub-Directory in the project root:");
		File directory = new File(Directory.FILE_DIRECTORY.getPath());
		String[] list = directory.list();
		for (int i = 0; i < list.length; i++) {
			logger.info("{}: {}", (i + 1), list[i]);
		}
	}

	private static void listFilesOfDataFilesFolder() {
		logger.info("Listing files in the project root:");
		File directory = new File(Directory.FILE_DIRECTORY.getPath());
		String[] list = directory.list();
		for (int i = 0; i < list.length; i++) {
			File f = new File(Directory.FILE_DIRECTORY.getPath(), list[i]);
			if (f.isFile()) {
				logger.info("{}: {}", (i + 1), list[i]);
			}
		}
	}
}
