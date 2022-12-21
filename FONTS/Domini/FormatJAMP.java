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
            throw new Exception("Error 1: fitxer mal estructurat");
        c = c.substring(1);
        c = c.replaceFirst("^\\s*", "");
        System.out.println(c);
        while (cIterator < c.length() && (!autorFinished || !titolFinished || !contingutFound)) {
            currentChar = c.charAt(cIterator);
            System.out.println(builtTag);
            System.out.println(c);
            if (!readData) {
                if (((Character.compare(currentChar, 'a') == 0 && listIterator == 0) ||
                        ((Character.compare(currentChar, 't') == 0 && listIterator == 1)) ||
                        ((Character.compare(currentChar, 'c') == 0 && listIterator == 2))) && !firstLetterRead ) {
                    if (builtTag.equals("")) {
                        builtTag = builtTag + currentChar;
                        firstLetterRead = true;
                    }
                    else throw new Exception("Error 2: fitxer mal estructurat");
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
                            System.out.println(listIterator);
                            if (listIterator == 2) contingutFound = true;
                        } else throw new Exception("Error 6: fitxer mal estructurat");
                    } else throw new Exception("Error 3: fitxer mal estructurat");

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
                        if ((Character.compare(aux.charAt(0), ',') == 0)) {
                            if (c.length() - 1 >= cIterator + 2) {
                                c = c.substring(cIterator + 2);
                                c = c.replaceFirst("^\\s*", "");
                                builtTag = "";
                                readData = false;
                                if (listIterator == 0) autorFinished = true;
                                else if (listIterator == 1) titolFinished = true;
                                ++listIterator;
                                cIterator = -1;
                            } else throw new Exception("Error 6: fitxer mal estructurat");

                        } else throw new Exception("Error 4: fitxer mal estructurat");
                    } else throw new Exception("Error 5: fitxer mal estructurat");
                } else {
                    String oldResult = result.get(listIterator);
                    result.set(listIterator, oldResult + currentChar);
                }
            }
            ++cIterator;
        }
        System.out.println("Contngut de c: " + c);

        c = c.replaceFirst("^\\s*", "");
        if (!(c.length() > 1) || Character.compare(c.charAt(0), '{') != 0)
            throw new Exception("Error 7: fitxer mal estructurat");
        c = c.substring(1);
        c = c.replaceFirst("^\\s*", "");

        cIterator = 0;
        firstLetterRead = false;
        readData = false;
        builtTag = "";

        while (cIterator < c.length() && (!contingutFinished)) {
            currentChar = c.charAt(cIterator);
            System.out.println(builtTag);
            System.out.println(c);
            if (!readData) {
                if (((Character.compare(currentChar, 'p') == 0)) && !firstLetterRead ) {
                    if (builtTag.equals("")) {
                        builtTag = builtTag + currentChar;
                        firstLetterRead = true;
                    }
                    else throw new Exception("Error 9: fitxer mal estructurat");
                } else if (Character.compare(currentChar, ':') == 0 && !builtTag.equals("")) {
                    if ((builtTag.equals("paragraf"))) {
                        if (c.length() - 1 >= cIterator + 1) {
                            c = c.substring(cIterator + 1);
                            c = c.replaceFirst("^\\s*", "");
                            cIterator = -1;
                            builtTag = "";
                            readData = true;
                            firstLetterRead = false;
                            System.out.println(listIterator);
                        } else throw new Exception("Error 10: fitxer mal estructurat");
                    } else throw new Exception("Error 11: fitxer mal estructurat");

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
                        if ((Character.compare(aux.charAt(0), ',') == 0)) {
                            if (c.length() - 1 >= cIterator + 2) {
                                c = c.substring(cIterator + 2);
                                c = c.replaceFirst("^\\s*", "");
                                builtTag = "";
                                readData = false;
                                cIterator = -1;
                                String oldResult = result.get(listIterator);
                                result.set(listIterator, oldResult + '\n');
                            } else throw new Exception("Error 12: fitxer mal estructurat");

                        }
                        else if ((Character.compare(aux.charAt(0), '}') == 0)) {
                            //
                            contingutFinished = true;
                        }
                        else throw new Exception("Error 13: fitxer mal estructurat");
                    } else throw new Exception("Error 14: fitxer mal estructurat");
                } else {
                    String oldResult = result.get(listIterator);
                    result.set(listIterator, oldResult + currentChar);
                }
            }
            ++cIterator;
        }
        if (c.length() - 1 >= cIterator + 1) {
            String aux = c.substring(cIterator + 1);
            aux = aux.replaceFirst("^\\s*", "");
            if ((Character.compare(aux.charAt(0), '}') != 0)) throw new Exception("Error 15: fitxer mal estructurat");
        } else throw new Exception("Error 16: fitxer mal estructurat");

        if (!autorFinished || !titolFinished || !contingutFound || !contingutFinished)
            throw new Exception("Error 17: fitxer mal estructurat");
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
                "autor: " + '"' +autor + '"' + "\n"+
                "titol: " + '"' +titol + '"' + "\n"+
                "contingut: " + "{" + "\n"+
                "paragraf: " + '"' +contingut + '"' + "\n"+
                "}\n" +
                "}";
    }
}
