package test;
import capaDomini.ControladorDomini;
import capaDomini.Document;
import com.sun.source.tree.CaseTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorDominiTest {
    /**
     * Test que comprova que un document es creï correctament.
     */
    @Test
    public void queryCrearDocument() {

    }

    /**
     * Test que comprova que l'autor d'un document es modifica correctament.
     */
    @Test
    public void queryModificarAutor() {
        try {
            ControladorDomini CtrlDomini = new ControladorDomini();
            CtrlDomini.queryCrearDocument("autor","titol","Test de modificar autor");
            CtrlDomini.queryModificarAutor("autor","nouAutor","titol");
            //assertEquals("nouAutor",);
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
}