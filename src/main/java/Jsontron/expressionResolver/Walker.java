package Jsontron.expressionResolver;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Walker {

    private JsonNode parentNode;

    private JsonNode currentNode;

    private String parentKey;

    private int currentId = 0;

    public Walker(JsonNode parentNode) {
        this.parentNode = parentNode;
        this.currentNode = parentNode.get(currentId);
        this.parentKey = parentNode.fieldNames().next();
    }

    public JsonNode getParentNode() {
        return parentNode;
    }

    public String getParentKey() {
        return parentKey;
    }

    public JsonNode getCurrentNode() {
        return currentNode;
    }

    public void moveNextNode() {
        this.currentId++;
        this.currentNode = this.parentNode.get(this.currentId);
    }

    public void goChild(String key) {
        this.parentNode = this.currentNode;
        this.currentId = 0;
        this.parentKey = this.parentNode.fieldNames().next();
        this.currentNode = this.parentNode.get(key);
    }
}