package gr.codelearn.core.showcase.designpattern.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//proxy
public class ReportGeneratorProxy {
	private static final Logger logger = LoggerFactory.getLogger(ReportGeneratorProxy.class);

	ReportGenerator reportGenerator;

	public void displayReportTemplate(String reportFormat) {
		//code to display the report template
		logger.info("ReportGeneratorImplProxy: Displaying blank report template in {} format.", reportFormat);
	}

	public void generateReport(String reportFormat) {
		if (reportGenerator == null) reportGenerator = new ReportGenerator();
		//expensive code is only called when really needed
		reportGenerator.generateReport(reportFormat);
	}

}
