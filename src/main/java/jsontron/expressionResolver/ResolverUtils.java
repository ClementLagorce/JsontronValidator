package jsontron.expressionResolver;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolverUtils {

    public static void evalXPath(String xPath, JsonNode instanceData) {
        List<Integer> allSlashs = matchIndexes(xPath, "((?<!/)/(?!/))|(//)");

        List<String> words = detectWord(xPath, allSlashs);
        List<JsonNode> nodes = new ArrayList<>();
        nodes.add(instanceData);

        List<JsonNode> caca = recursive(nodes, words);
    }

    public static List<JsonNode> recursive(List<JsonNode> nodes, List<String> words) {
        List<JsonNode> result = new ArrayList<>();
        List<JsonNode> temp = new ArrayList<>();
        String word = words.get(0);
        if (word.startsWith("//")) {
            for (JsonNode node : nodes) {
                temp.addAll(doubleSlash(node, word));
            }
        } else if (word.startsWith("/")) {
            for (JsonNode node : nodes) {
                temp.addAll(slash(node, word));
            }
        } else {
            for (JsonNode node : nodes) {
                temp.addAll(nothing(node, word));
            }
        }
        words.remove(0);
        if (words.isEmpty()) {
            return temp;
        } else {
            List<JsonNode> temp2 = recursive(temp, words);
            for (JsonNode node : temp2) {
                if (node != null) {
                    result.add(node);
                }
            }
        }
        return result;
}

    public static List<String> detectWord(String xPath, List<Integer> indexes) {
        List<String> words = new ArrayList<>();
        if (xPath.charAt(0) != '/') {
            words.add(xPath.substring(0, indexes.get(0)));
        }
        for (int i = 0; i < indexes.size(); i++) {
            if (i < indexes.size() - 1) {
                words.add(xPath.substring(indexes.get(i), indexes.get(i + 1)));
            } else {
                words.add(xPath.substring(indexes.get(i)));
            }
        }
        return words;
    }

    public static List<JsonNode> slash(JsonNode current, String key) {
        List<JsonNode> result = new ArrayList<>();
        if (current.isArray()) {
            for (JsonNode arrayNode : current) {
                result.add(arrayNode.get(key.substring(1)));
            }
        } else {
            result.add(current.get(key.substring(1)));
        }
        return result;
    }

    public static List<JsonNode> doubleSlash(JsonNode current, String key) {
        List<JsonNode> result = new ArrayList<>();
        for (JsonNode node : current.findParents(key.substring(2))) {
            result.add(node.get(key.substring(2)));
        }
        return result;
    }

    public static List<JsonNode> nothing(JsonNode current, String key) {
        return current.findParents(key);
    }

    public static List<Integer> matchIndexes(String xPath, String match) {
        List<Integer> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(xPath);
        while (matcher.find()) {
            result.add(matcher.start());
        }
        return result;
    }
}
