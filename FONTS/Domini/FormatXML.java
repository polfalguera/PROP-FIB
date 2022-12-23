package FONTS.Domini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.*;

/**
 * Representa la classe FormatXML.
 * @author Marc Quel.
 */
public class FormatXML implements Format {
    /**
     * Consultora
     * @param direccio es la direccio del fitxer.
     * @return Donat un fitxer .xml retorna l'autor, el titol i el contingut del fitxer
     * si l'estructura del mateix es correcte. En cas contrari fa throw d'exepcions.
     */
    public List<String> extractTitolAutorContingut(String direccio) throws Exception{
        List<String> result = new ArrayList<String>();
        result.add("");result.add("");result.add("");result.add("");
        String path = Paths.get(direccio).toAbsolutePath().toString();
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        StringBuilder fitxer = new StringBuilder();
        char lletra;
        while(br.ready()) {
            lletra = (char)br.read();
            fitxer.append(lletra);
        }
        br.close();
        file.close();

        String c = fitxer.toString();
        // Treu tot els espais en blanc fins <document>
        c = c.replaceFirst("^\\s*", "");

        //Lista de tags que te dues funcionalitats
        // La primera es mirar si en la primera i la ultima iteracio el tag es <document> i </document>
        // La segona es:
        //  - En el cas de buscar un tag de obertura, mirar que no l'hagi troabt ja
        //  - En el cas de buscar un tag de tancament, mirar que el tacncament del tag
        //    actual correspon amb el tancament trobat
        List<String> tags = new ArrayList<>();
        tags.add("<document>");
        tags.add("");
        tags.add("");
        tags.add("");
        tags.add("</document>");

        HashMap<String, String> closingTags = new HashMap<String, String>();
        closingTags.put("<autor>", "</autor>");
        closingTags.put("<titol>", "</titol>");
        closingTags.put("<contingut>", "</contingut>");

        HashMap<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("<autor>", "");
        resultMap.put("<titol>", "");
        resultMap.put("<contingut>", "");

        int tagsIterator = 0;
        int cIterator = 0;
        int listIterator = 0;

        String builtTag = "";

        char currentChar;

        boolean insideTags = false;
        boolean foundContent = false;
        boolean finished = false;
        while (cIterator < c.length() && !finished) {
            currentChar = c.charAt(cIterator);
            // We have to search for a certain open tag (if first tag we can't have anything before itself)
            if (!insideTags) {
                if (Character.compare(currentChar, '<') == 0) {
                    if (builtTag.equals("")) builtTag = builtTag + currentChar;
                    else throw new Exception("Error: fitxer mal estructurat");
                } else if (Character.compare(currentChar, '>') == 0 && (!builtTag.equals("") || tagsIterator == 0)) {
                    builtTag = builtTag + currentChar;
                    if (tagsIterator == 0) {
                        if (tags.contains(builtTag) && tags.get(tagsIterator).equals(builtTag)) {
                            builtTag = "";
                            ++tagsIterator;
                        } else throw new Exception("Error: fitxer mal estructurat");
                    } else {
                        if (!tags.contains(builtTag) && closingTags.containsKey(builtTag)) {
                            tags.set(tagsIterator, builtTag);
                            insideTags = true;
                            builtTag = "";
                        } else throw new Exception("Error: fitxer mal estructurat");
                    }
                } else if (!builtTag.equals("") || tagsIterator == 0) {
                    builtTag = builtTag + currentChar;
                }
            }
            // We have to save the info to the list and search for a certain close tag
            else {
                if (Character.compare(currentChar, '<') == 0) {
                    if (builtTag.equals("")) builtTag = builtTag + currentChar;
                    else throw new Exception("Error: fitxer mal estructurat");
                } else {
                    if (builtTag.equals("")) {
                        if (Character.getNumericValue(currentChar) != -1) foundContent = true;
                        if (foundContent && tagsIterator != (tags.size() - 1)) {
                            String oldResult = resultMap.get(tags.get(tagsIterator));
                            resultMap.put(tags.get(tagsIterator), (oldResult + currentChar));
                            if (Character.compare(currentChar, '\n') == 0) foundContent = false;
                        }
                    } else if (Character.compare(currentChar, '/') == 0) {
                        if (builtTag.equals("<")) builtTag = builtTag + currentChar;
                        else throw new Exception("Error: fitxer mal estructurat");
                    } else if (Character.compare(currentChar, '>') == 0) {
                        builtTag = builtTag + currentChar;
                        if (tagsIterator == (tags.size() - 1)) {
                            if (tags.contains(builtTag) && tags.get(tagsIterator).equals(builtTag)) {
                                finished = true;
                            } else throw new Exception("Error: fitxer mal estructurat");
                        } else {
                            if (builtTag.equals(closingTags.get(tags.get(tagsIterator)))) {
                                if (tagsIterator != (tags.size() - 2)) {
                                    foundContent = false;
                                    insideTags = false;
                                }
                                builtTag = "";
                                ++tagsIterator;
                            } else throw new Exception("Error: fitxer mal estructurat");
                        }
                    } else {
                        builtTag = builtTag + currentChar;
                    }
                }
            }
            ++cIterator;
        }
        if (!finished) throw new Exception("Error: fitxer mal estructurat");
        //From Map to List
        result.set(0, resultMap.get("<autor>"));
        result.set(1, resultMap.get("<titol>"));
        result.set(2, resultMap.get("<contingut>"));
        // Per treure els espais en blanc inicials
        result.set(0, result.get(0).replaceFirst("^\\s*", ""));
        result.set(1, result.get(1).replaceFirst("^\\s*", ""));
        result.set(2, result.get(2).replaceFirst("^\\s*", ""));
        // Per treure els espais en blanc finals
        result.set(0, result.get(0).replaceAll("\\s+$", ""));
        result.set(1, result.get(1).replaceAll("\\s+$", ""));
        result.set(2, result.get(2).replaceAll("\\s+$", ""));

        if (result.get(0).length() == 0) throw new Exception("Error: autor buit");
        if (result.get(1).length() == 0) throw new Exception("Error: títol buit");

        return result;
    }
    /**
     * Consultora
     * @param raw es tot el contingut que conte un fitxer .xml guardat en un String.
     * @return Donat tot el contingut d'un fitxer .xml guardat en un String, null, ja que no
     * cal implementar aquest metode de la interficia Format en aquesta classe
     */
    public List<String> extractTitolAutorContingutDocument(String raw) throws Exception {
        return null;
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
        return ("<document>"+
                    "<autor>"+ autor +"</autor>"+
                    "<titol>"+ titol +"</titol>"+
                    "<contingut>"+ contingut +"</contingut>"+
                "</document>");
    }
}
