package jsontron.elements;

import jsontron.exception.SchemaException;
import jsontron.dictionary.schemaDictionary;

import java.util.ArrayList;
import java.util.List;

public class Schema {

    private final String KEYWORD = "schema";
    private String id;
    private String title;
    private String schemaVersion;
    private String defaultPhase;
    private List<Phase> phases;
    private List<Pattern> patterns;

    public Schema() {
        phases = new ArrayList<>();
        patterns = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getDefaultPhase() {
        return defaultPhase;
    }

    public void setDefaultPhase(String defaultPhase) {
        this.defaultPhase = defaultPhase;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPattern(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public void putPhase(Phase phase) {
        this.phases.add(phase);
    }

    public void putPattern(Pattern pattern) {
        this.patterns.add(pattern);
    }

    public String toString() {
        String result = "{\"schema\":{\"id\":" + this.id +
                                 ",\"title\":" + this.title +
                         ",\"schemaVersion\":" + this.schemaVersion +
                          ",\"defaultPhase\":" + this.defaultPhase + ",";

        String phasesString = "";
        for (Phase phase : phases) {
            phasesString += "\"phase\":" + phase.toString() + ",";
        }
        String patternsString = "";
        for (int i = 0; i < patterns.size(); i++) {
            if (i == patterns.size() - 1) {
                patternsString += "\"pattern\":" + patterns.get(i).toString();
            } else {
                patternsString += "\"pattern\":" + patterns.get(i).toString() + ",";
            }
        }
        result += phasesString + patternsString + "}}";

        return result;
    }

    public void setter(Object element, schemaDictionary key) {
        try {
            switch(key) {
                case ID:
                    this.setId((String) element);
                    break;
                case TITLE:
                    this.setTitle((String) element);
                    break;
                case SCHEMA_VERSION:
                    this.setSchemaVersion((String) element);
                    break;
                case DEFAULT_PHASE:
                    this.setDefaultPhase((String) element);
                    break;
                case PHASE:
                    this.putPhase((Phase) element);
                    break;
                case PATTERN:
                    this.putPattern((Pattern) element);
                    break;
                default:
                    throw new SchemaException("Error : unknown keyword in schema");
            }
        } catch (SchemaException e) {

            e.printStackTrace();
        }
    }
}
