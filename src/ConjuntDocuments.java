package src;

import java.util.*;

public class ConjuntDocuments {

    private List<Document> documents;
    private HashMap<String, List<String> > titolsPerAutor;

    public ConjuntDocuments() {
        this.documents = new ArrayList<Document>();
        this.titolsPerAutor = new HashMap<>();
    }

    // Retorna:  0 -> Creat
    //          -1 -> No creat, titol buit
    //          -2 -> No creat, autor buit
    //          -3 -> No creat, titol per autor ja existent
    public int crearDocument(String titol, String autor) {
        if (titol == "" ) return -1;
        if (autor == "") return -2;
        if (!!titolsPerAutor.containsKey(autor)) {
            if (titolsPerAutor.get(autor).indexOf(titol) != -1) {
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
        return 0;
    }
}
