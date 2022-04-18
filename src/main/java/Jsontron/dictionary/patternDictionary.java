package Jsontron.dictionary;

public enum patternDictionary {

    ID("id"),
    TITLE("title"),
    RULE("rule");

    private String value;
    patternDictionary(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static patternDictionary getKeyByValue(String value) {
        for (patternDictionary key : patternDictionary.values()) {
            if (key.value.equals(value)) {
                return key;
            }
        }
        return null;
    }
}
