package jsontron.dictionary;

public enum schemaDictionary {
    ID("id"),
    TITLE("title"),
    SCHEMA_VERSION("schemaVersion"),
    DEFAULT_PHASE("defaultPhase"),
    PHASE("phase"),
    PATTERN("pattern");

    public String value;

    schemaDictionary(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static schemaDictionary getKeyByValue(String value) {
        for (schemaDictionary key : schemaDictionary.values()) {
            if (key.value.equals(value)) {
                return key;
            }
        }
        return null;
    }
}
