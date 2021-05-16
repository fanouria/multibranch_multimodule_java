package gr.codelearn.core.showcase.io.util;

import gr.codelearn.core.showcase.io.domain.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public static String efficientlyBuildString(List<String> data) {
		// Builds a string efficiently, since Strings are immutable and adding a separator allocates unnecessary
		// memory etc.
		StringBuilder stringBuilder = new StringBuilder();
		for (String datum : data) {
			//adding a column between names so that we can separate them if we ever need to read them
			stringBuilder.append(datum);
			stringBuilder.append(";");
		}
		return stringBuilder.toString();
	}

	public static void createDataFilesDirectory() {
		// Creates an empty directory called "data_files" in the user home directory which is used to store all files
		// that are included in this unit (if directory does not already exist)
		try {

			Path userHomeDirectory = Paths.get(Directory.FILE_DIRECTORY.getPath());
			if (!Files.exists(userHomeDirectory)) {
				Files.createDirectory(userHomeDirectory);
			}
			logger.info("Directory created/exists in: {}", userHomeDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
