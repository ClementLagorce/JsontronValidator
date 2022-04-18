package test;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private String id;
    private boolean isAbstract;
    private String context;
    private List<Assertion> assertions;

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

    public List<Assertion> getAssertions() {
        return assertions;
    }

    public void setAssertion(List<Assertion> assertions) {
        this.assertions = assertions;
    }
}
