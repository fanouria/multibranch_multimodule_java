package gr.codelearn.core.showcase.serialization.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
	// If the following field changes before reading a saved object, an InvalidClassException will be thrown
	private static final long serialVersionUID = 123;

	private String name;
	private String gender;
	private int yearOfBirth;
	// Transient attributes will not be taken into account when persisting the object. Here is an example one that will
	// not be saved
	private transient Date registerDate;
	// Static fields will also not be saved, as they belong to to the class and not to each object of the class
	private static String location;

	public Customer() {
		// Required by serialization or java.lang.InstantiationException will be thrown
		// Instance of "now":
		registerDate = new Date();
		location = "Greece";
	}

	public Customer(String name, String gender, int yearOfBirth) {
		this.name = name;
		this.gender = gender;
		this.yearOfBirth = yearOfBirth;
		// Instance of "now":
		registerDate = new Date();
		location = "Greece";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		Customer.location = location;
	}

	private void writeObject(ObjectOutputStream outputStream) throws IOException {
		// Method that allows us to change the default writing protocol of serialization
		// Only read by the JVM during runtime
		// In this case, we add some "encryption salt" to our gender for demonstration purposes
		gender += "Encrypted";
		outputStream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
		// Method that allows us to change the default reading protocol of serialization
		// Only read by the JVM during runtime
		// our "pseudo-constructor"
		inputStream.defaultReadObject();
		// now we are a "live" object again, so let's "decrypt" and set the registerDate
		// Note: this only works for the (Text) version and not the other parsing methods
		gender = gender.substring(0, 1);
		// If we do not set the date for example, it will be left as null when restored from the ByteService methods
		registerDate = new Date();

	}

	@Override
	public String toString() {
		return "Customer{" + "name='" + name + '\'' + ", gender='" + gender + '\'' + ", yearOfBirth=" + yearOfBirth +
				", registerDate=" + registerDate + '}';
	}
}
