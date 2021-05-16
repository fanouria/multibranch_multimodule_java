package gr.codehub.core.showcase.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GeneralExamplesMain {
	private static final Logger logger = LoggerFactory.getLogger(GeneralExamplesMain.class);

	public static void main(String[] args) {
		typeSafetyExample();
		upperBoundExample();
		lowerBoundExample();
	}

	public static void typeSafetyExample() {
		List listError = new ArrayList();
		listError.add("abc");
		listError.add(5);

		//run time error, ClassCastException
		for (Object object : listError) {
			String str = (String) object;
			logger.info("{} ", str);
		}

		List<String> listGeneric = new ArrayList<>();
		listGeneric.add("hello");
		//listGeneric.add(5); compiler error
		listGeneric.add(" world");
		listGeneric.forEach(item -> logger.info("{} ", item));
	}

	public static void upperBoundExample() {
		List<Integer> integerList = new ArrayList<>();
		integerList.add(5);
		integerList.add(15);
		integerList.add(10);
		logger.info("The mean of the integer list is:{} ", calculateMean(integerList));

		List<Double> doubleList = new ArrayList<>();
		doubleList.add(12.5);
		doubleList.add(15.5);
		doubleList.add(2.5);
		logger.info("The mean of the double list is:{} ", calculateMean(doubleList));
	}

	public static double calculateMean(List<? extends Number> numbers) {
		if (!numbers.isEmpty()) return numbers.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
		else return 0;
	}

	public static void lowerBoundExample() {
		List<Integer> integerList = new ArrayList<>();
		integerList.add(5);

		List<Number> numberList = new ArrayList<>();
		numberList.add(5);

		List<Double> doubleList = new ArrayList<>();
		doubleList.add(5.0);

		List<? super Integer> lowerBoundList;
		lowerBoundList = integerList;
		lowerBoundList = numberList;
		//lowerBoundList = doubleList; compiler error
	}
}
