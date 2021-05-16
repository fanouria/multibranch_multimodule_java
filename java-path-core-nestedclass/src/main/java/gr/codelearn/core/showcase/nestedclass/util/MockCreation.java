package gr.codelearn.core.showcase.nestedclass.util;

import net.andreinc.mockneat.MockNeat;

import java.util.List;

public class MockCreation {

	public static List<String> randomStrings(int size) {
		//create a random string
		return MockNeat.secure().strings().list(size).get();
	}
}
