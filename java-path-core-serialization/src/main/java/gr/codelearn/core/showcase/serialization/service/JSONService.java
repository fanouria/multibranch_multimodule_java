package gr.codelearn.core.showcase.serialization.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import gr.codelearn.core.showcase.serialization.domain.Directory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONService<T> {

	public void serializeToJSON(List<T> data, String fileName) {
		try (FileWriter file = new FileWriter(Directory.FILE_DIRECTORY.getPath() + fileName)) {
			// Google's JSON library
			Gson gson = new Gson();
			file.write(gson.toJson(data));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public List<T> deserializeFromJSON(Class<T[]> clazz, String fileName) {
		List<T> data = null;
		// Usual I/O + Google's json reader
		try (FileReader file = new FileReader(Directory.FILE_DIRECTORY.getPath() + fileName);
			 JsonReader jsonReader = new JsonReader(file);) {
			Gson gson = new Gson();
			// The following code was tested and throws a ClassCastException:
			//Type collectionType = new TypeToken<List<T>>(){}.getType();
			//data = gson.fromJson(jsonReader, collectionType);
			// So another work-around had to be found
			// For more info: https://stackoverflow.com/questions/14503881/strange-behavior-when-deserializing-nested-generic-classes-with-gson/14506181#14506181

			// We have to define at compile time what class type Java should expect here, because Gson is not able to
			// know on its own during runtime
			T[] dataArray = gson.fromJson(jsonReader, clazz);
			data = Arrays.asList(dataArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
