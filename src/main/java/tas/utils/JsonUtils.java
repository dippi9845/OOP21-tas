package main.java.tas.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

/**
 * Class that provides some useful functions for JSON object handling.
 */
public final class JsonUtils {

	/**
	 * Reads data from a JSON file and returns the relative JSONObject
	 * 
	 * @param jsonPath where the file is
	 * @return the relative JSONObject
	 */
	public static JSONObject getJsonData(final String jsonPath) {
		String content = "";
		try {
			content = Files.readString(Paths.get(jsonPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JSONObject(content);
	}

}
