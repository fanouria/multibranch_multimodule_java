package gr.codelearn.core.showcase.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayDefaultValues {
	private static final Logger logger = LoggerFactory.getLogger(ArrayDefaultValues.class);

	public static void main(String[] args) {
		logger.info("String array default values:");
		var stringBuilder = new StringBuilder();
		var stringArray = new String[3];
		for (String val : stringArray) {
			stringBuilder.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder.toString());

		logger.info("Integer array default values:");
		var stringBuilder2 = new StringBuilder();
		int[] integerArray = new int[3];
		for (int val : integerArray) {
			stringBuilder2.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder2.toString());

		logger.info("Double array default values:");
		double[] doubleArray = new double[3];
		var stringBuilder3 = new StringBuilder();
		for (double val : doubleArray) {
			stringBuilder3.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder3.toString());

		logger.info("Boolean array default values:");
		var booleanArray = new boolean[3];
		var stringBuilder4 = new StringBuilder();
		for (boolean val : booleanArray) {
			stringBuilder4.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder4.toString());

		logger.info("Reference Array default values:");
		var myArray = new ArrayDefaultValues[3];
		var stringBuilder5 = new StringBuilder();
		for (ArrayDefaultValues val : myArray) {
			stringBuilder5.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder5.toString());
	}
}
