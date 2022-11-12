package capaDomini;

import java.util.*;

public class ControladorDomini {

    private ControladorDocuments cjtDocuments;
    private ControladorContingut CtrlContingut;
    private ControladorExpressions CtrlExpressions;

    public ControladorDomini() throws Exception {
        try {
            this.cjtDocuments = new ControladorDocuments();
            this.CtrlContingut = new ControladorContingut();
            this.CtrlExpressions = new ControladorExpressions();
        }
        catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryCrearDocument(String autor, String titol, String contingut) throws Exception {
        try {
            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingut(contingut);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryEliminarDocument(String autor, String titol) throws Exception{
        try{
            int id = cjtDocuments.indexDocument(autor,titol);
            cjtDocuments.eliminarDocument(autor,titol);
            CtrlContingut.eliminarContingut(id);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
    }

    public void queryModificarAutor(String autor, String titol, String nouAutor) throws Exception {
        try {
            cjtDocuments.modificarAutor(autor, titol, nouAutor);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryModificarTitol(String autor, String titol, String nouTitol) throws Exception {
        try {
            cjtDocuments.modificarTitol(autor, titol, nouTitol);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public List<String> queryLlistarTitolsAutor(String autor) throws Exception {
        try {
            List<String> llistat = cjtDocuments.llistarTitolsAutor(autor);
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public List<String> queryLlistarAutorsPrefix(String prefix) throws Exception {
        try {
            List<String> llistat = cjtDocuments.llistarAutorsPrefix(prefix);
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public String queryGetContingutDocument(String autor, String titol) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            String contingut = CtrlContingut.getContingut(id);
            return contingut;
        } catch (Exception e ) {
            throw new Exception(e.toString());
        }
    }

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

    public List<String> queryObtenirKSemblants(String autor, String titol, int k, int mode) throws Exception {
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
            throw new Exception(e.toString());
        }
    }

    public List<String> queryConsultaExpressioBooleana(String expressio) throws Exception{

        List<String> continguts = CtrlContingut.getConjuntContinguts();

        List<Integer> indexos = CtrlExpressions.ConsultaExpressioBooleana(expressio,continguts);

        List<String> llistat = new ArrayList<>();
        for (int index : indexos) {
            try {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        }
        return llistat;
    }

}