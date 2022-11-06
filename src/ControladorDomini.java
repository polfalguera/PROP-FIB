package src;

import java.util.*;

public class ControladorDomini {

    private ConjuntDocuments cjtDocuments;

    public void queryCrearDocument(String titol, String autor, String status) {
        cjtDocuments.crearDocument(titol,autor,status);

        if (!status.equals(" ")) System.out.println(status);
    }

    public void queryEliminarDocument(String titol, String autor, String status) {
        cjtDocuments.eliminarDocument(titol,autor,status);

        if (!status.equals(" ")) System.out.println(status);
    }

    public List<String> queryLlistarTitolsAutor(String autor, String status) {
        List<String> llistat = cjtDocuments.llistarTitolsAutor(autor,status);

        if (!status.equals(" ")) System.out.println(status);

        return llistat;
    }

    public List<String> queryLlistarAutorsPrefix(String prefix, String status) {
        List<String> llistat = cjtDocuments.llistarAutorsPrefix(prefix,status);

        if (!status.equals(" ")) System.out.println(status);

        return llistat;
    }

    public String queryGetContingutDocument(String titol, String autor, String status) {
        int idDoc = cjtDocuments.indexContingutDocument(autor,titol,status);

        if (idDoc == -1) {
            System.out.println(status);
            return " ";
        }

        String contingut = " "; //CtrlContingut.getContingut(idDoc,status);

        return contingut;
    }

}


