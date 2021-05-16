package gr.codelearn.core.showcase.designpattern.structural.adapter.service;

public class NewLineFormatter implements TextFormattable {
	@Override
	public String formatText(String text) {
		return text.replace(".", "\n");
	}
}
