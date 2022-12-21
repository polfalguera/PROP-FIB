package FONTS.Domini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.*;

/**
 * Representa la classe FormatTXT.
 * @author Marc Quel.
 */
public class FormatTXT implements Format {
    /**
     * Consultora
     * @param direccio es la direccio del fitxer.
     * @return Donat un fitxer .txt retorna l'autor, el titol i el contingut del fitxer
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
        //Treu tots els espais en blanc fins a 'autor:'
        //c = c.replaceFirst("^\\s*", "");

        char currentChar;
        boolean breakFound = false;
        int cIterator = 0;
        int listIterator;
        //Guardem autor i titol
        for (listIterator = 0; listIterator < 2; ++listIterator) {
            while (!breakFound && cIterator < c.length()) {
                currentChar = c.charAt(cIterator);
                if (Character.compare(currentChar, '\n') == 0) breakFound = true;
                else {
                    String oldResult = result.get(listIterator);
                    result.set(listIterator, oldResult + currentChar);
                }
                ++cIterator;
            }
            breakFound = false;
        }
        //Guardem contingut
        while (cIterator < c.length()) {
            currentChar = c.charAt(cIterator);
            String oldResult = result.get(listIterator);
            result.set(listIterator, oldResult + currentChar);
            ++cIterator;
        }
        // Per treure els espais en blanc finals
        result.set(0, result.get(0).replaceAll("\\s+$", ""));
        result.set(1, result.get(1).replaceAll("\\s+$", ""));
        result.set(2, result.get(2).replaceAll("\\s+$", ""));

        if (result.get(0).length() == 0) throw new Exception("Error: autor buit");
        if (result.get(1).length() == 0) throw new Exception("Error: títol buit");
        return result;
    }

    public List<String> extractTitolAutorContingutDocument(String raw) throws Exception {
        List<String> result = new ArrayList<String>();
        result.add("");result.add("");result.add("");result.add("");
        String c = raw;
        //Treu tots els espais en blanc fins a 'autor:'
        //c = c.replaceFirst("^\\s*", "");

        char currentChar;
        boolean breakFound = false;
        int cIterator = 0;
        int listIterator;
        //Guardem autor i titol
        for (listIterator = 0; listIterator < 2; ++listIterator) {
            while (!breakFound && cIterator < c.length()) {
                currentChar = c.charAt(cIterator);
                if (Character.compare(currentChar, '\n') == 0) breakFound = true;
                else {
                    String oldResult = result.get(listIterator);
                    result.set(listIterator, oldResult + currentChar);
                }
                ++cIterator;
            }
            breakFound = false;
        }
        //Guardem contingut
        while (cIterator < c.length()) {
            currentChar = c.charAt(cIterator);
            String oldResult = result.get(listIterator);
            result.set(listIterator, oldResult + currentChar);
            ++cIterator;
        }
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
     * @param autor es l'autor del document.
     * @param titol es el titol del document.
     * @param contingut es el contingut del document.
     * @return Donat un autor, un titol i un contingut, retorna un String
     * que es la representacio del document en .txt.
     */
    public String documentToFile(String autor, String titol, String contingut) throws Exception{
        return ((autor + '\n') + (titol + '\n') + contingut);
    }
}
