package FONTS.Domini;

import java.util.List;

/**
 * Representa el controlador de de format
 * @author Marc Quel
 */
public class ControladorFormat {
    /**
     * Representa el formatejador de fitxers .txt
     */
    private Format formatejadorTXT;
    /**
     * Representa el formatejador de fitxers .xml
     */
    private Format formatejadorXML;
    /**
     * Representa el formatejador de fitxers .jamp
     */
    private Format formatejadorJAMP;

    public ControladorFormat() {

        this.formatejadorTXT = new FormatTXT();

        this.formatejadorXML = new FormatXML();

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
     * @param raw es tot el contingut que conte un fitxer guardat en un String.
     * @param format es el format del fitxer.
     * @return Donat tot el contingut d'un fitxer guardat en un String, retorna l'autor, el titol i el contingut del fitxer
     * si l'estructura del mateix es correcte i el fromat es .txt. En cas contrari fa throw d'exepcions.
     */
    public List<String> extractTitolAutorContingutDocument(String raw, String format) throws  Exception {
        if (format.equals("txt")) {
            try {
                return formatejadorTXT.extractTitolAutorContingutDocument(raw);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        } else throw new Exception("Error: format incorrecte");
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
