package FONTS.Domini;

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
        return null;
    }
}
