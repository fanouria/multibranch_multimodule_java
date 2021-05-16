package gr.codelearn.core.showcase.serialization.service;

import gr.codelearn.core.showcase.serialization.domain.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class XMLService<T> {
	private static final Logger logger = LoggerFactory.getLogger(XMLService.class);

	public void serializeToXML(List<T> data, String fileName) {
		// Usual I/O
		try (FileOutputStream outputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + fileName);
			 XMLEncoder encoder = new XMLEncoder(outputStream)) {
			// Adding an exception listener to the encoder in case something goes wrong
			encoder.setExceptionListener(e -> logger.info("Exception!: {}", e.toString()));
			encoder.writeObject(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<T> deserializeFromXML(String fileName) {
		List<T> data = null;
		// Usual I/O
		try (FileInputStream inputStream = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + fileName);
			 XMLDecoder decoder = new XMLDecoder(inputStream)) {
			data = (List<T>) decoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
