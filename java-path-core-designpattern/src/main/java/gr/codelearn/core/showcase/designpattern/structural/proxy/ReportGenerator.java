package gr.codelearn.core.showcase.designpattern.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//real subject
public class ReportGenerator {
	private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

	public ReportGenerator() {
		logger.info("ReportGeneratorImpl instance created");
	}

	public void generateReport(String reportFormat) {
		logger.info("ReportGeneratorImpl: Generating complex report in {} format.", reportFormat);
	}

}
