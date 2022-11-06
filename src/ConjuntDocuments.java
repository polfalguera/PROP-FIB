package src;

import java.util.*;

public class ConjuntDocuments {

    private List<Document> documents;
    private TreeMap<String, List<String>> titolsPerAutor;

    public ConjuntDocuments() {
        this.documents = new ArrayList<Document>();
        this.titolsPerAutor = new TreeMap<String, List<String>>();
    }

    public void crearDocument(String titol, String autor, String status) {
        status = "";
        if (titol.equals("")) {
            status = "Error, titol buit";
            return;
        }
        if (autor.equals("")) {
            status = "Error, autor buit";
            return;
        }
        if (titolsPerAutor.containsKey(autor)) {
            if (titolsPerAutor.get(autor).contains(titol)) {
                titolsPerAutor.get(autor).add(titol);
            } else {
                status = "Error, titol no existent";
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

    public void eliminarDocument(String titol, String autor, String status) {
        status = "";
        if (titol.equals("")) {
            status = "Error, titol buit";
            return;
        }
        if (autor.equals("")) {
            status = "Error, autor buit";
            return;
        }

        if (!titolsPerAutor.containsKey(autor)) {
            status = "Error, autor no existent";
            return;
        }
        if (!titolsPerAutor.get(autor).contains(titol)) {
            status = "Error, titol no existent";
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

    public List<String> llistarTitolsAutor(String autor, String status) {
        status = "";
        List<String> llistat = new ArrayList<>();

        if (titolsPerAutor.containsKey(autor)) llistat = titolsPerAutor.get(autor);
        if (llistat.isEmpty()) status = "No existeix cap titol amb l'autor introduit";
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

    public List<String> llistarAutorsPrefix(String prefix, String status) {
        status = "";
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
    public int indexContingutDocument(String autor, String titol, String status) {
        status = "";
        if (!titolsPerAutor.containsKey(autor)) {
            status = "Error, autor no existent";
            return -1;
        }
        if (!titolsPerAutor.get(autor).contains(titol)) {
            status = "Error, titol no existent";
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
