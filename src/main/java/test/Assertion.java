package test;

import com.fasterxml.jackson.databind.JsonNode;

public class Assertion {

    private String id;
    private String test;
    private String message;

    public Assertion() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
