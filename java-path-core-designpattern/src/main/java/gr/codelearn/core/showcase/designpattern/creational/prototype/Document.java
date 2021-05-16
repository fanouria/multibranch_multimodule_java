package gr.codelearn.core.showcase.designpattern.creational.prototype;

public class Document implements Cloneable {

	private String employer;
	private int age;
	private String signature;

	public Document(String employer) {
		this.employer = employer;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Document cloneDocument() {
		Document doc = null;
		try {
			doc = (Document) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return doc;
	}

	@Override
	public String toString() {
		return "Document{" + "employer='" + employer + '\'' + ", age=" + age + ", signature='" + signature + '\'' + '}';
	}
}
