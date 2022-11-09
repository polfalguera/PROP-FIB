package src;

import java.util.*;

public class ControladorDomini {

    private ConjuntDocuments cjtDocuments;
    private ControladorContingut CtrlContingut;
    private ControladorExpressions CtrlExpressions;

    public ControladorDomini() {
        try {
            this.cjtDocuments = new ConjuntDocuments();
            this.CtrlContingut = new ControladorContingut();
            this.CtrlExpressions = new ControladorExpressions();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void queryCrearDocument(String autor, String titol, String[] status) {
        cjtDocuments.crearDocument(autor,titol,status);

        if (!status[0].equals("")) System.out.println(status);
    }

    public void queryEliminarDocument(String autor, String titol, String[] status) {
        cjtDocuments.eliminarDocument(autor,titol,status);

        if (!status[0].equals("")) System.out.println(status);
    }

    public List<String> queryLlistarTitolsAutor(String autor, String[] status) {
        List<String> llistat = cjtDocuments.llistarTitolsAutor(autor,status);

        if (!status[0].equals("")) System.out.println(status);

        return llistat;
    }

    public List<String> queryLlistarAutorsPrefix(String prefix, String[] status) {
        List<String> llistat = cjtDocuments.llistarAutorsPrefix(prefix,status);

        if (!status[0].equals("")) System.out.println(status);

        return llistat;
    }

    public String queryGetContingutDocument(String autor, String titol, String[] status) {
        int id = cjtDocuments.indexContingutDocument(autor,titol,status);

        if (id == -1) {
            System.out.println(status);
            return "";
        }

        String contingut = CtrlContingut.getContingut(id);

        return contingut;
    }

    private List<String> queryGetTitolAutorIndex(int id) { return cjtDocuments.getAutorTitolIndex(id); }

    public List<String> queryTObtenirKSemblants(String autor, String titol, int k, String[] status, int mode) {
        int id = cjtDocuments.indexContingutDocument(autor,titol,status);

        String[] contingut = CtrlContingut.obtenirParaulesContingut(id);

        int[] indexos = CtrlContingut.termsTfIdf(contingut,k, mode);

        List<String> llistat = new ArrayList<>();
        for (int index : indexos) {
            llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
        }

        return llistat;
    }

    public List<String> queryConsultaExpressioBooleana(String expressio) {

        List<String> continguts = CtrlContingut.getConjuntContinguts();

        List<Integer> indexos = CtrlExpressions.ConsultaExpressioBooleana(expressio,continguts);

        List<String> llistat = new ArrayList<>();
        for (int index : indexos) {
            llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
        }

        return llistat;
    }

}


