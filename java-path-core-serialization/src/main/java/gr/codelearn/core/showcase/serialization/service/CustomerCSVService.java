package gr.codelearn.core.showcase.serialization.service;

import gr.codelearn.core.showcase.serialization.domain.Customer;
import gr.codelearn.core.showcase.serialization.domain.Directory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomerCSVService {
	// This service is not generic-oriented for complexity issues

	public void saveToCsv(List<Customer> customers, String fileName) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(Directory.FILE_DIRECTORY.getPath() + fileName));
			 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			// Lambda expression: for each customer, create a record with these 3 fields
			customers.forEach(customer -> {
				try {
					csvPrinter.printRecord(customer.getName(), customer.getGender(), customer.getYearOfBirth());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			csvPrinter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> restoreFromCsv(String fileName) {
		List<Customer> customers = new ArrayList<>();
		try (Reader reader = Files.newBufferedReader(Paths.get(Directory.FILE_DIRECTORY.getPath() + fileName));
			 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
			List<CSVRecord> records = csvParser.getRecords();
			records.forEach(record -> {
				Customer customer = new Customer();
				// We know beforehand the "cell location" of each customer object
				// Bad code practice in case the Customer class changes
				customer.setName(record.get(0));
				customer.setGender(record.get(1));
				customer.setYearOfBirth(Integer.parseInt(record.get(2)));
				customers.add(customer);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return customers;
	}
}
