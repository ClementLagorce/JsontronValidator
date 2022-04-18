package Jsontron.parsers;

import Jsontron.elements.Assert;
import Jsontron.exception.SchemaException;
import Jsontron.elements.Rule;
import Jsontron.dictionary.ruleDictionary;
import com.fasterxml.jackson.databind.JsonNode;

public class RuleParser {

    public static Rule parseRule(JsonNode nodeRule) {
        Rule myRule = new Rule();
        try {
            if (nodeRule.size() != 1 || nodeRule.get(0).isEmpty()) {
                throw new SchemaException("Error : The rule provided doesn't respect the syntax");
            } else {
                for (ruleDictionary keyword : ruleDictionary.values()) {
                    JsonNode nodeTemp = nodeRule.get(0).get(keyword.getValue());
                    if (nodeTemp == null) {
                        throw new SchemaException("Error : The rule provided doesn't contain '" + keyword.getValue() + "' keyword");
                    } else if (keyword.getValue().equals("assert")) {
                        Assert myAssert = AssertParser.parseAssert(nodeTemp);
                        myRule.setter(myAssert, keyword);
                    } else {
                        myRule.setter(nodeTemp.toString(), keyword);
                    }
                }
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
        return myRule;
    }
}