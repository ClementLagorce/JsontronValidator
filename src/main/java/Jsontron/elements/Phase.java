package Jsontron.elements;

import Jsontron.exception.SchemaException;
import Jsontron.dictionary.phaseDictionary;

import java.util.ArrayList;
import java.util.List;

public class Phase {

    private String id;

    private List<String> patternIds;
    public Phase() {
        patternIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPatternIds() {
        return patternIds;
    }

    public void setPatternIds(List<String> patternIds) {
        this.patternIds = patternIds;
    }

    public void putPatternId(String patternId) {
        this.patternIds.add(patternId);
    }

    public String toString() {
        return "[{\"id\":" + this.id + ",\"active\":" + this.patternIds.toString() + "}]";
    }

    public void setter(String element, phaseDictionary key) {
        try {
            switch(key) {
                case ID:
                    this.setId(element);
                    break;
                case ACTIVE:
                    this.putPatternId(element);
                    break;
                default:
                    throw new SchemaException("Error : unknown keyword in phase");
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
    }
}