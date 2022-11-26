package FONTS.Domini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.*;

public class FormatXML implements Format {
    /**
     * Consultora
     * @param direccio es la direccio del fitxer.
     * @return Donat un fitxer .xml retorna l'autor, el titol i el contingut del fitxer
     * si l'estructura del mateix es correcte. En cas contrari fa throw d'exepcions.
     */
    public List<String> extractTitolAutorContingut(String direccio) throws Exception{
        String path = Paths.get("DATA/"+ direccio +".xml").toAbsolutePath().toString();
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        StringBuilder fitxer = new StringBuilder();
        char lletra;
        while(br.ready()) {
            lletra = (char)br.read();
            fitxer.append(lletra);
        }
        String contingutFitxer = fitxer.toString();
        String[] tags =
                {"<document>",
                    "<autor>", "</autor>",
                    "<titol>", "</titol>",
                    "<contingut>", "</contingut>",
                "</document"};
        Boolean[] tagsTrobats = new Boolean[8];
        int tagPointer = 0;
        /*
        * Algorisme:
        * Tenir un vector de booleans, on cada boolea representa si he trobat el tag en questio.
        * Vaig posant a cert cada posicio del vector al trobar el tag
        *
        * Tota l'estona, si trobo <!--, tan me fa el que vingui despres,  he de buscar trobar -->.
        *
        * Tota l'estona que vaig llegint i estic fora de comentaris, si construeixo un tag que
        * es diferent al que he de trobar => exepcio
        *
        * Vaig llegint fins a construir <document>. Si no ho trobo => exepcio
        *
        * Vaig llegint fins a trobar <autor>. Si no ho trobo => exepcio
        *
        * Vaig llegint fins a trobat </autor>. Si no ho trobo => exepcio. Tot el que hi ha entre
        *  <autor> i </autor> ho guardo a la primera posicio del List<String>
        *
        * Vaig llegint fins a trobar <titol>. Si no ho trobo => exepcio
        *
        * Vaig llegint fins a trobat </titol>. Si no ho trobo => exepcio. Tot el que hi ha entre
        * <titol> i </titol> ho guardo a la segona posicio del List<String>
        *
        * Vaig llegint fins a trobar <contingut>. Si no ho trobo => exepcio
        *
        * Vaig llegint fins a trobat </contingut>. Si no ho trobo => exepcio. Tot el que hi ha entre
        * <contingut> i </contingut> ho guardo a la tercera posicio del List<String>
        *
        * Vaig llegint fins a trobar </document>. Si no ho trobo => exepcio
        * */
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
    public String documentToFile(String autor, String titol, String contingut) throws Exception{
        return null;
    }
}
