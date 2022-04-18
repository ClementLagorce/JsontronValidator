package Jsontron.parsers;

import Jsontron.exception.SchemaException;
import Jsontron.elements.Pattern;
import Jsontron.elements.Phase;
import Jsontron.elements.Schema;
import Jsontron.dictionary.schemaDictionary;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

import java.io.*;

public class SchemaParser {

    public static Schema parseSchema(String content) {
        Schema mySchema = new Schema();
        try {
            JsonNode nodeFile = JsonLoader.fromString(content);
            if (nodeFile.isEmpty() || nodeFile.get("schema") == null) {
                throw new SchemaException("Error : The file provided is empty or doesn't contains 'schema' keyword");
            } else {
                JsonNode nodeSchema = nodeFile.get("schema");
                for (schemaDictionary keyword : schemaDictionary.values()) {
                    if (nodeSchema.isEmpty() || nodeSchema.get(keyword.getValue()) == null) {
                        if (keyword.getValue().equals("phase")) {
                            continue;
                        } else {
                            throw new SchemaException("Error : The schema provided doesn't contain '" + keyword.getValue() + "' keyword");
                        }
                    } else {
                        if (keyword.getValue().equals("phase")) {
                            Phase myPhase = PhaseParser.parsePhase(nodeSchema.get(keyword.getValue()));
                            mySchema.setter(myPhase, keyword);
                        } else if (keyword.getValue().equals("pattern")) {
                            Pattern myPattern = PatternParser.parsePattern(nodeSchema.get(keyword.getValue()));
                            mySchema.setter(myPattern, keyword);
                        } else {
                            mySchema.setter(nodeSchema.get(keyword.getValue()).toString(), keyword);
                        }
                    }
                }
            }
        } catch (IOException | SchemaException e) {
            e.printStackTrace();
        }
        return mySchema;
    }
}
