package Jsontron.elements;

import Jsontron.exception.SchemaException;
import Jsontron.dictionary.ruleDictionary;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private String id;

    private boolean isAbstract;

    private String context;

    private List<Assert> assertions;

    public Rule() {
        assertions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Assert> getAssertions() {
        return assertions;
    }

    public void setAssertions(List<Assert> assertions) {
        this.assertions = assertions;
    }

    public void putAssert(Assert assertion) {
        this.assertions.add(assertion);
    }

    public String toString() {
        String result = "[{\"id\":" + this.id +
                   ",\"abstract\":" + this.isAbstract +
                    ",\"conetxt\":" + this.context + ",";

        String assertString = "";
        for (int i = 0; i < assertions.size(); i++) {
            if (i == assertions.size() - 1) {
                assertString += "\"assert\":" + assertions.get(i).toString();
            } else {
                assertString += "\"assert\":" + assertions.get(i).toString() + ",";
            }
        }

        result += assertString + "}]";
        return result;
    }

    public void setter(Object element, ruleDictionary key) {
        try {
            switch(key) {
                case ID:
                    this.setId((String) element);
                    break;
                case ABSTRACT:
                    this.setAbstract(Boolean.valueOf((String) element));
                    break;
                case CONTEXT:
                    this.setContext((String) element);
                    break;
                case ASSERT:
                    this.putAssert((Assert) element);
                    break;
                default:
                    throw new SchemaException("Error : unknown keyword in rule");
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
    }
}