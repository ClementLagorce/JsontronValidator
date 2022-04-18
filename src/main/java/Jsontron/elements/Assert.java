package Jsontron.elements;

import Jsontron.exception.SchemaException;
import Jsontron.dictionary.assertDictionary;

public class Assert {

    private String id;

    private String test;

    private String message;

    public Assert() {

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

    public String toString() {
        return "[{\"id\":" + this.id +
                ",\"test\":" + this.test +
                ",\"message\":" + this.message + "}]";
    }

    public void setter(Object element, assertDictionary key) {
        try {
            switch(key) {
                case ID:
                    this.setId((String) element);
                    break;
                case TEST:
                    this.setTest((String) element);
                    break;
                case MESSAGE:
                    this.setMessage((String) element);
                    break;
                default:
                    throw new SchemaException("Error : unknown keyword in assert");
            }
        } catch (SchemaException e) {
            e.printStackTrace();
        }
    }
}
