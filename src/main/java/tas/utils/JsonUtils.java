package main.java.tas.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
		final InputStream in = ClassLoader.getSystemResourceAsStream(jsonPath);
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));

		final String content = br.lines().collect(Collectors.joining());

		try {
			in.close();
			br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return new JSONObject(content);
	}

}
