package gr.codelearn.core.showcase.designpattern.structural.adapter.service;

//concrete adaptee
public class CsvFormatter implements CsvFormattable {
	@Override
	public String formatCsvText(String text) {
		return text.replace(".", ",");
	}
}
