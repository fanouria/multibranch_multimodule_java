package gr.codelearn.core.showcase.stream;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class StreamDemo {
	private static final Logger logger = LoggerFactory.getLogger(StreamDemo.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		streamCreateActions();
		streamBasicActions();
		streamIntermediateActions();
		streamFinalActions();
	}

	private static void streamCreateActions() {
		logger.info("");
		logger.info("STREAM CREATE ACTIONS");
		logger.info("--------------------");

		// Create an empty stream
		Stream<String> emptyStream = Stream.empty();

		// Get list of generated words as stream
		Stream<String> sampleStringStream1 = createSampleStringList();

		// Get a stream by splitting a String
		Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

		// Stream can also be created of any type of Collection
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();

		// Array can also be a source of a Stream
		Stream<String> streamOfArray = Stream.of("a", "b", "c");

		// They can  be created out of an existing array or of a part of an array:
		String[] arr = new String[]{"a", "b", "c", "d", "e"};
		Stream<String> streamOfArrayFull = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

		// Stream Builder (trickier)
		Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

		// Creates a sequence of ten strings with the value “element”.
		Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

		// Creates 20 elements starting from 40 then 42, 44, 46
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

		// Stream of primitives
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
	}

	private static void streamBasicActions() {
		logger.info("");
		logger.info("STREAM BASIC ACTIONS");
		logger.info("--------------------");

		// Get list of generated words as stream
		Stream<String> sampleStringStream1 = createSampleStringList();

		// Print content in its natural order
		logger.info("Print in natural order");
		logger.info("-----------------------");
		sampleStringStream1.forEach(logger::info);

		// Get list of generated words as stream
		Stream<String> sampleStringStream2 = createSampleNameList(20).stream();
		logger.info("");

		// Print content in a sorted order
		logger.info("Print in a sorted order");
		logger.info("-----------------------");
		sampleStringStream2.sorted().forEach(logger::info);

		// Concatenate Strings
		logger.info("");
		logger.info("Concatenate streams");
		logger.info("-----------------------");
		Stream<String> sampleConcatStringStream1 = createSampleNameList(3).stream();
		Stream<String> sampleConcatStringStream2 = createSampleNameList(3).stream();
		Stream.concat(sampleConcatStringStream1, sampleConcatStringStream2).sorted().forEach(logger::info);

	}

	public static void streamIntermediateActions() {
		logger.info("");
		logger.info("STREAM INTERMEDIATE ACTIONS");
		logger.info("---------------------------");

		// Joint example
		List<String> list = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").filter(s -> s.startsWith("L"))
				.map(String::toUpperCase).sorted().collect(toList());
		logger.info("Joint example");
		list.forEach(logger::info);
		logger.info("");

		// Filter
		Stream<String> filterStream = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").filter(s -> s.startsWith("L"));
		logger.info("Filter example");
		filterStream.forEach(logger::info);
		logger.info("");

		List<String> generatedNames = createSampleNameList(10);
		logger.info("Source list is: ");
		generatedNames.forEach(logger::info);
		logger.info("");

		// Limit
		Stream<String> limitStream = generatedNames.stream();
		logger.info("Limit example");
		limitStream.limit(5).forEach(logger::info);
		logger.info("");

		// Skip
		Stream<String> skipStream = generatedNames.stream();
		logger.info("Skip example");
		skipStream.skip(2).forEach(logger::info);
		logger.info("");

		// Distinct
		Stream<String> distinctStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Distinct example");
		distinctStream.distinct().forEach(logger::info);
		logger.info("");

		// Sorted
		Stream<String> sortedStream = generatedNames.stream();
		logger.info("Sorted example");
		sortedStream.sorted().forEach(logger::info);
		logger.info("");

		// Sorted with custom comparator
		Stream<String> sortedCustomStream = generatedNames.stream();
		logger.info("Sorted with custom comparator example");
		sortedCustomStream.sorted(Comparator.comparing(String::length).thenComparing(String::valueOf))
				.forEach(logger::info);
		logger.info("");

		// Map
		Stream<String> mapStream = generatedNames.stream();
		logger.info("Map example");
		mapStream.map(String::toLowerCase).forEach(logger::info);
		logger.info("");

		// Map to Integer, Double or Long
		Stream<String> mapToX = generatedNames.stream();
		logger.info("Map to Integer, Double or Long example");
		mapToX.mapToInt(String::length).forEach(s -> logger.info("{}", s));
		logger.info("");

		// FlatMap
		Stream<String> flatMapStream = generatedNames.stream();
		logger.info("FlatMap example");
		flatMapStream.flatMap(s -> s.chars().mapToObj(i -> (char) i)).forEach(s -> logger.info("{}", s));
	}

	public static void streamFinalActions() {
		logger.info("");
		logger.info("STREAM FINAL ACTIONS");
		logger.info("--------------------");

		// ForeachOrdered
		Stream<String> foreachOrderedStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("ForeachOrdered example");
		foreachOrderedStream.distinct().forEachOrdered(logger::info);
		logger.info("");

		// Collect to Set
		Stream<String> collectSetStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to Set example");
		collectSetStream.collect(Collectors.toSet()).forEach(logger::info);
		logger.info("");

		// Collect to List
		Stream<String> collectListStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to List example");
		collectListStream.collect(toList()).forEach(logger::info);
		logger.info("");

		// Collect to general collections
		Stream<String> collectGeneralCollectionsStream = Stream
				.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to general collections example");
		collectGeneralCollectionsStream.collect(Collectors.toCollection(LinkedList::new)).forEach(logger::info);
		logger.info("");

		// Collect to array
		Stream<String> collectArrayStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to array example");
		String[] namesArray = collectArrayStream.toArray(String[]::new);
		Arrays.stream(namesArray).forEach(logger::info);
		logger.info("");

		// Collect to Map
		Stream<String> collectMapStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to Map example");
		collectMapStream.distinct()
				.collect(Collectors.toMap(Function.identity(), s -> (int) s.chars().distinct().count()//
				)).forEach((k, v) -> logger.info("{}:{}", k, v));
		logger.info("");

		// Collect grouping by
		Stream<String> collectGroupingByStream = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		logger.info("Collect grouping by example");
		Map<Character, List<String>> groupingByMap = collectGroupingByStream.distinct().sorted()
				.collect(Collectors.groupingBy(s -> s.charAt(0)));//
		logger.info("{}.", groupingByMap);
		logger.info("");

		// Collect grouping by downstream collector
		Stream<String> collectGroupingByDownstreamCollector = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		logger.info("Collect grouping by  downstream collector example");
		Map<Character, Long> groupingByDownstreamMap = collectGroupingByDownstreamCollector.distinct().sorted()
				.collect(Collectors.groupingBy(s -> s.charAt(0), counting()));
		logger.info("{}.", groupingByDownstreamMap);
		logger.info("");

		// Calculation
		OptionalDouble optionalDouble = IntStream.of(1, 2, 3, 4, 5, 6).average();
		logger.info("Average is {}.", optionalDouble);
		long optionalLong = LongStream.of(12344, 12112, 121212, 12121111).max().orElse(0);
		logger.info("Maximum value is {}.", optionalLong);
		logger.info("");

		// Reduce
		List<Invoice> invoices = Arrays.asList(new Invoice("A01", BigDecimal.valueOf(9.85), BigDecimal.valueOf(5)),
				new Invoice("A02", BigDecimal.valueOf(24.99), BigDecimal.valueOf(3)),
				new Invoice("A03", BigDecimal.valueOf(4.99), BigDecimal.valueOf(4)));

		logger.info("Reduce example");
		BigDecimal summary = invoices.stream().map(x -> x.getQuantity().multiply(x.getPrice()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		logger.info("Summary of invoices is {}.", summary);
		logger.info("");

		// AnyMatch
		logger.info("AnyMatch example");
		Stream<String> anyMatchStream = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		boolean anyMatchFound = anyMatchStream.anyMatch(s -> s.startsWith("C"));
		logger.info("{} for character C.", anyMatchFound ? "Found at least one match" : "No match found");
		logger.info("");

		// AllMatch
		logger.info("AllMatch example");
		Stream<String> allMatchStream = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		boolean allMatchFound = allMatchStream.allMatch(s -> s.startsWith("C"));
		logger.info("{} for character C.", allMatchFound ? "Found all matches" : "Not all matches found");
		logger.info("");

		// NoneMatch
		logger.info("NoneMatch example");
		Stream<String> noneMatchStream = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		boolean noneMatchFound = noneMatchStream.noneMatch(s -> s.startsWith("X"));
		logger.info("{} for character X.", noneMatchFound ? "None match found" : "None match was not found");
		logger.info("");

		// FindAny
		logger.info("FindAny example");
		Stream<String> findAnyStream = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		// When stream is parallel, result may vary
		Optional<String> findAny = findAnyStream.findAny();
		logger.info("{}.", findAny.isPresent() ? findAny.get() : findAny.orElseGet(() -> "None"));
		logger.info("");

		// FindFirst
		logger.info("FindFirst example");
		Stream<String> findFirstStream = Stream
				.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick", "John", "Costas");
		// When stream is parallel, result may vary
		Optional<String> findFirst = findFirstStream.filter(s -> s.length() > 5).sorted().findFirst();
		logger.info("{}.", findFirst.isPresent() ? findFirst.get() : findFirst.orElseGet(() -> "None"));
		logger.info("");
	}

	private static Stream<String> createSampleStringList() {
		String generatedWords = generator.getWords(30);
		return Pattern.compile(" ").splitAsStream(generatedWords);
		// return Arrays.stream(generatedWords.split(" "));
	}

	private static List<String> createSampleNameList(int howMany) {
		List<String> names = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			names.add(generator.getFirstName());
		}
		return names;
	}
}

class Invoice {
	String invoiceNo;
	BigDecimal price;
	BigDecimal quantity;

	public Invoice(String invoiceNo, BigDecimal price, BigDecimal quantity) {
		this.invoiceNo = invoiceNo;
		this.price = price;
		this.quantity = quantity;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
