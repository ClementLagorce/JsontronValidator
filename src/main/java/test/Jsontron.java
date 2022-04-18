package test;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jsontron {

    private static Report myReport = new Report();
    private static JsonNode instanceData;
    private static JsonNode instanceSchema;

    public static void main(String[] args) throws IOException {
        /*Predicate expensivePredicate = context -> Float.parseFloat(context.item(Map.class)
                .get("price")
                .toString()) > 20.00;*/
        String jsonDataSourceString = readFile("src/main/resources/docEntry.json");
        instanceData = JsonLoader.fromString(jsonDataSourceString);

        String jsonSchemaSourceString = readFile("src/main/resources/docEntrySchema.json");
        instanceSchema = JsonLoader.fromString(jsonSchemaSourceString);

        /*Object expensive = JsonPath.parse(jsonDataSourceString).read("[?($.entry.length() == 8)]");
        Object nbComposittion = JsonPath.parse(jsonDataSourceString).read("$.entry[?(@.resource.resourceType == 'Composition')]");
        System.out.println(nbComposittion);*/
    }

    public static boolean eval(String myTest) {
        return true;
    }

    public static Result parseAssert(Assertion assertion) {
        Result res = new Result();
        try {
            String myTest = assertion.getTest();
            boolean asrtResult = eval(myTest);

            res.setId(assertion.getId());
            res.setAsrtResult(asrtResult);
            res.setMessage(assertion.getMessage());
        } catch (Exception e) {
            myReport.addError(instanceData, assertion, "Assert", "Error in Assert Parsing " + e.getMessage(), e.getStackTrace());
        }
        return res;
    }

    public static String validateAssert(Result parsedAssert) {
        String assrtMsg = "";
        try {
            assrtMsg = parsedAssert.isAsrtResult() ? "successful" : parsedAssert.getMessage();
        } catch (Exception e) {
            myReport.addError(instanceData, parsedAssert, "Assert", "Error in Assert Validation " + e.getMessage(), e.getStackTrace());
        }
        return assrtMsg;
    }

    public static List<Assertion> parseRule(Rule rule) {
        List<Assertion> asserts = new ArrayList<>();

        try {
            asserts = rule.getAssertions();
            if (asserts.size() < 1) {
                myReport.addWarning(instanceData, rule, "Rule", "Invalid Rule", "Rule doesn't have any assertions defined.");
            }
        } catch (Exception e) {
            myReport.addError(instanceData, rule, "Rule", "Error in Rule Parsing "+e.getMessage(), e.getStackTrace());
        }
        return asserts;
    }

    public static void validateRule (Rule rule) {
        try {
            String ruleParsedContext = rule.getContext();

        } catch (Exception e) {

        }
    }


    /*
var  validateRule = function(instance, rule){

	// first parse the context
	try {

		ruleParsedContext = jp.query(instance, rule.context);


		if(ruleParsedContext && Array.isArray(ruleParsedContext) && ruleParsedContext.length == 0){

			myReport.addWarning(instance, rule, "Rule Context", "Possible issue in Rule Validation: Empty Context NodeSet for rule.", "The context statement didnot return any nodes - Check Rule Context statement");
			return;

		}
		else if(ruleParsedContext && Array.isArray(ruleParsedContext) && ruleParsedContext.length > 0){

			ruleParsedContextNodeSet = ruleParsedContext; // all the asserts are in the first element of parsedContext nodeset


			if(ruleParsedContextNodeSet && Array.isArray(ruleParsedContextNodeSet) && ruleParsedContextNodeSet.length > 0){

				ruleParsedContextNodeSet.forEach(function(nsElement){


					contextNode =[];
					contextNode.push(nsElement);


				// get all asserts for the rule
					let asrts = [];
					asrts = parseRule(rule);

					//parse and validate all asserts
					if(Array.isArray(asrts) && asrts.length > 0 && asrts[0] !=''){
						asrts.forEach(function(element){

			;
							try{

								let parAssert = parseAssert(element);
								let valAssert = validateAssert(parAssert);
								myReport.addValidation(rule, contextNode, parAssert.id, element.test, valAssert,parAssert.result);

							}catch(e){

									myReport.addError(instance, rule, "Rule", "Error in Rule Validation "+e.message, e.stack);

									}
						})

					}

					else{

						myReport.addError(instance, rule, "Rule Context", "Error in Rule Validation: Invalid Context "+e.message, e.stack);

					}

				})

			}else{myReport.addError(instance, rule, "Rule Context", "Error in Rule Validation: Invalid Context Node ", "The context node set doesn't contain valid node - Check Rule Context");}

		}else{

			myReport.addError(instance, rule, "Rule Context", "Error in Rule Validation: Invalid Context NodeSet", "The context node set is not valid - Check Rule Context statement");

		}

	}catch(e){

		myReport.addError(instance, rule, "Rule", "Error in Rule Validation "+e.message, e.stack);

	}
     */

    /**
     * Function to read a file in local
     * @param fileName : The file path/name
     * @return String : The content of the file
     */
    public static String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        }
    }
}