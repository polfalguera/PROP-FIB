package FONTS.Presentacio;

import FONTS.Domini.ControladorDomini;
import FONTS.Domini.Document;
import FONTS.Domini.Expressio;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ControladorPresentacio {

    private ControladorDomini CtrlDomini;

    private final MainView vistaPrincipal;
    private final WelcomeView vistaBenvinguda;

    public ControladorPresentacio() throws Exception {
        this.CtrlDomini = new ControladorDomini();
        CtrlDomini.obrirPrograma();
        this.vistaPrincipal = new MainView("Document Manager",this);
        this.vistaBenvinguda = new WelcomeView("Benvinguda!",this);
    }
    public void iqueryObrirPrograma() throws Exception {
        try {
            CtrlDomini.obrirPrograma();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void iqueryTancarPrograma() throws Exception {
        try {
            CtrlDomini.tancarPrograma();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<Document> iqueryGetCjtDocuments() { return CtrlDomini.queryGetCjtDocuments(); }
    public void iqueryCrearDocument(String autor, String titol, String contingut) throws Exception {
        try {
            CtrlDomini.queryCrearDocument(autor, titol, contingut);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void iqueryEliminarDocument(String autor, String titol) throws Exception {
        try {
            CtrlDomini.queryEliminarDocument(autor,titol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public String iqueryGetContingutDocument(String autor, String titol) throws Exception {
        try {
            return CtrlDomini.queryGetContingutDocument(autor, titol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void iqueryModificarAutor(String anticAutor, String nouAutor, String titol) throws Exception{
        try {
            CtrlDomini.queryModificarAutor(anticAutor, nouAutor, titol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void iqueryModificarTitol(String autor, String anticTitol, String nouTitol) throws Exception {
        try {
            CtrlDomini.queryModificarTitol(autor, anticTitol, nouTitol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void iqueryModificarContingut(String autor, String anticTitol, String nouTitol) throws Exception {
        try {
            CtrlDomini.queryModificarContingut(autor, anticTitol, nouTitol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<String> iqueryLlistarTitolsAutor(String autor) throws Exception {
        try {
            return CtrlDomini.queryLlistarTitolsAutor(autor);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<String> iqueryLlistarAutorsPrefix(String prefix) throws Exception {
        try {
            return CtrlDomini.queryLlistarAutorsPrefix(prefix);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<String> iqueryConsultaExpressioBooleana(String expressio) throws Exception {
        try {
            return CtrlDomini.queryConsultaExpressioBooleana(expressio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void iqueryEliminarExpressioBooleana(String expressio) throws Exception {
        try {
            CtrlDomini.queryEliminarExpressioBooleana(expressio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public Set<String> iqueryGetConjuntExpressions() {
        HashMap<String,Expressio> cjtEx = CtrlDomini.queryGetConjuntExpressions();
        return cjtEx.keySet();
    }
    public List<String> iqueryObtenirKSemblants(String autor, String titol, int k, int mode) throws Exception {
        try {
            return CtrlDomini.queryObtenirKSemblants(autor,titol,k,mode);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<String> iqueryObtenirKRellevants(String paraules, int k, int mode) throws Exception {
        try {
            return CtrlDomini.queryObtenirKRellevants(paraules,k,mode);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<String> icarregarDocument(String direccio, String format) throws Exception {
        try {
            return CtrlDomini.carregarDocument(direccio, format);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void iqueryExportarDocument(String autor, String titol, String format, String direccio) throws Exception {
        try {
            CtrlDomini.exportarDocument(autor,titol,format,direccio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void carregarVistaPrincipal() {
        vistaBenvinguda.setVisible(false);
        vistaPrincipal.setVisible(true);
    }

    public static void main (String[] args) throws Exception{
        try {
            ControladorPresentacio CtrlPresentacio = new ControladorPresentacio();
            CtrlPresentacio.vistaBenvinguda.setVisible(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
