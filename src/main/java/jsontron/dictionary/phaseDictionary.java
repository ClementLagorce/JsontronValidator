package jsontron.dictionary;

public enum phaseDictionary {

    ID("id"),
    ACTIVE("active");

    private String value;

    phaseDictionary(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static phaseDictionary getKeyByValue(String value) {
        for (phaseDictionary key : phaseDictionary.values()) {
            if (key.value.equals(value)) {
                return key;
            }
        }
        return null;
    }
}
