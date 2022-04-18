package Jsontron.utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {
    public static List<String> nodeToList(JsonNode node) {
        List<String> patternIds = new ArrayList<>();
        for (int i = 0; i < node.size(); i++) {
            patternIds.add(node.get(i).toString());
        }
        return patternIds;
    }
}
