package Jsontron.dictionary;

public enum ruleDictionary {

    ID("id"),
    ABSTRACT("abstract"),
    CONTEXT("context"),
    ASSERT("assert");

    private String value;
    ruleDictionary(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ruleDictionary getKeyByValue(String value) {
        for (ruleDictionary key : ruleDictionary.values()) {
            if (key.value.equals(value)) {
                return key;
            }
        }
        return null;
    }
}
