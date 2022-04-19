package jsontron.parsers;

import jsontron.elements.Assert;
import jsontron.exception.SchemaException;
import jsontron.dictionary.assertDictionary;
import com.fasterxml.jackson.databind.JsonNode;

public class AssertParser {

    public static Assert parseAssert(JsonNode nodeAssert) {
        Assert myAssert = new Assert();
        try {
            if (nodeAssert.size() != 1 || nodeAssert.get(0).isEmpty()) {
                throw new SchemaException("Error : The assert provided doesn't respect the syntax");
            } else {
                for (assertDictionary keyword : assertDictionary.values()) {
                    JsonNode nodeTemp = nodeAssert.get(0).get(keyword.getValue());
                    if (nodeTemp == null) {
                        throw new SchemaException("Error : The rule provided doesn't contain '" + keyword.getValue() + "' keyword");
                    } else {
                        myAssert.setter(nodeTemp.toString(), keyword);
                    }
                }
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
        return myAssert;
    }
}