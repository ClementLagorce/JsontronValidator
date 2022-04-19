package jsontron.expressionResolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResolverMain {

    public static void main(String[] args) throws IOException {
        String jsonDataSourceString = readFile("src/main/resources/docEntry.json");
        JsonNode instanceData = JsonLoader.fromString(jsonDataSourceString);

        ResolverUtils.evalXPath("//doc", instanceData);

        String test = "";
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
