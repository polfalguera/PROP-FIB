package FONTS.Domini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la classe FormatJAMP.
 * @author Marc Quel.
 */
public class FormatJAMP implements Format {
    /**
     * Consultora
     * @param direccio es la direccio del fitxer.
     * @return Donat un fitxer .jamp retorna l'autor, el titol i el contingut del fitxer
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


        int cIterator = 0;

        String builtTag = "";

        char currentChar;


        boolean autorFinished = false;
        boolean titolFinished = false;
        boolean contingutFound = false;
        boolean contingutFinished = false;
        boolean readData = false;
        boolean firstLetterRead = false;
        int listIterator = 0;
        currentChar = c.charAt(0);
        // Treu tot els espais en blanc fins {
        c = c.replaceFirst("^\\s*", "");
        if (!(c.length() > 1) || Character.compare(currentChar, '{') != 0)
            throw new Exception("Error: fitxer mal estructurat");
        c = c.substring(1);
        c = c.replaceFirst("^\\s*", "");
        while (cIterator < c.length() && (!autorFinished || !titolFinished || !contingutFound)) {
            currentChar = c.charAt(cIterator);
            if (!readData) {
                if (((Character.compare(currentChar, 'a') == 0 && listIterator == 0) ||
                        ((Character.compare(currentChar, 't') == 0 && listIterator == 1)) ||
                        ((Character.compare(currentChar, 'c') == 0 && listIterator == 2))) && !firstLetterRead ) {
                    if (builtTag.equals("")) {
                        builtTag = builtTag + currentChar;
                        firstLetterRead = true;
                    }
                    else throw new Exception("Error: fitxer mal estructurat");
                } else if (Character.compare(currentChar, ':') == 0 && !builtTag.equals("")) {
                    if ((builtTag.equals("autor") && listIterator == 0) ||
                            (builtTag.equals("titol") && listIterator == 1) ||
                            (builtTag.equals("contingut") && listIterator == 2)) {
                        if (c.length() - 1 >= cIterator + 1) {
                            c = c.substring(cIterator + 1);
                            c = c.replaceFirst("^\\s*", "");
                            cIterator = -1;
                            builtTag = "";
                            readData = true;
                            firstLetterRead = false;
                            if (listIterator == 2) contingutFound = true;
                        } else throw new Exception("Error: fitxer mal estructurat");
                    } else throw new Exception("Error: fitxer mal estructurat");

                } else {
                    builtTag = builtTag + currentChar;
                }
            } else {
                if (Character.compare(currentChar, '"') == 0 && builtTag.equals("")) {
                    builtTag = builtTag + currentChar;
                } else if (Character.compare(currentChar, '"') == 0) {
                    if (c.length() - 1 >= cIterator + 1) {
                        String aux = c.substring(cIterator + 1);
                        aux = aux.replaceFirst("^\\s*", "");
                        if (aux.length() > 0 && (Character.compare(aux.charAt(0), ',') == 0)) {
                            if (c.length() - 1 >= cIterator + 2) {
                                c = c.substring(cIterator + 2);
                                c = c.replaceFirst("^\\s*", "");
                                builtTag = "";
                                readData = false;
                                if (listIterator == 0) autorFinished = true;
                                else if (listIterator == 1) titolFinished = true;
                                ++listIterator;
                                cIterator = -1;
                            } else throw new Exception("Error: fitxer mal estructurat");

                        } else throw new Exception("Error: fitxer mal estructurat");
                    } else throw new Exception("Error: fitxer mal estructurat");
                } else {
                    String oldResult = result.get(listIterator);
                    result.set(listIterator, oldResult + currentChar);
                }
            }
            ++cIterator;
        }

        c = c.replaceFirst("^\\s*", "");
        if (!(c.length() > 1) || Character.compare(c.charAt(0), '{') != 0)
            throw new Exception("Error: fitxer mal estructurat");
        c = c.substring(1);
        c = c.replaceFirst("^\\s*", "");

        cIterator = 0;
        firstLetterRead = false;
        readData = false;
        builtTag = "";

        while (cIterator < c.length() && (!contingutFinished)) {
            currentChar = c.charAt(cIterator);
            if (!readData) {
                if (((Character.compare(currentChar, 'p') == 0)) && !firstLetterRead ) {
                    if (builtTag.equals("")) {
                        builtTag = builtTag + currentChar;
                        firstLetterRead = true;
                    }
                    else throw new Exception("Error: fitxer mal estructurat");
                } else if (Character.compare(currentChar, ':') == 0 && !builtTag.equals("")) {
                    if ((builtTag.equals("paragraf"))) {
                        if (c.length() - 1 >= cIterator + 1) {
                            c = c.substring(cIterator + 1);
                            c = c.replaceFirst("^\\s*", "");
                            cIterator = -1;
                            builtTag = "";
                            readData = true;
                            firstLetterRead = false;
                        } else throw new Exception("Error: fitxer mal estructurat");
                    } else throw new Exception("Error: fitxer mal estructurat");

                } else {
                    builtTag = builtTag + currentChar;
                }
            } else {
                if (Character.compare(currentChar, '"') == 0 && builtTag.equals("")) {
                    builtTag = builtTag + currentChar;
                } else if (Character.compare(currentChar, '"') == 0) {
                    if (c.length() - 1 >= cIterator + 1) {
                        String aux = c.substring(cIterator + 1);
                        aux = aux.replaceFirst("^\\s*", "");
                        if (aux.length() > 0 && (Character.compare(aux.charAt(0), ',') == 0)) {
                            if (c.length() - 1 >= cIterator + 2) {
                                c = c.substring(cIterator + 2);
                                c = c.replaceFirst("^\\s*", "");
                                builtTag = "";
                                readData = false;
                                cIterator = -1;
                                String oldResult = result.get(listIterator);
                                result.set(listIterator, oldResult + '\n');
                                if (c.length() == 0) throw new Exception("Error: fitxer mal estructurat");
                                if ((Character.compare(c.charAt(0), '}') == 0)) {
                                    contingutFinished = true;
                                }
                            } else throw new Exception("Error: fitxer mal estructurat");

                        } else throw new Exception("Error: fitxer mal estructurat");
                    } else throw new Exception("Error: fitxer mal estructurat");
                } else {
                    String oldResult = result.get(listIterator);
                    result.set(listIterator, oldResult + currentChar);
                }
            }
            ++cIterator;
        }
        if (c.length() > 1) {
            String aux = c;

            aux = aux.substring(1);

            aux = aux.replaceFirst("^\\s*", "");

            if (aux.length() == 0) throw new Exception("Error: fitxer mal estructurat");
            if ((Character.compare(aux.charAt(0), '}') != 0)) throw new Exception("Error: fitxer mal estructurat");
            else {
                if (aux.length() > 1) {
                    aux = aux.substring(1);
                    aux = aux.replaceFirst("^\\s*", "");
                    if (aux.length() > 0) throw new Exception("Error: fitxer mal estructurat");
                }
            }
        } else throw new Exception("Error: fitxer mal estructurat");

        if (!autorFinished || !titolFinished || !contingutFound || !contingutFinished)
            throw new Exception("Error: fitxer mal estructurat");
        // Per treure els espais en blanc inicials
        result.set(0, result.get(0).replaceFirst("^\\s*", ""));
        result.set(1, result.get(1).replaceFirst("^\\s*", ""));
        result.set(2, result.get(2).replaceFirst("^\\s*", ""));
        // Per treure els espais en blanc finals
        result.set(0, result.get(0).replaceAll("\\s+$", ""));
        result.set(1, result.get(1).replaceAll("\\s+$", ""));
        result.set(2, result.get(2).replaceAll("\\s+$", ""));

        if (result.get(0).length() == 0) throw new Exception("Error: autor buit");
        if (result.get(1).length() == 0) throw new Exception("Error: t??tol buit");

        return result;
    }
    /**
     * Consultora
     * @param raw es tot el contingut que conte un fitxer .jamp guardat en un String.
     * @return Donat tot el contingut d'un fitxer .jamp guardat en un String, null, ja que no
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
     * que es la representacio del document en .jamp.
     */
    public String documentToFile(String autor, String titol, String contingut) throws Exception{
        return "{\n" +
                "autor: " + '"' +autor + '"' + ',' + "\n"+
                "titol: " + '"' +titol + '"' + ',' + "\n"+
                "contingut: " + "{" + "\n"+
                "paragraf: " + '"' +contingut + '"' + ',' + "\n"+
                "}\n" +
                "}";
    }
}
