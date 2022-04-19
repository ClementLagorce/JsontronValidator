package jsontron.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Renderer {
    public static String jsonRenderer(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        JsonElement je = JsonParser.parseString(json);
        return gson.toJson(je);
    }
}
