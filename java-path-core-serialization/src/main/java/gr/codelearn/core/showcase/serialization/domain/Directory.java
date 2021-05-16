package gr.codelearn.core.showcase.serialization.domain;

import java.io.File;

public enum Directory {
	//the file directory where all the I/O files will be created
	//translates to: "a folder called 'data_files' in the user's home directory (independently of operating system)"
	FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "data_files" + File.separator);

	String path;

	Directory(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
