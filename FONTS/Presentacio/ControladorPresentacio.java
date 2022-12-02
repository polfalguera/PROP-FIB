package FONTS.Presentacio;

import FONTS.Domini.ControladorDomini;

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

    public static void main (String[] args) {
        try {
            ControladorPresentacio CtrlPresentacio = new ControladorPresentacio();
            CtrlPresentacio.vistaPrincipal.setVisible(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
