package FONTS.Presentacio;

import FONTS.Domini.ControladorDomini;
import java.util.ArrayList;
import java.util.List;

public class ControladorPresentacio {

    private ControladorDomini CtrlDomini;

    private final MainView vistaPrincipal;

    public ControladorPresentacio() throws Exception {
        this.CtrlDomini = new ControladorDomini();
        this.vistaPrincipal = new MainView("Document Manager",this);
    }
    public void iqueryCrearDocument(String autor, String titol, String contingut) throws Exception {
        CtrlDomini.queryCrearDocument(autor, titol, contingut);
    }

    public String iqueryGetContingutDocument(String autor, String titol) throws Exception{
        return CtrlDomini.queryGetContingutDocument(autor, titol);
    }

    public void iqueryModificarAutor(String anticAutor, String nouAutor, String titol) throws Exception{
        CtrlDomini.queryModificarAutor(anticAutor, nouAutor, titol);
    }

    public void iqueryModificarTitol(String autor, String anticTitol, String nouTitol) throws Exception {
        CtrlDomini.queryModificarTitol(autor, anticTitol, nouTitol);
    }

    public List<String> iqueryLlistarTitolsAutor(String autor) throws Exception {
        return CtrlDomini.queryLlistarTitolsAutor(autor);
    }

    public List<String> iqueryLlistarAutorsPrefix(String prefix) throws Exception {
        return CtrlDomini.queryLlistarAutorsPrefix(prefix);
    }

    public static void main (String[] args) {
        try {
            ControladorPresentacio CtrlPresentacio = new ControladorPresentacio();
            CtrlPresentacio.vistaPrincipal.setVisible(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
