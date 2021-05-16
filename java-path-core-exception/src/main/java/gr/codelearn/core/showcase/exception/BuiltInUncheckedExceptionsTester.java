package gr.codelearn.core.showcase.exception;

import gr.codelearn.core.showcase.exception.domain.ClassA;
import gr.codelearn.core.showcase.exception.domain.ClassB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BuiltInUncheckedExceptionsTester {
	private static final Logger logger = LoggerFactory.getLogger(BuiltInUncheckedExceptionsTester.class);

	public static void main(String[] args) {
		//the compiler does not require the following exceptions to be handled, mostly because they are logic errors
		//and the compiler cannot recognise such errors
		//however (if you are using any sophisticated IDE), most of these on-purpose exceptions will be shown as
		//"code-crashing" issues
		//the following methods cannot be run all together, only one can be run at a time, so uncomment the one you
		//would like to see

		//divisionByZero();
		//arrayOutOfBounds();
		//stringOutOfBounds();
		//notANumberExample();
		//methodCallOnNull();
		//saveWrongDataTypeToArray();
		//incorrectClassCast();
		//modifyUnmodifiableCollection();
	}

	public static int divisionByZero() {
		//throws ArithmeticException as the user tries to divide by zero which is not mathematically sound
		return 5 / 0;
	}

	public static int arrayOutOfBounds() {
		//throws ArrayIndexOutOfBoundsException as the user tries to get a cell from the array which does not exist
		int array[] = new int[20];
		return array[21];
	}

	public static char stringOutOfBounds() {
		//throws StringIndexOutOfBoundsException as the user tries to get a cell from the character array
		//which does not exist
		String sentence = "Artemis is the goddess of the hunt";
		return sentence.charAt(500);
	}

	public static double notANumberExample() {
		//will not throw an exception, but can still cause problems as the sqr variable will be "NaN"
		double sqr = Math.sqrt(-3);
		logger.info("{}", 5 * sqr);
		return 5 * sqr;
	}

	public static int methodCallOnNull() {
		//throws NullPointerException as the user tries to call a method on a null object
		ArrayList<Integer> arrayList = null;
		return arrayList.get(5);
	}

	public static void saveWrongDataTypeToArray() {
		//throws ArrayStoreException, as the user tries to save a String to an Integer array
		Object array[] = new Integer[3];
		array[0] = "5";
	}

	public static void incorrectClassCast() {
		//throws ClassCastException, as the user tries to save a parent class to a child class with casting
		ClassA object1 = new ClassA();
		ClassB object2 = (ClassB) object1;
	}

	public static void modifyUnmodifiableCollection() {
		//throws UnsupportedOperationException, as the user tries to modify a list which is unmodifiable

		//creating object of ArrayList<Character>
		List<Character> list = new ArrayList<Character>();
		//populate the list
		list.add('X');
		list.add('Y');
		//printing the list
		logger.info("Initial list: {}", list);
		//getting unmodifiable list
		//using unmodifiableCollection() method
		Collection<Character> immutablelist = Collections.unmodifiableCollection(list);
		//Adding element to new Collection
		logger.info("Trying to modify the unmodifiableCollection");
		immutablelist.add('Z');
	}
}
