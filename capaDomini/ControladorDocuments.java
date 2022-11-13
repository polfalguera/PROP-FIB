package capaDomini;

import java.util.*;

/**
 * Representa el controlador de documents.
 * @author Marc Quel.
 */

public class ControladorDocuments {
    /**
     * Representa el conjunt de documents
     */
    private List<Document> documents;
    /**
     * Representa els titols que te cada autor
     */
    private TreeMap<String, List<String>> titolsPerAutor;

    /**
     * Constructora del controlador de documents
     * Inicialitza les estructures de dades
     */
    public ControladorDocuments() {
        this.documents = new ArrayList<Document>();
        this.titolsPerAutor = new TreeMap<String, List<String>>();
    }

    /**
     * Modificadora
     * Afegeix un document a documents
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     */
    public void crearDocument(String autor, String titol) throws Exception {
        if (autor.equals("")) {
            throw new Exception("Error, autor buit");
        }
        if (titol.equals("")) {
            throw new Exception("Error, titol buit");
        }
        if (titolsPerAutor.containsKey(autor)) {
            if (!titolsPerAutor.get(autor).contains(titol)) {
                titolsPerAutor.get(autor).add(titol);
            } else {
                throw new Exception("Error, titol de l'autor ja existent");
            }
        } else {
            List<String> titols = new ArrayList<String>();
            titols.add(titol);
            titolsPerAutor.put(autor, titols);
        }
        Document nouDocument = new Document(titol, autor);
        documents.add(nouDocument);

    }

    /**
     * Modificadora
     * Elimina un document de documents
     * @param autor es l'autor del document a crear.
     * @param titol es el titol del document a crear.
     */
    public void eliminarDocument(String autor, String titol) throws Exception {
        if (autor.equals("")) {
            throw new Exception("Error, autor buit");
        }
        if (titol.equals("")) {
            throw new Exception("Error, titol buit");
        }

        if (!titolsPerAutor.containsKey(autor)) {
            throw new Exception("Error, autor no existent");

        }
        if (!titolsPerAutor.get(autor).contains(titol)) {
            throw new Exception("Error, titol de l'autor no existent");
        }
        for (int i = 0; i < documents.size(); ++i) {
            Document doc = documents.get(i);
            if (doc.getTitol().equals(titol) && doc.getAutor().equals(autor)) {
                documents.remove(i);
                break;
            }
        }
        titolsPerAutor.get(autor).remove(titol);
        if (titolsPerAutor.get(autor).isEmpty()) titolsPerAutor.remove(autor);
    }

    /**
     * Consultora
     * @param autor es l'autor del document que volem comprovar si existeix
     * @param titol es el titol del document que volem comprovar si existeix
     * @return Retorna true si el document existeix, sino retorna false.
     * */
    public boolean existeixDocument(String autor, String titol) {
        if (titolsPerAutor.containsKey(autor)) return titolsPerAutor.get(autor).contains(titol);
        return false;
    }

    /**
     * Modificadora
     * Modifica l'autor d'un document
     * @param anticAutor es l'autor del document a modificar.
     * @param nouAutor es el nou autor que tindra el document a modificar.
     * @param titol es el titol del document a modificar.
     */
    public void modificarAutor(String anticAutor, String nouAutor, String titol) throws Exception {
        if (nouAutor.equals("")) {
            throw new Exception("Error, nou autor buit");
        }
        if (anticAutor.equals("")) {
            throw new Exception("Error, autor buit");
        }
        if (titol.equals("")) {
            throw new Exception("Error, titol buit");
        }
        if (nouAutor.equals((anticAutor))) return;

        if (titolsPerAutor.containsKey(anticAutor)) {
            if (titolsPerAutor.get(anticAutor).contains(titol)) {
                if (titolsPerAutor.containsKey(nouAutor)) {
                    if (!titolsPerAutor.get(nouAutor).contains(titol)) {
                        if (titolsPerAutor.get(anticAutor).size() == 1) {
                            titolsPerAutor.remove(anticAutor);
                        } else titolsPerAutor.get(anticAutor).remove(titol);
                        titolsPerAutor.get(nouAutor).add(titol);

                        int index = 0;
                        for (int i = 0; i < documents.size(); ++i) {
                            if (documents.get(i).getTitol().equals(titol) && documents.get(i).getAutor().equals(anticAutor)) index = i;
                            break;
                        }
                        documents.get(index).setAutor(nouAutor);
                    } else {
                        throw new Exception("Error, titol del nou autor ja existent");
                    }
                } else {
                    if (titolsPerAutor.get(anticAutor).size() == 1) {
                        titolsPerAutor.remove(anticAutor);
                    } else titolsPerAutor.get(anticAutor).remove(titol);

                    List<String> titols = new ArrayList<String>();
                    titols.add(titol);
                    titolsPerAutor.put(nouAutor, titols);

                    int index = 0;
                    for (int i = 0; i < documents.size(); ++i) {
                        if (documents.get(i).getTitol().equals(titol) && documents.get(i).getAutor().equals(anticAutor)) index = i;
                        break;
                    }
                    documents.get(index).setAutor(nouAutor);
                }

            } else {
                throw new Exception("Error, titol de l'autor no existent");
            }
        } else {
            throw new Exception("Error, autor no existent");
        }

    }

    /**
     * Modificadora
     * Modifica el titol d'un document
     * @param autor es l'autor del document a modificar.
     * @param anticTitol es el titol del document a modificar.
     * @param nouTitol es el nou titol del document a modificar.
     */
    public void modificarTitol(String autor, String anticTitol, String nouTitol) throws Exception {
        if (nouTitol.equals("")) {
            throw new Exception("Error, nou titol buit");
        }
        if (anticTitol.equals("")) {
            throw new Exception("Error, titol buit");
        }
        if (autor.equals("")) {
            throw new Exception("Error, autor buit");
        }

        if (anticTitol.equals(nouTitol)) return;

        if (titolsPerAutor.containsKey(autor)) {
            if (titolsPerAutor.get(autor).contains(anticTitol)) {
                if (!titolsPerAutor.get(autor).contains(nouTitol)){
                    int index = 0;
                    for (int i = 0; i < documents.size(); ++i) {
                        if (documents.get(i).getTitol().equals(anticTitol) && documents.get(i).getAutor().equals(autor)) index = i;
                        break;
                    }
                    documents.get(index).setTitol(nouTitol);

                    index = 0;
                    List<String> titols = titolsPerAutor.get(autor);
                    for (int i = 0; i < titols.size(); ++i) {
                        if (titols.get(i).equals(anticTitol)) index = i;
                        break;
                    }
                    titolsPerAutor.get(autor).set(index, nouTitol);
                } else {
                    throw new Exception("Error, l'autor ja te un titol com el nou titol");
                }
            } else {
                throw new Exception("Error, titol de l'autor no existent");
            }
        } else {
            throw new Exception("Error, autor no existent");
        }
    }

    /**
     * Consultora
     * @param autor es l'autor del qual volem saber tots els seus titols
     * @return Retorna una llista que conte els titols de l'autor introduit
     * */
    public List<String> llistarTitolsAutor(String autor) throws Exception{
        List<String> llistat = new ArrayList<>();

        if (titolsPerAutor.containsKey(autor)) llistat = titolsPerAutor.get(autor);
        if (llistat.isEmpty()) throw new Exception("No existeix cap titol de l'autor introduit");
        return llistat;
    }


    private boolean esPrefix(String prefix, String autor) {
        int i = 0;
        String[] p = prefix.split("");
        String[] a = autor.split("");
        if (p.length > a.length) return false;
        while (i < p.length) {
            if (!p[i].equals(a[i])) return false;
            ++i;
        }
        return true;
    }

    /**
     * Consultora
     * @param prefix es el prefix que han de contenir tots els autors que volem llistar
     * @return Retorna una llista que conte tots els autors que contenen el prefix introduit
     * */
    public List<String> llistarAutorsPrefix(String prefix) {
        List<String> llistat = new ArrayList<>();

        Set<String> autors = titolsPerAutor.keySet();

        boolean primerTrobat = false;
        for (String a: autors) {
            if (esPrefix(prefix,a)) {
                llistat.add(a);
                if (!primerTrobat) primerTrobat = true;
            }
            else {
                if (primerTrobat) break;
            }
        }

        return llistat;
    }

    /**
     * Consultora
     * @param autor es l'autor del document que volem obtenir el seu index
     * @param titol es el titol del document que volem obtenir el seu index
     * @return Retorna la posicio del document en documents.
     * */
    public int indexDocument(String autor, String titol) throws Exception{
        if (titol.equals("")) {
            throw new Exception("Error, titol buit");
        }
        if (autor.equals("")) {
            throw new Exception("Error, autor buit");
        }
        if (!titolsPerAutor.containsKey(autor)) {
            throw new Exception("Error, autor no existent");
        }
        if (!titolsPerAutor.get(autor).contains(titol)) {
            throw new Exception("Error, titol de l'autor no existent");
        }
        for (int i = 0; i < documents.size(); ++i) {
            Document doc = documents.get(i);
            if (doc.getTitol().equals(titol) && doc.getAutor().equals(autor)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Consultora
     * @param id es l'index del document en documents
     * @return Retorna l'autor i el titol del document en la posicio index en documents
     * */
    public List<String> getAutorTitolIndex(int id) throws Exception {
        if (id >= documents.size() || id < 0) throw new Exception("Error, index out of bounds");
        List<String> AutorTitol = new ArrayList<String>();

        AutorTitol.add(documents.get(id).getAutor());
        AutorTitol.add(documents.get(id).getTitol());

        return AutorTitol;
    }
}
