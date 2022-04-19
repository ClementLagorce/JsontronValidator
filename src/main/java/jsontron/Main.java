package jsontron;

import jsontron.elements.Schema;
import jsontron.parsers.SchemaParser;
import jsontron.utils.Renderer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        String path = "src/main/resources/docEntrySchema.json";
        String content = readFile(path);
        Schema mySchema = SchemaParser.parseSchema(content);
        System.out.println(Renderer.jsonRenderer(mySchema.toString()));

        String path2 = "src/main/resources/docEntry.json";
        String jsonDataSourceString = readFile(path2);

        String assertion = mySchema.getPatterns().get(0).getRules().get(0).getAssertions().get(0).getTest();
        System.out.println(assertion.substring(1,assertion.length()-1));

        //Object test = JsonPath.parse(jsonDataSourceString).read(assertion.substring(1,assertion.length()-1));
        //System.out.println(test);



        /*Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream("src/main/resources/test.xml"));

        String expression = "/DocumentEntry/doc[docName/@value = 'test1']";

        XPath xPathProcessor = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xPathProcessor.compile(expression);
        NodeList test = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);*/

    }

    public static boolean eval(String test) {
        return false;
    }

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