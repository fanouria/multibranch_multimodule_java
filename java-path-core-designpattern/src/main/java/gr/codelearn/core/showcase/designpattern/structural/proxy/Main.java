package gr.codelearn.core.showcase.designpattern.structural.proxy;

//client
public class Main {
	public static void main(String[] args) {

		ReportGeneratorProxy proxy = new ReportGeneratorProxy();
		//display a proxy report until the real one is requested:
		proxy.displayReportTemplate("PDF");
		proxy.generateReport("PDF");

	}
}
