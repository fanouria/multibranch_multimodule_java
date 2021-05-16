package gr.codelearn.core.showcase.serialization.service;

import gr.codelearn.core.showcase.serialization.domain.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ByteService<T> {
	private static final Logger logger = LoggerFactory.getLogger(ByteService.class);

	public void writeObjectToFile(T object, String fileName) {
		// Writes an object (byte form) to a file
		try (final FileOutputStream outputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + fileName);
			 ObjectOutputStream oout = new ObjectOutputStream(outputStream)) {

			oout.writeObject(object);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public T readObjectFromFile(String fileName) {
		T object = null;
		// Reads an object from a file
		logger.info("Reading object from a file:");
		try (final FileInputStream fin = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + fileName);
			 ObjectInputStream oin = new ObjectInputStream(fin)) {

			object = (T) oin.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}

	public void writeMultipleObjectsToFile(List<T> objectList, String fileName) {
		// Similar to the writing a single object, but the same method can also be used to write multiple objects in a
		// file
		try (FileOutputStream outputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + fileName);
			 ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
			for (T object : objectList) {
				out.writeObject(object);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<T> readMultipleObjectsFromFile(String fileName) {
		List<T> objectsList = new ArrayList<>();
		try (FileInputStream inputStream = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + fileName);
			 ObjectInputStream in = new ObjectInputStream(inputStream)) {
			T object;
			// Check EOFException as to why this infinite loop exists
			while (true) {
				object = (T) in.readObject();
				objectsList.add(object);
			}

		} catch (EOFException e) {
			// Exception which is thrown when end of file has been reached
			// No need to be handled as it helps break out of the infinite loop
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objectsList;
	}
}
