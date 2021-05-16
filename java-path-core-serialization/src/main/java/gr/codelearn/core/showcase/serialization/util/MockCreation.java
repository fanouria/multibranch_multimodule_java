package gr.codelearn.core.showcase.serialization.util;

import gr.codelearn.core.showcase.serialization.domain.Customer;
import net.andreinc.mockneat.MockNeat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MockCreation {

	public static Customer createRandomCustomer() {
		String randomName = MockNeat.secure().celebrities().rockStars().get();
		String randomGender = MockNeat.secure().genders().letter().get();
		//following method creates a random number between 1900 and 2021
		//nextInt is normally exclusive of the top value,
		//so add 1 to make it inclusive
		int randomYearOfBirth = ThreadLocalRandom.current().nextInt(1900, 2021 + 1);

		Customer customer = new Customer(randomName, randomGender, randomYearOfBirth);
		return customer;
	}

	public static List<Customer> createRandomCustomers(int sampleSize) {
		ArrayList<Customer> customerList = new ArrayList<>();
		for (int i = 0; i < sampleSize; i++) {
			String randomName = MockNeat.secure().celebrities().rockStars().get();
			String randomGender = MockNeat.secure().genders().letter().get();
			//following method creates a random number between 1900 and 2021
			//nextInt is normally exclusive of the top value,
			//so add 1 to make it inclusive
			int randomYearOfBirth = ThreadLocalRandom.current().nextInt(1900, 2021 + 1);

			Customer customer = new Customer(randomName, randomGender, randomYearOfBirth);
			customerList.add(customer);
		}
		return customerList;
	}
}
