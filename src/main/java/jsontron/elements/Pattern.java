package jsontron.elements;

import jsontron.exception.SchemaException;
import jsontron.dictionary.patternDictionary;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    private String id;

    private String title;

    private List<Rule> rules;
    public Pattern() {
        rules = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void putRule(Rule rule) {
        this.rules.add(rule);
    }

    public String toString() {
        String result = "[{\"id\":" + this.id +
                      ",\"title\":" + this.title + ",";

        String ruleString = "";
        for (int i = 0; i < rules.size(); i++) {
            if (i == rules.size() - 1) {
                ruleString += "\"rule\":" + rules.get(i).toString();
            } else {
                ruleString += "\"rule\":" + rules.get(i).toString() + ",";
            }
        }

        result += ruleString + "}]";
        return result;
    }

    public void setter(Object element, patternDictionary key) {
        try {
            switch(key) {
                case ID:
                    this.setId((String) element);
                    break;
                case TITLE:
                    this.setTitle((String) element);
                    break;
                case RULE:
                    this.putRule((Rule) element);
                    break;
                default:
                    throw new SchemaException("Error : unknown keyword in pattern");
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
    }
}