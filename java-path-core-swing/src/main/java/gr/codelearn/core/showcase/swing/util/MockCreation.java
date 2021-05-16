package gr.codelearn.core.showcase.swing.util;

import net.andreinc.mockneat.MockNeat;

import java.util.ArrayList;
import java.util.List;

public class MockCreation {

	public static List<String> createRandomCustomers(int sampleSize) {
		// Creates a list which contains random customer Strings with rockstar names
		ArrayList<String> customerList = new ArrayList<>();
		for (int i = 0; i < sampleSize; i++) {
			String randomName = MockNeat.secure().celebrities().rockStars().get();
			customerList.add(randomName);
		}
		return customerList;
	}
}
