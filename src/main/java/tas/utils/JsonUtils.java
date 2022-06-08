package main.java.tas.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public final class JsonUtils {

    /**
     * Reads data from a JSON file and returns the relative JSONObject
     * 
     * @param jsonPath where the file is
     * @return the relative JSONObject
     */
    public static JSONObject setupJsonData(String jsonPath) {
        String content = "";
        try {
            content = Files.readString(Paths.get(jsonPath));
        } catch (IOException e) {
            System.out.println("Error: No enemy json file detected!");
            System.out.println(e);
        }
        return new JSONObject(content);
    }

}
