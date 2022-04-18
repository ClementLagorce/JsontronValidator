package Jsontron.expressionResolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ResolverMain {

    private static JsonNode instanceData;

    public static void main(String[] args) throws IOException {
        String jsonDataSourceString = readFile("src/main/resources/docEntry.json");
        instanceData = JsonLoader.fromString(jsonDataSourceString);

        // Doit retourner la String “file”
        String xPathTest1 = "/DocumentEntry//doc/docName = 'test1'";

        // Doit retourner vrai
        String xPathTest2 = "/DocumentEntry/id = 'file'";

        // Doit retourner faux
        String xPathTest3 = "//docName = 'test1'";

        //
        String xPathTest4 = "/DocumentEntry/doc[docName = 'test1']";

        String xPathTest5 = "/DocumentEntry/id";

        Walker myWalker = new Walker(instanceData);
        ResolverUtils.eval2("//docName", instanceData);


        //Object result = ResolverUtils.eval(xPathTest1, instanceData);
        //System.out.println(result);
    }

    /**
     * Function to read a file in local
     *
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