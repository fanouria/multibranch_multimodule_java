package gr.codelearn.core.showcase.collection;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SetDemo {
	private static final Logger logger = LoggerFactory.getLogger(SetDemo.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		createSets();
		createTreeSet();
		createLinkedHashSet();
	}

	private static void createSets() {
		logger.info("### SET ACTIONS ###");
		// Default implementation
		Set<String> sampleSet = new HashSet<>();
		sampleSet.add("one");
		sampleSet.add("two");
		sampleSet.add("three");
		sampleSet.addAll(generateRandomStrings(10));

		// Looping using an iterator
		Iterator<String> iterator = sampleSet.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			logger.info("{}", element);
		}
		logger.info("");

		// Enhanced for loop
		for (String item : sampleSet) {
			logger.info("{}", item);
		}
		logger.info("");

		// Checking for existence
		logger.info("Token 'three' {}.", sampleSet.contains("three") ? "exists." : "does not exist.");
		logger.info("Token 'four' {}.", sampleSet.contains("four") ? "exists." : "does not exist.");
		logger.info("");

		// Unmodifiable set
		var emptyReadOnlySet = Set.<String>of();
		Set<String> readOnlySet = Set
				.of("Athens", "Thessaloniki", "Patra", "Herakleion", "Ioannina", "Larisa", "Volos", "Rodos");
		logger.info("Physical order");
		logger.info("--------------------");
		for (String item : readOnlySet) {
			logger.info("{}", item);
		}
		logger.info("");
	}

	private static void createTreeSet() {
		logger.info("");
		logger.info("### TREESET ACTIONS ###");

		// TreeSet implementation
		TreeSet<String> sampleTreeSet = new TreeSet<>();
		sampleTreeSet.addAll(Set
				.of("Athens", "Thessaloniki", "Patra", "Herakleion", "Ioannina", "Larisa", "Volos", "Rodos"));
		sampleTreeSet.add("Item to be deleted");

		logger.info("Sorted order");
		logger.info("--------------------");
		for (String item : sampleTreeSet) {
			logger.info("{}", item);
		}
		logger.info("First value is {} and last values is {}.", sampleTreeSet.first(), sampleTreeSet.last());

		// Before removal
		logger.info("");
		logger.info("Before removal: {}.", sampleTreeSet);

		//Remove item
		sampleTreeSet.remove("Item to be deleted");
		logger.info("After removal: {}.", sampleTreeSet);

		// Removing the first element
		sampleTreeSet.pollFirst();
		logger.info("After removing first: {}.", sampleTreeSet);

		// Removing the last element
		sampleTreeSet.pollLast();
		logger.info("After removing last: {}.", sampleTreeSet);
		logger.info("");
	}

	private static void createLinkedHashSet() {
		logger.info("");
		logger.info("### LINKEDHASHSET ACTIONS ###");

		// Maintains natural order of insertion
		LinkedHashSet<String> sampleLinkedHashSet = new LinkedHashSet<>();
		sampleLinkedHashSet.addAll(Set
				.of("Athens", "Thessaloniki", "Patra", "Herakleion", "Ioannina", "Larisa", "Volos", "Rodos"));
		logger.info("Printing set order: {}", sampleLinkedHashSet);
		logger.info("");

		LinkedHashSet<String> linkedset = new LinkedHashSet<>();

		// Adding element to LinkedHashSet
		linkedset.add("A");
		linkedset.add("B");
		linkedset.add("C");
		linkedset.add("D");

		// This will not add new element as A already exists
		linkedset.add("A");
		linkedset.add("E");

		logger.info("Size of LinkedHashSet: {}.", linkedset.size());
		logger.info("Original LinkedHashSet: {}.", linkedset);
		logger.info("Removing D from LinkedHashSet: {}.", linkedset.remove("D"));
		logger.info("Trying to Remove Z which is not present: {}.", linkedset.remove("Z"));
		logger.info("Checking if A is present: {}.", linkedset.contains("A"));
		logger.info("Updated LinkedHashSet: {}.", linkedset);
	}

	private static Set<String> generateRandomStrings(int howMany) {
		Set<String> names = new HashSet<>();
		for (int i = 0; i < howMany; i++) {
			names.add(generator.getFirstName());
		}
		return names;
	}
}
