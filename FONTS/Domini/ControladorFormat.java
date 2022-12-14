package FONTS.Domini;

import java.util.List;

/**
 * Representa el controlador de de format
 * @author Marc Quel
 */
public class ControladorFormat {
    private Format formatejadorTXT;
    private Format formatejadorXML;
    private Format formatejadorJAMP;

    public ControladorFormat() {
        /*
        * Els fitxers .txt han de tenir la seguent estructura:
        * Nom de l'autor (ha de estar tot el nom a la primera  linia)
        *
        * Nom del titol  (ha de estar tot el titol a la segona linia)
        *
        *
        *
        * contingut (tot el que es llegeix despres del titol es considera contingut)
        * */
        this.formatejadorTXT = new FormatTXT();

        /*
         * Els fitxers .xml han de tenir la seguent estructura:
         * <document>
         *      <autor>Nom de l'autor</autor>
         *      <titol>Nom del titol</titol>
         *      <contingut>contingut qualsevol</contingut>
         * </document>
         *
         * L'ordre en el que apareixen els tags autor, titol i contingut no es important
         * */
        this.formatejadorXML = new FormatXML();

        /*
        * Els fitxer .jamp han de tenir la seguent estructura:
        *
        * */
        this.formatejadorJAMP = new FormatJAMP();
    }

    /**
     * Consultora
     * @param direccio es la direccio del fitxer.
     * @param format es el format del fitxer.
     * @return Donat un fitxer i el seu format, retorna l'autor, el titol i el contingut del fitxer
     * si l'estructura del mateix es correcte. En cas contrari fa throw d'exepcions.
     */
    public List<String> extractTitolAutorContingut(String direccio, String format) throws Exception {
        if (format.equals("txt")) {
            try {
                return formatejadorTXT.extractTitolAutorContingut(direccio);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        else if (format.equals("xml")) {
            try {
                return formatejadorXML.extractTitolAutorContingut(direccio);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        else if (format.equals("jamp")) {
            try {
                return formatejadorJAMP.extractTitolAutorContingut(direccio);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        else throw new Exception("Error: format incorrecte");
    }

    /**
     * Consultora
     * @param autor es l'autor del document.
     * @param titol es el titol del document.
     * @param contingut es el contingut del document.
     * @return Donat un autor, un titol i un contingut, retorna un String
     * que es la representacio del document en el format desitjat.
     */
    public String documentToFile(String autor, String titol, String contingut, String format) throws Exception {
        if (format == "txt") {
            try {
                return formatejadorTXT.documentToFile(autor, titol, contingut);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        else if (format == "xml") {
            try {
                return formatejadorXML.documentToFile(autor, titol, contingut);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        else if (format == "jamp") {
            try {
                return formatejadorJAMP.documentToFile(autor, titol, contingut);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        else throw new Exception("Error: format incorrecte");
    }
}
