package capaDomini;

import java.util.*;

public class ControladorDomini {

    private ControladorDocuments cjtDocuments;
    private ControladorContingut CtrlContingut;
    private ControladorExpressions CtrlExpressions;

    public ControladorDomini() {
        try {
            this.cjtDocuments = new ControladorDocuments();
            this.CtrlContingut = new ControladorContingut();
            this.CtrlExpressions = new ControladorExpressions();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void queryCrearDocument(String autor, String titol) {
        try {
            cjtDocuments.crearDocument(autor,titol);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void queryEliminarDocument(String autor, String titol) {
        try{
            cjtDocuments.eliminarDocument(autor,titol);
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void queryModificarAutor(String autor, String titol, String nouAutor) {
        try {
            cjtDocuments.modificarAutor(autor, titol, nouAutor);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void queryModificarTitol(String autor, String titol, String nouTitol) {
        try {
            cjtDocuments.modificarTitol(autor, titol, nouTitol);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public List<String> queryLlistarTitolsAutor(String autor) {
        try {
            List<String> llistat = cjtDocuments.llistarTitolsAutor(autor);
            return llistat;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<String> queryLlistarAutorsPrefix(String prefix) {
        try {
            List<String> llistat = cjtDocuments.llistarAutorsPrefix(prefix);
            return llistat;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public String queryGetContingutDocument(String autor, String titol) {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            String contingut = CtrlContingut.getContingut(id);
            return contingut;
        } catch (Exception e ) {
            System.out.println(e.toString());
            return null;
        }
    }

    private List<String> queryGetTitolAutorIndex(int id) {
        try {
            return cjtDocuments.getAutorTitolIndex(id);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<String> queryTObtenirKSemblants(String autor, String titol, int k, int mode) {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);

            String[] contingut = CtrlContingut.obtenirParaulesContingut(id);

            int[] indexos = CtrlContingut.termsTfIdf(contingut,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<String> queryConsultaExpressioBooleana(String expressio) {

        List<String> continguts = CtrlContingut.getConjuntContinguts();

        List<Integer> indexos = CtrlExpressions.ConsultaExpressioBooleana(expressio,continguts);

        List<String> llistat = new ArrayList<>();
        for (int index : indexos) {
            try {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        }
        return llistat;
    }

}


