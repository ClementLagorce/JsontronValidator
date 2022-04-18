package test;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Report {

    private static List<Map<String, Object>> errors;
    private static List<Map<String, Object>> warnings;
    private static List<Map<String, Object>> validations;
    private static List<Map<String, Object>> finalValidationReport;
    private static boolean valid;

    private static Logger LOG = LoggerFactory.getLogger(Report.class);

    public Report() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
        validations = new ArrayList<>();
        finalValidationReport = new ArrayList<>();
        valid = false;
    }

    public static void addError(JsonNode instance, Object schema, String attr, String msg, Object detail) {
        Map<String, Object> error = new TreeMap<>();
        error.put("schInstance", instance);
        error.put("schema", schema);
        error.put("attribute", attr);
        error.put("message", msg);
        error.put("detail", detail);
        errors.add(error);
    }

    public static void addWarning(JsonNode instance, Object schema, String attr, String msg, Object detail) {
        Map<String, Object> warning = new TreeMap<>();
        warning.put("schInstance", instance);
        warning.put("schema", schema);
        warning.put("attribute", attr);
        warning.put("message", msg);
        warning.put("detail", detail);
        warnings.add(warning);
    }

    public static void addvalidation(String rule, String context, String assertionid, String test, String msg, boolean result) {
        Map<String, Object> validation = new TreeMap<>();
        validation.put("schRule", rule);
        validation.put("ruleContext", context);
        validation.put("assertionid", assertionid);
        validation.put("assertionTest", test);
        validation.put("message", msg);
        validation.put("assertionValid", result);
        validations.add(validation);
    }

    public static void addvalidationSchemaInstance(JsonNode instance, Object schema) {
        Map<String, Object> validation = new TreeMap<>();
        validation.put("schInstance", instance);
        validation.put("schema", schema);
        validations.add(validation);
    }

    public static void setFinalValidationReport(List<Map<String, Object>> errorList, List<Map<String, Object>> validationList) {
        List<Map<String, Object>> validationFailures = new ArrayList<>();
        try {
            if (validationList != null && !validationList.isEmpty()) {
                for (Map<String, Object> valElement : validationList) {
                    if(!((boolean) valElement.get("assertionValid"))) {
                        validationFailures.add(valElement);
                    }
                }
            }
            validationFailures.addAll(errorList);
            finalValidationReport = validationFailures;
        } catch (Exception e) {
            LOG.error(" ERROR IN VALIDATION REPORTING: " + e.getMessage());
        }
    }
}
