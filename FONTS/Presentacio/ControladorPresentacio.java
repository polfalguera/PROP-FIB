package FONTS.Presentacio;

import FONTS.Domini.ControladorDomini;

public class ControladorPresentacio {

    private ControladorDomini ctrlDomain;

    private final MainView vistaPrincipal;

    public ControladorPresentacio() throws Exception {
        this.ctrlDomain = new ControladorDomini();
        this.vistaPrincipal = new MainView(this);
    }

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                () -> {
                    ControladorPresentacio ctrlPresentation = null;
                    try {
                        ctrlPresentation = new ControladorPresentacio();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    assert ctrlPresentation != null;
                    //ctrlPresentation.openMainView();
                });
    }


}
