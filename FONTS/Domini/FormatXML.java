package FONTS.Domini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

public class FormatXML implements Format {
    /**
     * Consultora
     * @param direccio es la direccio del fitxer.
     * @return Donat un fitxer .xml retorna l'autor, el titol i el contingut del fitxer
     * si l'estructura del mateix es correcte. En cas contrari fa throw d'exepcions.
     */
    public List<String> extractTitolAutorContingut(String direccio) throws Exception{
        List<String> result = new ArrayList<String>();
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        String path = Paths.get("DATA/"+ direccio +".xml").toAbsolutePath().toString();
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        StringBuilder fitxer = new StringBuilder();
        char lletra;
        while(br.ready()) {
            lletra = (char)br.read();
            fitxer.append(lletra);
        }
        String c = fitxer.toString();

        List<String> tags = new ArrayList<>();
        tags.add("<document>");
        tags.add("<autor>");
        tags.add("</autor>");
        tags.add("<titol>");
        tags.add("</titol>");
        tags.add("<contingut>");
        tags.add("</contingut>");
        tags.add("</document>");

        Boolean[] tagsTrobats = new Boolean[8];
        Arrays.fill(tagsTrobats, Boolean.FALSE);
        int tagsIterator = 0;
        int cIterator = 0;
        int listIterator = 0;

        String builtTag = "";

        char currentChar;

        boolean insideTags = false;
        boolean foundContent = false;

        while (cIterator < c.length()) {
            currentChar = c.charAt(cIterator);
            // We have to search for a certain open tag (if first tag we can't have anything before itself)
            if (!insideTags) {
                if (Character.compare(currentChar, '<') == 0) {
                    if (builtTag.equals("")) builtTag = builtTag + currentChar;
                    else throw new Exception("Error, fitxar mal estructurat");
                }
                else if (Character.compare(currentChar, '>') == 0 && (!builtTag.equals("") || tagsIterator == 0)) {
                    builtTag = builtTag + currentChar;
                    if (tags.contains(builtTag) && tags.get(tagsIterator).equals(builtTag)) {
                        if (tagsTrobats[0]) insideTags = true;
                        tagsTrobats[tagsIterator] = true;
                        builtTag = "";
                        ++tagsIterator;
                    } else throw new Exception("Error, fitxar mal estructurat");
                } else if (!builtTag.equals("") || tagsIterator == 0) {
                    builtTag = builtTag + currentChar;
                }
            }
            // We have to save the info to the list and search for a certain close tag
            else {
                if (Character.compare(currentChar, '<') == 0) {
                    if (builtTag.equals("")) builtTag = builtTag + currentChar;
                    else throw new Exception("Error, fitxar mal estructurat");
                } else {
                    if (builtTag.equals("")) {
                        if (Character.getNumericValue(currentChar) != -1) foundContent = true;
                        if (foundContent) {
                            String oldResult = result.get(listIterator);
                            result.remove(listIterator);
                            result.add(listIterator, oldResult + currentChar);
                            if (Character.compare(currentChar, '\n') == 0) foundContent = false;
                        }
                    } else if (Character.compare(currentChar, '/') == 0) {
                        if (builtTag.equals("<")) builtTag = builtTag + currentChar;
                        else throw new Exception("Error, fitxar mal estructurat");
                    } else if (Character.compare(currentChar, '>') == 0) {
                        builtTag = builtTag + currentChar;
                        if (tags.contains(builtTag) && tags.get(tagsIterator).equals(builtTag)) {
                            if (!tagsTrobats[6]) {
                                insideTags = false;
                                foundContent = false;
                            }
                            tagsTrobats[tagsIterator] = true;
                            builtTag = "";
                            ++tagsIterator;
                            ++listIterator;
                        } else throw new Exception("Error, fitxar mal estructurat");
                    } else {
                        builtTag = builtTag + currentChar;
                    }
                }
            }
            ++cIterator;
        }
        return result;
    }

    /**
     * Consultora
     * @param autor es l'autor del document.
     * @param titol es el titol del document.
     * @param contingut es el contingut del document.
     * @return Donat un autor, un titol i un contingut, retorna un String
     * que es la representacio del document en .xml.
     */
    public String documentToFile(String autor, String titol, String contingut) throws Exception {
        return "<document>"+
                    "<autor>"+ autor +"</autor>"+
                    "<titol>"+ titol +"</titol>"+
                    "<contingut>"+ contingut +"</contingut>"+
                "</document>";
    }
}
