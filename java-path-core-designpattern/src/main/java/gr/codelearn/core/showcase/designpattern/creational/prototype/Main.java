package gr.codelearn.core.showcase.designpattern.creational.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//let's suppose that the following object was retrieved from a database
		Document doc = new Document("CodeHub");
		//instead of retrieving the document from the database again, we can simply clone it
		Document docCopy1 = doc.cloneDocument();
		docCopy1.setAge(27);
		docCopy1.setSignature("AD");
		Document docCopy2 = doc.cloneDocument();
		docCopy2.setAge(45);
		docCopy2.setSignature("TN");

		logger.info("{}", doc);
		logger.info("{}", docCopy1);
		logger.info("{}", docCopy2);
	}
}
