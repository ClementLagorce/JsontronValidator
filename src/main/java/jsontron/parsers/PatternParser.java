package jsontron.parsers;

import jsontron.exception.SchemaException;
import jsontron.elements.Pattern;
import jsontron.elements.Rule;
import jsontron.dictionary.patternDictionary;
import com.fasterxml.jackson.databind.JsonNode;

public class PatternParser {
    public static Pattern parsePattern(JsonNode nodePattern) {
        Pattern myPattern = new Pattern();
        try {
            if (nodePattern.size() != 1 || nodePattern.get(0).isEmpty()) {
                throw new SchemaException("Error : The pattern provided doesn't respect the syntax");
            } else {
                for (patternDictionary keyword : patternDictionary.values()) {
                    JsonNode nodeTemp = nodePattern.get(0).get(keyword.getValue());
                    if (nodeTemp == null) {
                        throw new SchemaException("Error : The pattern provided doesn't contain '" + keyword.getValue() + "' keyword");
                    } else if (keyword.getValue().equals("rule")) {
                        Rule myRule = RuleParser.parseRule(nodeTemp);
                        myPattern.setter(myRule, keyword);
                    } else {
                        myPattern.setter(nodeTemp.toString(), keyword);
                    }
                }
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
        return myPattern;
    }
}