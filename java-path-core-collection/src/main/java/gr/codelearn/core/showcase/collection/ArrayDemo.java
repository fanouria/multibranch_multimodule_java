package gr.codelearn.core.showcase.collection;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayDemo {
	private static final Logger logger = LoggerFactory.getLogger(ArrayDemo.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		// Integer array actions
		integerArrayActions();
		// String array actions
		stringArrayActions();
		// Some other array related actions
		otherArrayActions();
	}

	private static void integerArrayActions() {
		logger.info("### INTEGER ARRAY ACTIONS ###");

		// Declaring array literal the old way
		int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		// Declaring array literal the new way
		int[] intArrayTheNewWay = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		// Using classic looping
		for (int i = 0; i < intArrayTheNewWay.length; i++) {
			logger.info("{} ", intArrayTheNewWay[i]);
		}
		logger.info("");

		// Using for enhanced loop
		for (int i : intArrayTheNewWay) {
			logger.info("{} ", i);
		}
		logger.info("");

		// Declaring and initializing 2D array
		int[][] intArray2D = {{1, 3, 5, 7, 9, 11}, {2, 4, 6, 8, 10, 13}, {1, 1, 2, 3, 5, 8, 13, 21}};

		// printing 2D array
		for (int i = 0; i < intArray2D.length; i++) {
			for (int j = 0; j < intArray2D[i].length; j++) {
				logger.info("{} ", intArray2D[i][j]);
			}
			logger.info("");
		}
		logger.info("");

		// Clone array
		int[] cloneArray = intArray.clone();

		// Will print false as deep copy is created for one-dimensional array
		logger.info("Arrays generated with clone command are {}.", intArray == cloneArray ? "identical" : " different");
	}

	private static void stringArrayActions() {
		logger.info("### STRING ARRAY ACTIONS ###");

		// Get some random generated strings
		var generatedStringArray = generateRandomStrings(10);
		printArrayContents(generatedStringArray);

		// Create a copy of that string filled array
		var copiedGeneratedStringArray = Arrays.copyOf(generatedStringArray, generatedStringArray.length);
		printArrayContents(copiedGeneratedStringArray);

		// Create a copy containing a subset of that string filled array
		var copiedSubsetOfGeneratedStringArray = Arrays
				.copyOfRange(generatedStringArray, generatedStringArray.length - 4, generatedStringArray.length);
		printArrayContents(copiedSubsetOfGeneratedStringArray);

		// Sort the contents
		Arrays.sort(copiedSubsetOfGeneratedStringArray);
		printArrayContents(copiedSubsetOfGeneratedStringArray);

		// Sort in a custom way (reversed order)
		Arrays.sort(copiedGeneratedStringArray, new Comparator<>() {
			public int compare(String o1, String o2) {
				// Intentional: Reverse order for this demo
				return o2.compareTo(o1);
			}
		});
		printArrayContents(copiedSubsetOfGeneratedStringArray);
	}

	private static void otherArrayActions() {
		logger.info("### OTHER ARRAY ACTIONS ###");

		// Fill an array
		var fillableArray = new int[10];
		Arrays.fill(fillableArray, 123);
		printArrayContents(fillableArray);

		// Search for a value
		int[] intArray = {1, 3, 5, 7, 9, 11};
		int index = Arrays.binarySearch(intArray, 12);
		logger.info("Found 12 at position {}.", index);
		index = Arrays.binarySearch(intArray, 3);
		logger.info("Found 3 at position {}.", index);
		index = Arrays.binarySearch(intArray, 7);
		logger.info("Found 7 at position {}.", index);
		logger.info("");

		// Check equality
		int[] ints1 = {0, 2, 4, 6, 8, 10};
		int[] ints2 = {0, 2, 4, 6, 8, 10};
		int[] ints3 = {10, 8, 6, 4, 2, 0};

		boolean ints1EqualsInts2 = Arrays.equals(ints1, ints2);
		boolean ints1EqualsInts3 = Arrays.equals(ints1, ints3);

		logger.info("{}", ints1EqualsInts2);
		logger.info("{}", ints1EqualsInts3);
	}

	private static void printArrayContents(int[] array) {
		for (int i = 0; i < array.length; i++) {
			logger.info("{}. {}", i + 1, array[i]);
		}
		logger.info("");
	}

	private static void printArrayContents(String[] array) {
		for (int i = 0; i < array.length; i++) {
			logger.info("{}. {}", i + 1, array[i]);
		}
		logger.info("");
	}

	private static String[] generateRandomStrings(int howMany) {
		String[] names = new String[howMany];
		for (int i = 0; i < howMany; i++) {
			names[i] = generator.getFirstName();
		}
		return names;
	}
}
