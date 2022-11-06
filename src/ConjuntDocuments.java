package src;

import java.util.*;

public class ConjuntDocuments {

    private List<Document> documents;
    private TreeMap<String, List<String>> titolsPerAutor;

    public ConjuntDocuments() {
        this.documents = new ArrayList<Document>();
        this.titolsPerAutor = new TreeMap<String, List<String>>();
    }

    // Retorna:  0 -> Creat
    //          -1 -> No creat, titol buit
    //          -2 -> No creat, autor buit
    //          -3 -> No creat, titol per autor ja existent
    public int crearDocument(String titol, String autor) {
        if (titol.equals("")) return -1;
        if (autor.equals("")) return -2;
        if (titolsPerAutor.containsKey(autor)) {
            if (titolsPerAutor.get(autor).contains(titol)) {
                titolsPerAutor.get(autor).add(titol);
            } else return -2;
        } else {
            List<String> titols = new ArrayList<String>();
            titols.add(titol);
            titolsPerAutor.put(autor, titols);
        }
        Document nouDocument = new Document(titol, autor);
        documents.add(nouDocument);
        return 0;
    }

    // Retorna:  0 -> Eliminat
    //          -1 -> No eliminat, titol buit
    //          -2 -> No eliminat, autor buit
    //          -3 -> No eliminat, no existeix
    public int eliminarDocument(String titol, String autor) {
        if (titol.equals("")) return -1;
        if (autor.equals("")) return -2;

        if (!titolsPerAutor.containsKey(autor)) return -3;
        if (!titolsPerAutor.get(autor).contains(titol)) return -3;

        for (int i = 0; i < documents.size(); ++i) {
            Document doc = documents.get(i);
            if (doc.getTitol().equals(titol) && doc.getAutor().equals(autor)) {
                documents.remove(i);
                break;
            }
        }

        titolsPerAutor.get(autor).remove(titol);
        if (titolsPerAutor.get(autor).isEmpty()) titolsPerAutor.remove(autor);

        return 0;
    }

    public List<String> llistarTitolsAutor(String autor) {
        List<String> llistat = new ArrayList<>();

        if (titolsPerAutor.containsKey(autor)) llistat = titolsPerAutor.get(autor);

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

    public List<String> llistarAutorsPrefix(String prefix) {
        List<String> llistat = new ArrayList<>();

        Set<String> autors = titolsPerAutor.keySet();

        for (String a: autors) {
            if (esPrefix(prefix,a)) llistat.add(a);
            else break;
        }

        return llistat;
    }
}
