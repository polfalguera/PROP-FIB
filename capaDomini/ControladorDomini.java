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

    public void queryCrearDocumentPath(String autor, String titol, String path) throws Exception {
        try {
            cjtDocuments.crearDocument(autor,titol);
            CtrlContingut.afegirContingutPath(path);
        }catch (Exception e) {
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

    public void queryModificarAutor(String anticAutor, String nouAutor, String titol) throws Exception {
        try {
            cjtDocuments.modificarAutor(anticAutor, titol, nouAutor);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryModificarTitol(String autor, String anticTitol, String nouTitol) throws Exception {
        try {
            cjtDocuments.modificarTitol(autor,anticTitol, nouTitol);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryModificarContingut(String autor, String titol, String nouContingut) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            CtrlContingut.modificarContingut(id,nouContingut);
        }catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryModificarContingutPath(String autor, String titol, String path) throws Exception {
        try {
            int id = cjtDocuments.indexDocument(autor,titol);
            CtrlContingut.modificarContingutPath(id,path);
        }catch (Exception e) {
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

            int[] indexos = CtrlContingut.kRellevants(contingut,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public List<String> queryObtenirKRellevants(String paraules, int k, int mode) throws Exception {
        try {

            String[] p = paraules.split("\\p{Punct}| |\\n|¿|¡");

            int[] indexos = CtrlContingut.kRellevants(p,k, mode);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        }catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryCrearExpressioBooleana(String expressio) throws Exception {
        try {
            CtrlExpressions.anadir_expressio(expressio);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryEliminarExpressioBooleana(String expressio) throws Exception {
        try {
            CtrlExpressions.deleteExpressio(expressio);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void queryModificarExpressioBooleana(String antigaExpressio, String novaExpressio) throws Exception {
        try {
            CtrlExpressions.setExpressio(antigaExpressio,novaExpressio);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public List<String> queryConsultaExpressioBooleana(String expressio) throws Exception{
        try {
            List<String> continguts = CtrlContingut.getConjuntContinguts();

            List<Integer> indexos = CtrlExpressions.ConsultaExpressioBooleana(expressio,continguts);

            List<String> llistat = new ArrayList<>();
            for (int index : indexos) {
                llistat.addAll(cjtDocuments.getAutorTitolIndex(index));
            }
            return llistat;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
}