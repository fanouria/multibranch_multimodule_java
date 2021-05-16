package gr.codelearn.core.showcase.serialization;

import gr.codelearn.core.showcase.serialization.domain.Customer;
import gr.codelearn.core.showcase.serialization.service.ByteService;
import gr.codelearn.core.showcase.serialization.service.CustomerCSVService;
import gr.codelearn.core.showcase.serialization.service.JSONService;
import gr.codelearn.core.showcase.serialization.service.XMLService;
import gr.codelearn.core.showcase.serialization.util.MockCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//saving a single serialized object to text
		Customer randomCustomer = MockCreation.createRandomCustomer();
		ByteService<Customer> byteService = new ByteService<>();
		byteService.writeObjectToFile(randomCustomer, "customer.txt");
		Customer retrievedCustomer = byteService.readObjectFromFile("customer.txt");
		logger.info("Retrieved customer: {}", retrievedCustomer);

		List<Customer> randomCustomers = MockCreation.createRandomCustomers(50);

		// Saving multiple objects
		byteService.writeMultipleObjectsToFile(randomCustomers, "customers.txt");
		List<Customer> restoredCustomersText = byteService.readMultipleObjectsFromFile("customers.txt");
		for (Customer restoredCustomer : restoredCustomersText) {
			logger.info("(Text) Restored customer: {}", restoredCustomer);
		}

		// Xml example
		XMLService<Customer> xmlService = new XMLService<>();
		xmlService.serializeToXML(randomCustomers, "customers.xml");
		List<Customer> restoredCustomersXML = xmlService.deserializeFromXML("customers.xml");
		for (Customer restoredCustomer : restoredCustomersXML) {
			logger.info("(XML) Restored customer: {}", restoredCustomer);
		}

		// Json example
		JSONService<Customer> jsonService = new JSONService<>();
		jsonService.serializeToJSON(randomCustomers, "customers.json");
		List<Customer> restoredCustomersJSON = jsonService.deserializeFromJSON(Customer[].class, "customers.json");
		for (Customer restoredCustomer : restoredCustomersJSON) {
			logger.info("(JSON) Restored customer: {}", restoredCustomer);
		}

		// Csv example
		// Note: CustomerCSVService class created is not generic-oriented like the rest
		CustomerCSVService customerCsvService = new CustomerCSVService();
		customerCsvService.saveToCsv(randomCustomers, "customers.csv");
		List<Customer> restoredCustomersCsv = customerCsvService.restoreFromCsv("customers.csv");
		for (Customer restoredCustomer : restoredCustomersCsv) {
			logger.info("(CSV) Restored customer: {}", restoredCustomer);
		}
	}

}
