package utils;

import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonReader {

    public static JSONObject getTestData(String name) throws Exception {

        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(name);

        if (is == null) {
            throw new RuntimeException("JSON file not found!" +name);
        }

        String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        return new JSONObject(content);
    }
}