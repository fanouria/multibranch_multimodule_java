package gr.codelearn.core.showcase.swing;

import gr.codelearn.core.showcase.swing.gui.ShowAllStringsGUI;
import gr.codelearn.core.showcase.swing.service.CustomerService;
import gr.codelearn.core.showcase.swing.util.MockCreation;

import java.util.List;

public class Main {
	// Messages that will be shown all over the GUI
	// Each class is designed in such a way that any String "category" can be managed using the same GUI
	// In this case, the category is customers
	private static final String[] messages = new String[]{"All Customers", "Add a new customer", "Customer name:", "Customer name cannot contain any numbers or be empty!"};

	public static void main(String[] args) {
		// Generating random customer list
		List<String> randomCustomerList = MockCreation.createRandomCustomers(10);
		// Initialiazing customer service and setting the random list
		CustomerService customerService = new CustomerService(randomCustomerList);
		// Creating the main screen and making it visible
		ShowAllStringsGUI showAllStringsGUI = new ShowAllStringsGUI(customerService, messages);
		showAllStringsGUI.setVisible(true);
	}
}
