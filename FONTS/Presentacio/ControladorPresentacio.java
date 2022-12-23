package FONTS.Presentacio;

import FONTS.Domini.ControladorDomini;
import FONTS.Domini.Document;
import FONTS.Domini.Expressio;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
/**
 * Representa el controlador de Presentacio
 */
public class ControladorPresentacio {
    /**
     * Instancia del controlador de domini
     */
    private ControladorDomini CtrlDomini;
    /**
     * Instancia d'una vista principal
     */
    private final MainView vistaPrincipal;
    /**
     * Instancia d'una vista de benvinguda
     */
    private final WelcomeView vistaBenvinguda;

    /**
     * Crida al mètode queryGetAutorTitolIndex del CtrlDomini
     * @param id
     * @return Retorna l'autor i el titol del docuement que es troba en la posicio del parametre id en la llista
     * de documents del controlador de documents
     */
    public List<String> iqueryGetAutorTitolIndex(int id) throws Exception {
        return this.CtrlDomini.queryGetAutorTitolIndex(id);
    }
    /**
     * Constructora del controlador de presentació.
     * */
    public ControladorPresentacio() throws Exception {
        this.CtrlDomini = new ControladorDomini();
        CtrlDomini.obrirPrograma();
        this.vistaPrincipal = new MainView("Document Manager",this);
        this.vistaBenvinguda = new WelcomeView("Benvinguda!",this);
    }
    /**
     * Crida al mètode queryObrirPrograma del CtrlDomini
     */
    public void iqueryObrirPrograma() throws Exception {
        try {
            CtrlDomini.obrirPrograma();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryTancarPrograma del CtrlDomini
     */
    public void iqueryTancarPrograma() throws Exception {
        try {
            CtrlDomini.tancarPrograma();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryGetCjtDocuments del CtrlDomini
     * @return Retorna la llista de documents del controlador de documents
     */
    public List<Document> iqueryGetCjtDocuments() { return CtrlDomini.queryGetCjtDocuments(); }
    /**
     * Crida al mètode queryCrearDocument del CtrlDomini
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     * @param contingut es el contingut del document a crear.
     */
    public void iqueryCrearDocument(String autor, String titol, String contingut) throws Exception {
        try {
            CtrlDomini.queryCrearDocument(autor, titol, contingut);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryEliminarDocument del CtrlDomini
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     */
    public void iqueryEliminarDocument(String autor, String titol) throws Exception {
        try {
            CtrlDomini.queryEliminarDocument(autor,titol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryGetContingutDocument del CtrlDomini
     * @param autor es l'autor del document a consultar el seu contingut
     * @param titol es el titol del document a consultar el seu contingut
     * @return Retorna el contingut del document amb autor i titol introduits
     * */
    public String iqueryGetContingutDocument(String autor, String titol) throws Exception {
        try {
            return CtrlDomini.queryGetContingutDocument(autor, titol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryModificarAutor del CtrlDomini
     * @param anticAutor es l'autor del document a modificar.
     * @param nouAutor es el nou autor que tindra el document a modificar.
     * @param titol es el titol del document a modificar.
     */
    public void iqueryModificarAutor(String anticAutor, String nouAutor, String titol) throws Exception{
        try {
            CtrlDomini.queryModificarAutor(anticAutor, nouAutor, titol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryModificarTitol del CtrlDomini
     * @param autor es l'autor del document a modificar.
     * @param anticTitol es el titol del document a modificar.
     * @param nouTitol es el nou titol del document a modificar.
     */
    public void iqueryModificarTitol(String autor, String anticTitol, String nouTitol) throws Exception {
        try {
            CtrlDomini.queryModificarTitol(autor, anticTitol, nouTitol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryModificarContingut del CtrlDomini
     * @param autor es l'autor del document a modificar.
     * @param titol es el titol del document a modificar.
     * @param nouContingut es el nou contingut del document a modificar.
     */
    public void iqueryModificarContingut(String autor, String titol, String nouContingut) throws Exception {
        try {
            CtrlDomini.queryModificarContingut(autor, titol, nouContingut);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryLlistarTitolsAutor del CtrlDomini
     * @param autor es l'autor del qual volem saber tots els seus titols
     * @return Retorna una llista que conte els titols de l'autor introduit
     * */
    public List<String> iqueryLlistarTitolsAutor(String autor) throws Exception {
        try {
            return CtrlDomini.queryLlistarTitolsAutor(autor);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryLlistarAutorsPrefix del CtrlDomini
     * @param prefix es el prefix que han de contenir tots els autors que volem llistar
     * @return Retorna una llista que conte tots els autors que contenen el prefix introduit
     * */
    public List<String> iqueryLlistarAutorsPrefix(String prefix) throws Exception {
        try {
            return CtrlDomini.queryLlistarAutorsPrefix(prefix);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryConsultaExpressioBooleana del CtrlDomini
     * @param expressio es l'expressio booleana per la qual volem evaluar els continguts dels docuemnts que la compleixen
     * @return Retorna una llista que conte l'autor i el titol dels documents tal que el seu contingut satisfa l'expressio booleana
     * */
    public List<String> iqueryConsultaExpressioBooleana(String expressio) throws Exception {
        try {
            return CtrlDomini.queryConsultaExpressioBooleana(expressio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryEliminarExpressioBooleana del CtrlDomini
     * Elimina una expressio booleana del controlador d'expressions
     * @param expressio es l'expressio la qual volem eliminar del controlador d'expressions
     */
    public void iqueryEliminarExpressioBooleana(String expressio) throws Exception {
        try {
            CtrlDomini.queryEliminarExpressioBooleana(expressio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryModificarExpressioBooleana del CtrlDomini
     * @param antigaExpressio es l'expressio la qual volem modificar
     * @param novaExpressio es la nova expressio booleana de l'expressio que volem modificar
     */
    public void iqueryModificarExpressioBooleana(String antigaExpressio, String novaExpressio) throws Exception {
        try {
            CtrlDomini.queryModificarExpressioBooleana(antigaExpressio,novaExpressio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryGetConjuntExpressions del CtrlDomini
     * @return Retorna totes les expresions que estan carregades a memoria.
     * */
    public Set<String> iqueryGetConjuntExpressions() {
        HashMap<String,Expressio> cjtEx = CtrlDomini.queryGetConjuntExpressions();
        return cjtEx.keySet();
    }
    /**
     * Crida al mètode queryObtenirKSemblants del CtrlDomini
     * @param autor es l'autor del document a consultar els k docuemnts semblants
     * @param titol es el titol del document a consultar els k docuemnts semblants
     * @param k es el nombre de documents semblants que es volen calcular
     * @param mode es el mode que es vol utilitzar per calcular com de semblants son els documents
     * @return Retorna una llista que conte l'autor i el titol dels k documents semblants
     * */
    public List<String> iqueryObtenirKSemblants(String autor, String titol, int k, int mode) throws Exception {
        try {
            return CtrlDomini.queryObtenirKSemblants(autor,titol,k,mode);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode queryObtenirKRellevants del CtrlDomini
     * @param paraules son les paraules per les quals es volen evaluar els k documents mes rellevants
     * @param k es el nombre de documents rellevants que es volen calcular
     * @param mode es el mode que es vol utilitzar per calcular com de rellevants son els documents
     * @return Retorna una llista que conte l'autor i el titol dels k documents rellevants
     * */
    public List<String> iqueryObtenirKRellevants(String paraules, int k, int mode) throws Exception {
        try {
            return CtrlDomini.queryObtenirKRellevants(paraules,k,mode);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode carregarDocument del CtrlDomini
     * @param direccio Es la direccio d'on es vol llegir el fitxer a carregar
     * @param format Es el format del fitxer que es vol carregar. Pot ser: "txt", "xml" o "jamp"
     * Crea un document nou al sistema que conte l'autor, el titol i el contingut
     * extrets del fitxer guardat a la direccio que s'ha pasat com a parametre
     * @return Retorna l'autor i el titol del document creat
     *
     * */
    public List<String> icarregarDocument(String direccio, String format) throws Exception {
        try {
            return CtrlDomini.carregarDocument(direccio, format);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Crida al mètode exportarDocument del CtrlDomini
     * @param direccio es la direccio a la que es vol exportar el document
     * @param autor es l'autor del docuement que es vol exportar
     * @param titol es el titol del docuement que es vol exportar
     * @param format es el format al que es vol exportar el fitxer, pot ser: "txt", "xml" o "jamp"
     * Guarda a la direccio especificada un fitxer amb el format especificat que conte l'autor, el
     * titol i el contingut del document a exportar.
     * */
    public void iqueryExportarDocument(String autor, String titol, String format, String direccio) throws Exception {
        try {
            CtrlDomini.exportarDocument(autor,titol,format,direccio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Carrega la vista principal.
     * */
    public void carregarVistaPrincipal() {
        vistaBenvinguda.setVisible(false);
        vistaPrincipal.setVisible(true);
    }

    public static void main (String[] args) throws Exception{
        try {
            ControladorPresentacio CtrlPresentacio = new ControladorPresentacio();
            CtrlPresentacio.vistaBenvinguda.setVisible(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
