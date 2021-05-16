package gr.codelearn.core.showcase.io.util;

import net.andreinc.mockneat.MockNeat;

import java.util.List;

public class MockCreation {

	public static String randomString() {
		//create a random string
		return MockNeat.secure().strings().get();
	}

	public static List<String> createRandomCelebritiesList(int sampleSize) {
		//create a specified size of celebrity names
		return MockNeat.secure().celebrities().list(sampleSize).get();
	}

}
