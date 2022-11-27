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
        String path = Paths.get("DATA/"+ direccio +".txt").toAbsolutePath().toString();
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
        c = c.replaceFirst("^\\s*", "");
        char currentChar;

        int cIterator = 0;
        int listIterator;

        List<String> tags = new ArrayList<>();
        tags.add("autor:");
        tags.add("titol:");

        String builtTag = "";

        // Utilitzo aquesta variable per no guardar al titol o a l'autor
        // Els espais entre els : i la primera lletra
        boolean firstLetter = false;

        // Look if the first 2 lines are autor: and titol:
        for (listIterator = 0; listIterator < 2; ++listIterator) {
            int counter = 0;
            while (counter < tags.get(listIterator).length() && cIterator < c.length()) {
                currentChar = c.charAt(cIterator);
                boolean whiteSpace = (Character.compare(currentChar, '\n') == 0 || Character.compare(currentChar, ' ') == 0);
                if (!whiteSpace) firstLetter = true;
                if (!whiteSpace || firstLetter) {
                    builtTag = builtTag + currentChar;
                    ++counter;
                }
                ++cIterator;
            }
            firstLetter = false;
            if (builtTag.equals(tags.get(listIterator))) {
                currentChar = c.charAt(cIterator);
                while(currentChar != '\n' && cIterator < c.length()) {
                    if (Character.compare(currentChar, ' ') != 0) firstLetter = true;
                    if (firstLetter || Character.compare(currentChar, ' ') != 0) {
                        String oldResult = result.get(listIterator);
                        result.set(listIterator, oldResult + currentChar);
                    }

                    ++cIterator;
                    currentChar = c.charAt(cIterator);
                }
                firstLetter = false;
                ++cIterator;
                builtTag = "";
            } else throw new Exception("Error, fitxar mal estructurat");
        }

        while (cIterator < c.length()) {
            currentChar = c.charAt(cIterator);
            String oldResult = result.get(listIterator);
            result.set(listIterator, oldResult + currentChar);

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
     * que es la representacio del document en .txt.
     */
    public String documentToFile(String autor, String titol, String contingut) throws Exception{
        return (("autor:" + autor + '\n') + ("titol:" + titol + '\n') + contingut);
    }
}
