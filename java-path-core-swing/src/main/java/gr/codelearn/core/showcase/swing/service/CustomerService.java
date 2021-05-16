package gr.codelearn.core.showcase.swing.service;

import java.util.List;

public class CustomerService {

	// RegEx: If string contains at least one number
	private final static String validationExpression = ".*\\d.*";
	private List<String> customerList;

	public CustomerService(List<String> customerList) {
		this.customerList = customerList;
	}

	public boolean addCustomer(String customer) {
		// Adds one customer to the list, if validation passes
		// If it does not, then method returns false to notify for the error
		if (validateCustomer(customer)) {
			customerList.add(customer);
			return true;
		} else {
			return false;
		}
	}

	private boolean validateCustomer(String customer) {
		// If the customer name contains a number, then the first expression will return true
		// Also needs to not be empty (0 size string)
		return !(customer.matches(validationExpression) || customer.isEmpty());
	}

	public List<String> getCustomerList() {
		// Simple getter
		return customerList;
	}

}
