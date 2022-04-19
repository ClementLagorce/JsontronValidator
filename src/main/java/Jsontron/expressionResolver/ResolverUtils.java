package Jsontron.expressionResolver;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolverUtils {

    public static void eval2(String xPath, JsonNode instanceData) {
        JsonNode currentNode = instanceData;
        Walker myWalker = new Walker(currentNode);

        String [] arrayTemp = xPath.split("/");
        List<String> splitedXPath = new ArrayList<>();
        boolean isDoubleSlash = false;
        for (int i = 1; i < arrayTemp.length; i++) {
            splitedXPath.add(arrayTemp[i]);
        }

        if (splitedXPath.get(0).equals("")) {
            isDoubleSlash = true;
            splitedXPath.remove(0);
        }

        for (String currentPath : splitedXPath) {
            if (isDoubleSlash) {
                List<JsonNode> test = doubleSlash(instanceData, splitedXPath);
                isDoubleSlash = false;
            } else {
                myWalker.goChild(currentPath);
            }
        }
    }

    public static void eval3(String xPath, JsonNode instanceData) {
        List<Integer> allSlashs = matchIndexes(xPath, "((?<!/)/(?!/))|(//)");

        List<String> words = detectWord(xPath, allSlashs);


        // ranger les éléments de words dans 3 listes :
        // - une liste de double slash
        // - une liste de slash
        // - une liste de nothing

        // faire des tests avec des for sur ces 3 listes pour vérifier que les fonction qui utilisent findParents et tout fonctionnent bien
        String test = "";
    }

    public static List<String> detectWord(String xPath, List<Integer> indexes) {
        List<String> words = new ArrayList<>();
        if (xPath.charAt(0) != '/') {
            words.add(xPath.substring(0, indexes.get(0)));
        }
        for (int i = 0; i < indexes.size(); i++) {
            if (i < indexes.size()-1) {
                words.add(xPath.substring(indexes.get(i), indexes.get(i + 1)));
            } else {
                words.add(xPath.substring(indexes.get(i)));
            }
        }
        return words;
    }

    public static JsonNode slash(JsonNode current, String key) {
        return current.get(key);
    }

    public static List<JsonNode> doubleSlash(JsonNode current, String key) {
        return current.findParents(key);
    }

    public static List<JsonNode> nothing(JsonNode current, String key) {
        return current.findParents(key);
    }

    public static List<Integer> matchIndexes(String xPath, String match) {
        List<Integer> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(xPath);
        while(matcher.find()) {
            result.add(matcher.start());
        }
        return result;
    }

    public static List<JsonNode> doubleSlash(JsonNode jsonNode, List<String> keys) {
        List<JsonNode> firstKeyNode = jsonNode.findParents(keys.get(0));
        List<JsonNode> result = new ArrayList<>();

        for (JsonNode current : firstKeyNode) {
            JsonNode temp = current;
            boolean isValid = true;
            isValid = isNodeValid(current, keys, result);
            if (isValid) {
                result.add(current);
            }
        }
        return result;
    }

    public static boolean isNodeValid(JsonNode current, List<String> keys, List<JsonNode> result) {
        boolean isValid = true;
        JsonNode temp = current;
        List<String> keysNotConsumed = new ArrayList<>();
        for (String key : keys) {
            keysNotConsumed.add(key);
        }
        for (int i = 0; i < keys.size(); i++) {
            String key = keysNotConsumed.get(0);
            if (!temp.isArray()) {
                if (temp.get(keysNotConsumed.get(0)) == null) {
                    isValid = false;
                } else {
                    temp = temp.get(keysNotConsumed.get(0));
                    keysNotConsumed.remove(0);
                }
            } else {
                for (JsonNode arrayNode : temp) {
                    isValid = isNodeValid(arrayNode, keysNotConsumed, result);
                }
            }
        }
        return isValid;
    }
/*
    public static Object eval(String xPath, JsonNode instanceData) {
        String keySpecial = "/@=";
        String xPathTemp = xPath;

        JsonNode currentNode = instanceData;
        Walker myWalker = new Walker(currentNode);
        int commandeEnCours = -2;
        int currentCommande = 0;
        boolean doubleSlashPasser = false;
        List<String> infos = new ArrayList<>();

        for (int i = 0; i < xPathTemp.length(); i++) {
            String currentChar = String.valueOf(xPathTemp.charAt(i));
            String nextChar = "";
            if (i < xPathTemp.length() - 1) {
                nextChar = String.valueOf(xPathTemp.charAt(i + 1));
            }

            if (currentChar.equals(" ")) {
                continue;
            }

            if (currentChar.equals("/")) {
                if (nextChar.equals("/")) {

                } else {
                    myWalker.goChild();
                }
            } else if (currentChar.equals("@")) {

            }

            if (keySpecial.contains(currentChar)) {
                if (!currentChar.equals("=")) {
                    currentNode = ActualiserCurrentNode(currentNode, infos, commandeEnCours, doubleSlashPasser);
                    commandeEnCours = RencontreKeySpecial(currentNode, currentChar, nextChar, commandeEnCours);
                    if (commandeEnCours == 0) {
                        i++;
                    }
                } else {
                    if (commandeEnCours == 0) {
                        doubleSlashPasser = true;
                        i++;
                        commandeEnCours = 4;
                    } else if (commandeEnCours == 1) {
                        commandeEnCours = 5;
                    }
                }
            }

            currentCommande = RencontreKeySpecial(currentNode, currentChar, nextChar, currentCommande);
            infos = CurrentCommande(currentChar, commandeEnCours, currentCommande, infos);
        }

        currentNode = ActualiserCurrentNode(currentNode, infos, commandeEnCours, doubleSlashPasser);
        if (commandeEnCours == 2 | commandeEnCours == 4 | commandeEnCours == 5) {
            if (Objects.isNull(currentNode)) {
                return false;
            } else {
                return true;
            }
        } else if (commandeEnCours == 1) {
            return currentNode.textValue();
        } else if (commandeEnCours == 0) {
            return currentNode;
        } else {
            return null;
        }
    }
*/
/*    private static int RencontreKeySpecial(JsonNode currentNode, String currentChar, String nextChar, int commande) {
        /* 0 = "//" ; 1 = "/" ; 2 = "=" ; 3 = "@" */
  /*      if (currentChar.equals("/") && nextChar.equals("/")) {
            return 0;
        } else if (currentChar.equals("/")) {
            return 1;
        } else if (currentChar.equals("=")) {
            return 2;
        } else if (currentChar.equals("@")) {
            return 3;
        } else if (!currentChar.equals("'")) {
            return -1;
        }

        return commande;
    }

    private static JsonNode ActualiserCurrentNode(JsonNode currentNode, List<String> infos, int commande, boolean doubleSlashPasser) {
        switch (commande) {
            case 0:
                return DoubleSlash(currentNode, infos);
            case 1:
                return SimpleSlash(currentNode, infos, doubleSlashPasser);
            default:
                System.out.println("Erreur");
        }
        return currentNode;
    }

    private static List<String> CurrentCommande(String currentChar, int commandeEnCours, int commande, List<String> infos) {
        if (commande == -1 & !currentChar.equals("'")) {
            String one_infos = infos.get(0);
            one_infos += currentChar;
            infos.set(0, one_infos);
            return infos;
        } else if ((commandeEnCours == 5 | commandeEnCours == 4) & infos.get(1).equals("")) {
            infos.set(1, infos.get(0));
            infos.set(0, "");
            return infos;
        } else if (infos.get(1).equals("")) {
            infos.set(0, "");
        }

        return infos;
    }

    private static void RencontreSimpleSlash(JsonNode currentNode) {

    }

    private static JsonNode SimpleSlash(JsonNode jsonNode, List<String> infos, boolean doubleSlashPasser) {
        List<JsonNode> jsonNode1 = new ArrayList<>();
        jsonNode1.add(jsonNode.get(infos.get(0)));
        return jsonNode1;
    }

    private static JsonNode DoubleSlash(JsonNode jsonNode, List<String> infos) {
        List<JsonNode> one_infos = jsonNode.findParents(infos.get(0));
        return one_infos;
    }
*/
    /*private static JsonNode Equals(JsonNode jsonNode, List<String> infos, int commandeEnCours) {
        JsonNode nodeReturn = null;
        if (commandeEnCours == 5) {
            if (jsonNode.get(infos.get(1)).size() > 1) {
                try {
                    for (int i = 0; i < jsonNode.size(); i++) {
                        if (jsonNode.get(i).get(infos.get(1)).textValue().equals(infos.get(0))) {
                            nodeReturn = jsonNode.get(i).get(infos.get(1));
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Erreur aucune balise ayant le nom " + infos.get(1) + " trouver...");
                }
            } else {
                if (jsonNode.get(infos.get(1)).textValue().equals(infos.get(0))) {
                    nodeReturn = jsonNode.get(infos.get(1));
                }
            }
        } else if (commandeEnCours == 4) {
            List<JsonNode> jsonNodesFind = jsonNode.findParents(infos.get(1));
            for (int i = 0; i < jsonNodesFind.size(); i++) {
                if (jsonNodesFind.get(i).get(infos.get(1)).equals(infos.get(0))) {
                    nodeReturn = jsonNodesFind.get(i);
                }
            }
        }


        return nodeReturn;
    }
*/

}
