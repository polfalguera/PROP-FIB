package FONTS.Domini;

import FONTS.Persistencia.Persistencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el controlador de domini
 * @author Pol Faluera
 */
public class ControladorDomini {
    /**
     * Representa el controlador de documents
     */
    private ControladorDocuments cjtDocuments;
    /**
     * Representa el controlador de contingut
     */
    private ConjuntContinguts CtrlContingut;
    /**
     * Representa el controlador d'expressions
     */
    private ControladorExpressions CtrlExpressions;
    /**
     * Representa el controlador de format
     */
    private ControladorFormat CtrlFormat;

    /**
     * Constructora del controlador de domini
     * Inicialitza els tres controladors
     */
    public ControladorDomini() throws Exception {
        try {
            this.cjtDocuments = new ControladorDocuments();
            this.CtrlContingut = new ConjuntContinguts();
            this.CtrlExpressions = new ControladorExpressions();
            this.CtrlFormat = new ControladorFormat();
            // Aqui podriem llegir de disc els documents
            // persistits en una altre sesio
        }
        catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    /**
     * Modificadora
     * Afegeix un document al controlador de documents, i el seu contingut al controlador de contingut
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     * @param contingut es el contingut del document a crear.
     */
    public void queryCrearDocument(String autor, String titol, String contingut) throws Exception {
        try {
            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingut(contingut);
            //Capa de persistencia
            Persistencia.nouDocument(autor, titol, contingut);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Modificadora
     * Afegeix un document al controlador de documents, i el contingut
     * del path introduit al controlador de contingut.
     * El fitxer que apunta el path ha de ser un .txt
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     * @param path path que apunta al fitxer que conte el contingut a introduir.
     */

    /* Ara que ja tenim el controlador de format i la capa de persistencia ja no es necessaria
    // aquesta funcio
    public void queryCrearDocumentPath(String autor, String titol, String path) throws Exception {
        try {
            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingutPath(path);
            //Capa de persistencia
            Persistencia.nouDocument(autor, titol, CtrlContingut.getContingut());
        }catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    */

    /**
     * Modificadora
     * Elimina un document del controlador de documents, i el seu contingut
     * del controlador de contingut
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     */
    public void queryEliminarDocument(String autor, String titol) throws Exception{
        try{
            int id = cjtDocuments.indexDocument(autor,titol);
            cjtDocuments.eliminarDocument(autor,titol);
            CtrlContingut.eliminarContingut(id);
            //Capa de persistencia
            Persistencia.eliminarDocument(autor, titol);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
    }

    /**
     * Modificadora
     * Modifica l'autor d'un document
     * @param anticAutor es l'autor del document a modificar.
     * @param nouAutor es el nou autor que tindra el document a modificar.
     * @param titol es el titol del document a modificar.
     */
    public void queryModificarAutor(String anticAutor, String nouAutor, String titol) throws Exception {
        try {
            cjtDocuments.modificarAutor(anticAutor,nouAutor,titol);
            //Capa de persistencia
            int index = cjtDocuments.indexDocument(nouAutor, titol);
            String contingut = CtrlContingut.getContingut(index);
            Persistencia.modificarDocument(anticAutor, titol, nouAutor, titol, contingut);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Modificadora
     * Modifica el titol d'un document
     * @param autor es l'autor del document a modificar.
     * @param anticTitol es el titol del document a modificar.
     * @param nouTitol es el nou titol del document a modificar.
     */
    public void queryModificarTitol(String autor, String anticTitol, String nouTitol) throws Exception {
        try {
            cjtDocuments.modificarTitol(autor,anticTitol, nouTitol);
            //Capa de persistencia
            int index = cjtDocuments.indexDocument(autor, nouTitol);
            String contingut = CtrlContingut.getContingut(index);
            Persistencia.modificarDocument(autor, anticTitol, autor, nouTitol, contingut);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    /**
     * Modificadora
     * Modifica el contingut d'un document del controlador de contingut
     * @param autor es l'autor del document a modificar.
     * @param titol es el titol del document a modificar.
     * @param nouContingut es el nou contingut del document a modificar.
     */
    public void queryModificarContingut(String autor, String titol, String nouContingut) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            CtrlContingut.modificarContingut(id,nouContingut);
            //Capa de persistencia
            Persistencia.modificarDocument(autor, titol, autor, titol, nouContingut);
        }catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    /**
     * Modificadora
     * Modifica el contingut d'un document del controlador de contingut.
     * El nou contingut es llegeix del path introduit.
     * El fitxer que apunta el path ha de ser un .txt
     * @param autor es l'autor del document a modificar.
     * @param titol es el titol del document a modificar.
     * @param path path que apunta al fitxer que conte el nou contingut del document a modificar.
     */
    // Ara que ja tenim el controlador de format i la capa de persistencia ja no es necessaria
    // aquesta funcio
    /*
    public void queryModificarContingutPath(String autor, String titol, String path) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            CtrlContingut.modificarContingutPath(id,path);
        }catch (Exception e) {
            throw new Exception(e.toString());
        }
    }*/

    /**
     * Consultora
     * @param autor es l'autor del qual volem saber tots els seus titols
     * @return Retorna una llista que conte els titols de l'autor introduit
     * */
    public List<String> queryLlistarTitolsAutor(String autor) throws Exception {
        try {
            List<String> llistat = cjtDocuments.llistarTitolsAutor(autor);
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Consultora
     * @param prefix es el prefix que han de contenir tots els autors que volem llistar
     * @return Retorna una llista que conte tots els autors que contenen el prefix introduit
     * */
    public List<String> queryLlistarAutorsPrefix(String prefix) throws Exception {
        try {
            List<String> llistat = cjtDocuments.llistarAutorsPrefix(prefix);
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Consultora
     * @param autor es l'autor del document a consultar el seu contingut
     * @param titol es el titol del document a consultar el seu contingut
     * @return Retornael contingut del document amb autor i titol introduits
     * */
    public String queryGetContingutDocument(String autor, String titol) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            String contingut = CtrlContingut.getContingut(id);
            return contingut;
        } catch (Exception e ) {
            throw new Exception(e.toString());
        }
    }
    /* PER A FUTURES ENTREGUES
    public int queryGetIndexDocument(String autor, String titol) throws Exception {
        try {
            return cjtDocuments.indexDocument(autor,titol);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    public List<String> queryGetAutorTitolIndex(int id) throws Exception {
        try {
            return cjtDocuments.getAutorTitolIndex(id);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    */
    /**
     * Consultora
     * @param autor es l'autor del document a consultar els k docuemnts semblants
     * @param titol es el titol del document a consultar els k docuemnts semblants
     * @param k es el nombre de documents semblants que es volen calcular
     * @param mode es el mode que es vol utilitzar per calcular com de semblants son els documents
     * @return Retorna una llista que conte l'autor i el titol dels k documents semblants
     * */
    public List<String> queryObtenirKSemblants(String autor, String titol, int k, int mode) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);

            String[] contingut = CtrlContingut.obtenirParaulesContingut(id);

            int[] indexos = CtrlContingut.kRellevants(contingut,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Consultora
     * @param paraules son les paraules per les quals es volen evaluar els k documents mes rellevants
     * @param k es el nombre de documents rellevants que es volen calcular
     * @param mode es el mode que es vol utilitzar per calcular com de rellevants son els documents
     * @return Retorna una llista que conte l'autor i el titol dels k documents rellevants
     * */
    public List<String> queryObtenirKRellevants(String paraules, int k, int mode) throws Exception {
        try {

            String[] p = paraules.split("\\p{Punct}| |\\n|¿|¡");

            int[] indexos = CtrlContingut.kRellevants(p,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        }catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Modificadora
     * Crea una nova expressio booleana en el controlador d'expressions
     * @param expressio es l'expressio la qual volem guardar en el controlador d'expressions
     */
    public void queryCrearExpressioBooleana(String expressio) throws Exception {
        try {
            CtrlExpressions.anadir_expressio(expressio);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Modificadora
     * Elimina una expressio booleana del controlador d'expressions
     * @param expressio es l'expressio la qual volem eliminar del controlador d'expressions
     */
    public void queryEliminarExpressioBooleana(String expressio) throws Exception {
        try {
            CtrlExpressions.deleteExpressio(expressio);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Modificadora
     * Modificar una expressio booleana del controlador d'expressions
     * @param antigaExpressio es l'expressio la qual volem modificar
     * @param novaExpressio es la nova expressio booleana de l'expressio que volem modificar
     */
    public void queryModificarExpressioBooleana(String antigaExpressio, String novaExpressio) throws Exception {
        try {
            CtrlExpressions.setExpressio(antigaExpressio,novaExpressio);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Consultora
     * @param expressio es l'expressio booleana per la qual volem evaluar els continguts dels docuemnts que la compleixen
     * @return Retorna una llista que conte l'autor i el titol dels documents tal que el seu contingut satisfa l'expressio booleana
     * */
    public List<String> queryConsultaExpressioBooleana(String expressio) throws Exception {
        try {
            List<String> continguts = CtrlContingut.getConjuntContinguts();

            List<Integer> indexos = CtrlExpressions.ConsultaExpressioBooleana(expressio,continguts);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    public void carregarDocument(String direccio, String format) throws Exception {
        try {
            List<String> data = CtrlFormat.extractTitolAutorContingut(direccio, format);
            String autor = data.get(0);
            String titol = data.get(1);
            String contingut = data.get(2);
            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingut(contingut);
            Persistencia.nouDocument(autor, titol, contingut);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * De moment retorno un String que conte tot el que hauria d'haver-hi
     * en el document que es descarrega en el format desitjat a exportar
     *
     * */
    public String exportarDocument(String autor, String titol, String format) throws Exception {
        try {
            int index = cjtDocuments.indexDocument(autor, titol);
            String contingut = CtrlContingut.getContingut(index);
            String file = CtrlFormat.documentToFile(autor, titol, contingut, format);
            return file;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Consultora
     * @param autor es l'autor del document que volem comprovar si existeix
     * @param titol es el titol del document que volem comprovar si existeix
     * @return Retorna true si el document existeix, sino retorna false.
     * */
    public boolean queryExisteixDocument(String autor, String titol) { return cjtDocuments.existeixDocument(autor,titol); }
    public boolean queryExisteixExpressioBooleana(String expressio) { return CtrlExpressions.ExistExpressio(expressio); }
}