package Jsontron.expressionResolver;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ReturnType {

    private ReturnType() {}

    public static Double NUMBER;

    public static Boolean BOOLEAN;

    public static String STRING;

    public static JsonNode NODE;

    public static List<JsonNode> NODESET;
}
