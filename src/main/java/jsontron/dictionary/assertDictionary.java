package jsontron.dictionary;

public enum assertDictionary {

    ID("id"),
    TEST("test"),
    MESSAGE("message");

    private String value;
    assertDictionary(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static assertDictionary getKeyByValue(String value) {
        for (assertDictionary key : assertDictionary.values()) {
            if (key.value.equals(value)) {
                return key;
            }
        }
        return null;
    }
}
