package gr.codelearn.core.showcase.designpattern.structural.adapter;

import gr.codelearn.core.showcase.designpattern.structural.adapter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//client
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		String testText = "Hello.My name is Bond.James Bond.";
		//new line formatter
		TextFormattable nlf = new NewLineFormatter();
		String newlineText = nlf.formatText(testText);
		logger.info("{}", newlineText);

		//csv formatter
		CsvFormattable cf = new CsvFormatter();
		String csvFormattedText = cf.formatCsvText(testText);
		logger.info("{}", csvFormattedText);

		//adapter
		//"adapts" the csv formatting to "textFormattable" classes
		TextFormattable cad = new CsvAdapter(cf);
		String adaptedText = cad.formatText(testText);
		logger.info("{}", adaptedText);
	}
}
