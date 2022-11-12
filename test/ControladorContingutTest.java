package test;
import org.junit.Test;
import capaDomini.ControladorContingut;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ControladorContingutTest {
    /**
     * Test que comprova que es retornen els continguts de forma correcta.
     */
    @Test
    public void getConjuntContinguts() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("contingut 1.");
            contingut.afegirContingut("contingut 2., ");
            List<String> res = contingut.getConjuntContinguts();
            assertEquals("contingut 1.", res.get(0));
            assertEquals("contingut 2., ", res.get(1));
        } catch (Exception e) {
           System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que es retornen les paraules d'un contingut correctament.
     */
    @Test
    public void obtenirParaulesContingut() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("prova 1.");
            String [] esperat = {"prova", "1"};
            assertEquals(esperat, contingut.obtenirParaulesContingut(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que s'elimina correctament un contingut.
     */
    @Test
    public void eliminarContingut() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("prova 1.");
            contingut.afegirContingut("prova 2.");
            contingut.eliminarContingut(0);
            List<String> esperat = new ArrayList<String>();
            esperat.add("prova 2.");
            assertEquals(esperat, contingut.getConjuntContinguts());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que es retorna el contingut amb index i correctament.
     */
    @Test
    public void getContingut() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("prova 1.");
            contingut.afegirContingut("prova 2.");
            assertEquals("prova 2.", contingut.getContingut(1));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que es retorna el k elements més rellevants ordenats correctament amb l'assignació de pesos.
     */
    @Test
    public void termsTfIdf() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("test 1.");
            contingut.afegirContingut("prova 2.");
            String [] paraules = {"test"};
            int [] res = contingut.termsTfIdf(paraules, 1, 0);
            assertEquals(0, res[0]);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que es modifica correctament el contingut amb índex i.
     */
    @Test
    public void modificarContingut() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("test 1.");
            contingut.afegirContingut("prova 2.");
            contingut.modificarContingut(0, "prova 1.");
            assertEquals("prova 1.", contingut.getContingut(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que s'afegeix correctament un nou contingut.
     */
    @Test
    public void afegirContingut() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("prova 1.");
            assertEquals("prova 1.", contingut.getContingut(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que es modifica correctament el contingut amb índex i utilitzant el path.
     */
    @Test
    public void modificarContingutPath() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            contingut.afegirContingut("test 1.");
            String path = Paths.get("data/data.txt").toAbsolutePath().toString();
            contingut.modificarContingutPath(0, path);
            assertEquals("prova 1.", contingut.getContingut(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que s'afegeix correctament un nou contingut utilitzant el path.
     */
    @Test
    public void afegirContingutPath() {
        try {
            ControladorContingut contingut = new ControladorContingut();
            String path = Paths.get("data/data.txt").toAbsolutePath().toString();
            contingut.afegirContingutPath(path);
            assertEquals("prova 1.", contingut.getContingut(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}