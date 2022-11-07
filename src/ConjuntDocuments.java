package src;

import java.util.*;

public class ConjuntDocuments {

    private List<Document> documents;
    private TreeMap<String, List<String>> titolsPerAutor;

    public ConjuntDocuments() {
        this.documents = new ArrayList<Document>();
        this.titolsPerAutor = new TreeMap<String, List<String>>();
    }

    public void crearDocument(String titol, String autor, String[] status) {
        status[0] = "";
        if (titol.equals("")) {
            status[0] = "Error, titol buit";
            return;
        }
        if (autor.equals("")) {
            status[0] = "Error, autor buit";
            return;
        }
        if (titolsPerAutor.containsKey(autor)) {
            if (!titolsPerAutor.get(autor).contains(titol)) {
                titolsPerAutor.get(autor).add(titol);
            } else {
                status[0] = "Error, titol de l'autor ja existent";
                return;
            }
        } else {
            List<String> titols = new ArrayList<String>();
            titols.add(titol);
            titolsPerAutor.put(autor, titols);
        }
        Document nouDocument = new Document(titol, autor);
        documents.add(nouDocument);

    }

    public void modificarAutor(String titol, String autor, String nouAutor, String[] status) {
        status[0] = "";
        if (nouAutor.equals("")) {
            status[0] = "Error, autor buit";
            return;
        }
        if (nouAutor.equals((autor))) return;

        if (titolsPerAutor.containsKey(autor)) {
            if (titolsPerAutor.get(autor).contains(titol)) {
                if (titolsPerAutor.containsKey(nouAutor)) {
                    if (!titolsPerAutor.get(nouAutor).contains(titol)) {
                        titolsPerAutor.get(autor).remove(titol);
                        titolsPerAutor.get(nouAutor).add(titol);

                        int index = 0;
                        for (int i = 0; i < documents.size(); ++i) {
                            if (documents.get(i).getTitol().equals(titol) && documents.get(i).getAutor().equals(autor)) index = i;
                            break;
                        }
                        documents.get(index).setAutor(nouAutor);
                    } else {
                        status[0] = "Error, titol del nou autor ja existent";
                        return;
                    }
                } else {
                    titolsPerAutor.get(autor).remove(titol);

                    List<String> titols = new ArrayList<String>();
                    titols.add(titol);
                    titolsPerAutor.put(nouAutor, titols);

                    int index = 0;
                    for (int i = 0; i < documents.size(); ++i) {
                        if (documents.get(i).getTitol().equals(titol) && documents.get(i).getAutor().equals(autor)) index = i;
                        break;
                    }
                    documents.get(index).setAutor(nouAutor);
                }

            } else {
                status[0] = "Error, titol de l'autor no existent";
                return;
            }
        } else {
            status[0] = "Error, autor no existent";
            return;
        }

    }
    public void modificarTitol(String titol, String autor, String nouTitol, String[] status) {
        status[0] = "";
        if (nouTitol.equals("")) {
            status[0] = "Error, autor buit";
            return;
        }

        if (titol.equals(nouTitol)) return;

        if (titolsPerAutor.containsKey(autor)) {
            if (titolsPerAutor.get(autor).contains(titol)) {
                if (!titolsPerAutor.get(autor).contains(nouTitol)){
                    int index = 0;
                    for (int i = 0; i < documents.size(); ++i) {
                        if (documents.get(i).getTitol().equals(titol) && documents.get(i).getAutor().equals(autor)) index = i;
                        break;
                    }
                    documents.get(index).setTitol(nouTitol);

                    index = 0;
                    List<String> titols = titolsPerAutor.get(autor);
                    for (int i = 0; i < titols.size(); ++i) {
                        if (titols.get(i).equals(titol)) index = i;
                        break;
                    }
                    titolsPerAutor.get(autor).set(index, nouTitol);
                } else {
                    status[0] = "Error, l'autor ja te un titol com el nou titol";
                    return;
                }
            } else {
                status[0] = "Error, titol de l'autor no existent";
                return;
            }
        } else {
            status[0] = "Error, autor no existent";
            return;
        }
    }

    public void eliminarDocument(String titol, String autor, String[] status) {
        status[0] = "";
        if (titol.equals("")) {
            status[0] = "Error, titol buit";
            return;
        }
        if (autor.equals("")) {
            status[0] = "Error, autor buit";
            return;
        }

        if (!titolsPerAutor.containsKey(autor)) {
            status[0] = "Error, autor no existent";
            return;
        }
        if (!titolsPerAutor.get(autor).contains(titol)) {
            status[0] = "Error, titol de l'autor no existent";
            return;
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

    public List<String> llistarTitolsAutor(String autor, String[] status) {
        status[0] = "";
        List<String> llistat = new ArrayList<>();

        if (titolsPerAutor.containsKey(autor)) llistat = titolsPerAutor.get(autor);
        if (llistat.isEmpty()) status[0] = "No existeix cap titol de l'autor introduit";
        return llistat;
    }

    public boolean esPrefix(String prefix, String autor) {
        int i = 0;
        String[] p = prefix.split("");
        String[] a = autor.split("");
        while (i < autor.length()) {
            if (!p[i].equals(a[i])) return false;
            ++i;
        }
        return true;
    }

    public List<String> llistarAutorsPrefix(String prefix, String[] status) {
        status[0] = "";
        List<String> llistat = new ArrayList<>();

        Set<String> autors = titolsPerAutor.keySet();

        for (String a: autors) {
            if (esPrefix(prefix,a)) llistat.add(a);
            else break;
        }

        return llistat;
    }

    // Retorna: -1 -> Docuemnt no trobat
    // Altrament: index del document
    public int indexContingutDocument(String autor, String titol, String[] status) {
        status[0] = "";
        if (titol.equals("")) {
            status[0] = "Error, titol buit";
            return -1;
        }
        if (autor.equals("")) {
            status[0] = "Error, autor buit";
            return -1;
        }
        if (!titolsPerAutor.containsKey(autor)) {
            status[0] = "Error, autor no existent";
            return -1;
        }
        if (!titolsPerAutor.get(autor).contains(titol)) {
            status[0] = "Error, titol de l'autor no existent";
            return -1;
        }
        for (int i = 0; i < documents.size(); ++i) {
            Document doc = documents.get(i);
            if (doc.getTitol().equals(titol) && doc.getAutor().equals(autor)) {
                return i;
            }
        }
        return -1;
    }
}
