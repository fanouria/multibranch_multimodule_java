package gr.codelearn.core.showcase.exception;

import gr.codelearn.core.showcase.exception.exceptions.NumberBelowZeroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class BuiltInCheckedExceptionTester {
	private static final Logger logger = LoggerFactory.getLogger(BuiltInCheckedExceptionTester.class);

	public static void main(String[] args) {
		// Demonstration of built in checked exceptions
		// Each checked exception
		String filename = "testFile.txt";
		String directory = "data files\\";
		writeToFile(directory, filename);
		readFromFile(directory, filename);
		//load class information example
		loadClassInformation();
		//database connection example
		connectToDatabase();
		//custom checked exception
		parsePositiveIntegerFromString("5");
	}

	public static void writeToFile(String directory, String filename) {
		//simple method to write a message to a file
		//PrintWriter requires a checked exception for "FileNotFound"
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(directory + filename));
			out.write("Hello");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void readFromFile(String directory, String filename) {
		//simple method to read a message from a file
		//FileInputStream requires exception check for "FileNotFound" and readLine requires an exception check
		//for "Input/Output"
		//FYI: streams are not properly closed
		try {
			FileInputStream fileInputStream = new FileInputStream(directory + filename);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader br = new BufferedReader(inputStreamReader);
			logger.info("{}", br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Class<?> loadClassInformation() {
		//loads information of a given class
		//forName requires exception check for "ClassNotFound"
		try {
			return Class.forName("gr.codelearn.core.showcase.exception.domain.ClassA");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void connectToDatabase() {
		//tries to connect to a MySQL database
		//multiple methods such as getConnection require an exception check for "SQLException"
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/codehub", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from students");
			while (rs.next()) {
				logger.info("{} {} {}", rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void parsePositiveIntegerFromString(String numberStr) {
		//checked exceptions can also be exception that us as developers have created
		//here an example from another tester class is used
		try {
			ExceptionsMechanismTester.throwCustomException(numberStr);
		} catch (NumberBelowZeroException e) {
			e.printStackTrace();
		}
	}

}
