package FONTS.Domini;

import FONTS.Persistencia.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
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
            // persistits en un altre sesio
        }
        catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    /**
     * Persisteix a disc tant les frequencies de totes els continguts com les expressions
     */
    public void tancarPrograma() throws Exception {
        try {
            //Guardem les expressions
            HashMap<String, Expressio> expresions = CtrlExpressions.getCjtExpressions();
            List<String> e = new ArrayList<String>();
            for (HashMap.Entry<String, Expressio> entry : expresions.entrySet()) {
                e.add(entry.getKey());
            }
            Persistencia.persisitirExpressio(e);

            //Guardem les frequencies
            Persistencia.buidarFrequencies();
            List<HashMap<String, Integer>> freq = CtrlContingut.getFreqContingut();
            for (int i = 0; i < freq.size(); ++i) {
                List<String> AutorTitol = queryGetAutorTitolIndex(i);
                Persistencia.persitirFrequencies(AutorTitol.get(0), AutorTitol.get(1), freq.get(i));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Carrega de disc a les estrucutres de dades les expressions persistides,
     * les frequencies presistides, i els documents persistits
     */
    public void obrirPrograma() throws Exception {
        try {
            //Recuperem les expressions
            List<String> expressions = Persistencia.recuperarExpressions();
            for (String s : expressions) CtrlExpressions.anadir_expressio(s);

            //Recuperem les frequencies
            List<HashMap<String, Integer>> freq = Persistencia.recuperarFreq();

            CtrlContingut.setFrequencies(freq);


            //Recuperem els documents
            List<String> documents = Persistencia.recuperarDocuments();
            CtrlContingut.inicializarContinguts(documents.size());
            List <String> autorsItitols = new ArrayList<>();

            for (String s : documents) {

                List<String> d = CtrlFormat.extractTitolAutorContingutDocument(s, "txt");
                cjtDocuments.crearDocument(d.get(0), d.get(1));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Modificadora
     * Afegeix un document al controlador de documents, i el seu contingut al controlador de contingut
     * Tambe persisteix el document a disc
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     * @param contingut es el contingut del document a crear.
     */
    public void queryCrearDocument(String autor, String titol, String contingut) throws Exception {
        try {
            if (contingut.equals("")) throw new Exception("Error: contingut buit");
            if (titol.equals("")) throw new Exception("Error: t??tol buit");
            if (autor.equals("")) throw new Exception("Error: autor buit");

            String c = CtrlFormat.documentToFile(autor,titol,contingut,"txt");
            Persistencia.nouDocument(autor, titol, c);

            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingut(contingut);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Modificadora
     * Elimina un document del controlador de documents, i el seu contingut
     * del controlador de contingut
     * Tambe l'elimina de disc
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
            throw new Exception(e.getMessage());
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
        if (nouAutor.equals("")) throw new Exception("Error: autor buit");
        try {
            int index = cjtDocuments.indexDocument(anticAutor, titol);
            String contingut = CtrlContingut.getContingut(index);
            String c = CtrlFormat.documentToFile(nouAutor,titol,contingut,"txt");
            Persistencia.modificarDocument(anticAutor, titol, nouAutor, titol, c, false);

            cjtDocuments.modificarAutor(anticAutor,nouAutor,titol);
        } catch (Exception e) {
            if (e.getMessage() == "El contingut no est?? en memoria") {
                String doc = Persistencia.obtenirContingut(anticAutor, titol);
                List<String> d = CtrlFormat.extractTitolAutorContingutDocument(doc, "txt");

                String cont = CtrlFormat.documentToFile(nouAutor,titol,d.get(2),"txt");
                Persistencia.modificarDocument(anticAutor, titol, nouAutor, titol, cont, false);

                cjtDocuments.modificarAutor(anticAutor,nouAutor,titol);
                CtrlContingut.modificarContingut(cjtDocuments.indexDocument(nouAutor, titol), d.get(2));

            }
            else throw new Exception(e.getMessage());
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
        if (nouTitol.equals("")) throw new Exception("Error: titol buit");
        try {
            int index = cjtDocuments.indexDocument(autor, anticTitol);
            String contingut = CtrlContingut.getContingut(index);
            String c = CtrlFormat.documentToFile(autor,nouTitol,contingut,"txt");
            Persistencia.modificarDocument(autor, anticTitol, autor, nouTitol, c, false);

            cjtDocuments.modificarTitol(autor,anticTitol, nouTitol);

        } catch (Exception e) {
            if (e.getMessage() == "El contingut no est?? en memoria") {
                String doc = Persistencia.obtenirContingut(autor, anticTitol);
                List<String> d = CtrlFormat.extractTitolAutorContingutDocument(doc, "txt");

                String cont = CtrlFormat.documentToFile(autor,nouTitol,d.get(2),"txt");
                Persistencia.modificarDocument(autor, anticTitol, autor, nouTitol, cont,false);

                cjtDocuments.modificarTitol(autor,anticTitol, nouTitol);
                CtrlContingut.modificarContingut(cjtDocuments.indexDocument(autor, nouTitol), d.get(2));
            } else throw new Exception(e.getMessage());
        }
    }
    /**
     * Modificadora
     * Modifica el contingut d'un document
     * @param autor es l'autor del document a modificar.
     * @param titol es el titol del document a modificar.
     * @param nouContingut es el nou contingut del document a modificar.
     */
    public void queryModificarContingut(String autor, String titol, String nouContingut) throws Exception {
        if (nouContingut.equals("")) throw new Exception("Error: contingut buit");
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            CtrlContingut.modificarContingut(id,nouContingut);
            String c = CtrlFormat.documentToFile(autor,titol,nouContingut,"txt");
            //Capa de persistencia
            Persistencia.modificarDocument(autor, titol, autor, titol, c, true);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

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
            throw new Exception(e.getMessage());
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
            throw new Exception(e.getMessage());
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
            if (e.getMessage() == "El contingut no est?? en memoria") {
                String doc = Persistencia.obtenirContingut(autor, titol);
                List<String> d = CtrlFormat.extractTitolAutorContingutDocument(doc, "txt");
                CtrlContingut.modificarContingut(cjtDocuments.indexDocument(autor, titol), d.get(2));
                // Ara ja el tenim en memoria
                int id = cjtDocuments.indexDocument(autor,titol);
                String contingut = CtrlContingut.getContingut(id);
                return contingut;
            } else throw new Exception(e.getMessage());
        }
    }
    /**
     * Consultora
     * @param id es l'index del document que es vol consultar al controlador de documents
     * @return Retorna l'autor i el titol del docuement que es troba en la posicio del parametre id en la llista
     * de documents del controlador de documents
     * */
    public List<String> queryGetAutorTitolIndex(int id) throws Exception {
        try {
            return cjtDocuments.getAutorTitolIndex(id);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
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
            String aux = CtrlContingut.getContingut(id);

            String[] contingut = CtrlContingut.obtenirParaulesContingut(id);

            int[] indexos = CtrlContingut.kRellevants(contingut,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        } catch (Exception e) {
            if (e.getMessage() == "El contingut no est?? en memoria") {
                String doc = Persistencia.obtenirContingut(autor, titol);
                List<String> d = CtrlFormat.extractTitolAutorContingutDocument(doc, "txt");
                CtrlContingut.modificarContingut(cjtDocuments.indexDocument(autor, titol), d.get(2));
                //
                int id = cjtDocuments.indexDocument(autor,titol);
                String[] contingut = CtrlContingut.obtenirParaulesContingut(id);

                int[] indexos = CtrlContingut.kRellevants(contingut,k, mode);

                List<String> llistat = new ArrayList<>();
                for (int index : indexos) {
                    llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
                }
                return llistat;
            }
            else throw new Exception(e.getMessage());
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

            String[] p = paraules.split("\\p{Punct}| |\\n|??|??");

            int[] indexos = CtrlContingut.kRellevants(p,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Modificadora
     * Crea una nova expressio booleana en el controlador d'expressions
     * @param expressio es l'expressio la qual volem guardar en el controlador d'expressions
     */
    public void queryCrearExpressioBooleana(String expressio) throws Exception {
        try {
            int i = CtrlExpressions.getNumExpressions();
            CtrlExpressions.anadir_expressio(expressio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
            throw new Exception(e.getMessage());
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
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Consultora
     * @param expressio es l'expressio booleana per la qual volem evaluar els continguts dels docuemnts que la compleixen
     * @return Retorna una llista que conte l'autor i el titol dels documents tal que el seu contingut satisfa l'expressio booleana
     * */
    public List<String> queryConsultaExpressioBooleana(String expressio) throws Exception {
        try {
            //List<String> continguts = CtrlContingut.getConjuntContinguts();
            List <String> fitxers = Persistencia.recuperarDocuments();
            List <String> continguts = new ArrayList<String>();
            for (int i = 0; i < fitxers.size(); ++i) {

                continguts.add(CtrlFormat.extractTitolAutorContingutDocument(fitxers.get(i), "txt").get(2));
            }

            List<Integer> indexos = CtrlExpressions.ConsultaExpressioBooleana(expressio,continguts);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                fitxers.get(index);
                List<String> aux = CtrlFormat.extractTitolAutorContingutDocument(fitxers.get(index), "txt");
                int realIndex = cjtDocuments.indexDocument(aux.get(0), aux.get(1));
                llistat.addAll(cjtDocuments.getAutorTitolIndex(realIndex));
            }
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Consultora
     * @return Retorna totes les expresions que estan carregades a memoria.
     * */
    public HashMap<String,Expressio> queryGetConjuntExpressions() {
        return CtrlExpressions.getCjtExpressions();
    }

    /**
     * Consultora
     * @param direccio Es la direccio d'on es vol llegir el fitxer a carregar
     * @param format Es el format del fitxer que es vol carregar. Pot ser: "txt", "xml" o "jamp"
     * Crea un document nou al sistema que conte l'autor, el titol i el contingut
     * extrets del fitxer guardat a la direccio que s'ha pasat com a parametre
     * @return Retorna l'autor i el titol del document creat
     *
     * */
    public List<String> carregarDocument(String direccio, String format) throws Exception {
        try {
            List<String> data = CtrlFormat.extractTitolAutorContingut(direccio, format);
            String autor = data.get(0);
            String titol = data.get(1);
            String contingut = data.get(2);

            String c = CtrlFormat.documentToFile(autor,titol,contingut,"txt");
            Persistencia.nouDocument(autor, titol, c);

            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingut(contingut);

            List<String> aux = new ArrayList<>();
            aux.add(autor); aux.add(titol);
            return aux;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param direccio es la direccio a la que es vol exportar el document
     * @param autor es l'autor del docuement que es vol exportar
     * @param titol es el titol del docuement que es vol exportar
     * @param format es el format al que es vol exportar el fitxer, pot ser: "txt", "xml" o "jamp"
     * Guarda a la direccio especificada un fitxer amb el format especificat que conte l'autor, el
     * titol i el contingut del document a exportar.
     * */
    public void exportarDocument(String autor, String titol, String format, String direccio) throws Exception {
        try {
            int index = cjtDocuments.indexDocument(autor, titol);
            String contingut = CtrlContingut.getContingut(index);
            String file = CtrlFormat.documentToFile(autor, titol, contingut, format);
            Persistencia.persistirADireccio(titol, autor, file, direccio, format);
        } catch (Exception e) {
            if (e.getMessage() == "El contingut no est?? en memoria") {
                String doc = Persistencia.obtenirContingut(autor, titol);
                List<String> d = CtrlFormat.extractTitolAutorContingutDocument(doc, "txt");
                CtrlContingut.modificarContingut(cjtDocuments.indexDocument(autor, titol), d.get(2));
                // Ara ja el tenim en memoria
                int id = cjtDocuments.indexDocument(autor,titol);
                String contingut = CtrlContingut.getContingut(id);
                //Ja tenim el contingut carregat a memoria
                String file = CtrlFormat.documentToFile(autor, titol, contingut, format);
                Persistencia.persistirADireccio(titol, autor, file, direccio, format);
            } else throw new Exception(e.getMessage());
        }
    }

    /**
     * Consultora
     * @return Retorna la llista de documents del controlador de documents
     * */
    public List<Document> queryGetCjtDocuments() { return cjtDocuments.getCjtDocuments(); }
    /**
     * Consultora
     * @param autor es l'autor del document que es vol comprovar si existeix
     * @param titol es el titol del document que es vol comprovar si existeix
     * @return Retorna true si el document existeix, sino retorna false.
     * */
    public boolean queryExisteixDocument(String autor, String titol) { return cjtDocuments.existeixDocument(autor,titol); }
    /**
     * Consultora
     * @param expressio es l'expressio que es vol comprovar si existeix.
     * @return Retorna true si l'expressio existeix, sino retorna false.
     * */
    public boolean queryExisteixExpressioBooleana(String expressio) { return CtrlExpressions.ExistExpressio(expressio); }
}