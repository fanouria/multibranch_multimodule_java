package gr.codehub.core.showcase.oop.animal;

public class GermanShepherd extends Dog {

	private Size size;

	public GermanShepherd() {
	}

	public GermanShepherd(String name, int age, String eyeColor, Size size) {
		super(name, age, eyeColor);
		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
}
