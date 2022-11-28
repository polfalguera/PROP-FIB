package FONTS.Presentacio;

import FONTS.Domini.ControladorDomini;

public class ControladorPresentacio {

    private ControladorDomini CtrlDomini;

    private final MainView vistaPrincipal;

    public ControladorPresentacio() throws Exception {
        this.CtrlDomini = new ControladorDomini();
        this.vistaPrincipal = new MainView("Document Manager");
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
