package Jsontron.parsers;

import Jsontron.exception.SchemaException;
import Jsontron.elements.Phase;
import Jsontron.dictionary.phaseDictionary;
import Jsontron.utils.ParseUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class PhaseParser {

    public static Phase parsePhase(JsonNode nodePhase) {
        Phase myPhase = new Phase();
        try {
            if (nodePhase.size() != 1 || nodePhase.get(0).isEmpty()) {
                throw new SchemaException("Error : The phase provided doesn't respect the syntax");
            } else {
                for (phaseDictionary keyword : phaseDictionary.values()) {
                    JsonNode nodeTemp = nodePhase.get(0).get(keyword.getValue());
                    if (nodeTemp == null) {
                        throw new SchemaException("Error : The phase provided doesn't contain '" + keyword.getValue() + "' keyword");
                    } else if (keyword.getValue().equals("active")) {
                        for (String id : ParseUtils.nodeToList(nodeTemp)) {
                            myPhase.setter(id, keyword);
                        }
                    } else {
                        myPhase.setter(nodeTemp.toString(), keyword);
                    }
                }
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
        return myPhase;
    }
}
