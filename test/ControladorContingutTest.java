package test;
import capaDomini.ControladorDocuments;
import org.junit.BeforeClass;
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
    private static ControladorContingut getConjuntContinguts;
    private static ControladorContingut obtenirParaulesContingut;
    private static ControladorContingut eliminarContingut;
    private static ControladorContingut getContingut;
    private static ControladorContingut kRellevants;
    private static ControladorContingut modificarContingut;
    private static ControladorContingut modificarContingutPath;

    @BeforeClass
    public static void inicialitzacio() {
        //Creacio del contingut per a getConjuntContinguts()
        try {
            getConjuntContinguts = new ControladorContingut();
            getConjuntContinguts.afegirContingut("contingut 1.");
            getConjuntContinguts.afegirContingut("contingut 2., ");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a obtenirParaulesContingut()
        try {
            obtenirParaulesContingut = new ControladorContingut();
            obtenirParaulesContingut.afegirContingut("prova 1.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a eliminarContingut()
        try {
            eliminarContingut = new ControladorContingut();
            eliminarContingut.afegirContingut("prova 1.");
            eliminarContingut.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a getContingut()
        try {
            getContingut = new ControladorContingut();
            getContingut.afegirContingut("prova 1.");
            getContingut.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a kRellevants()
        try {
            kRellevants = new ControladorContingut();
            kRellevants.afegirContingut("test 1.");
            kRellevants.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a modificarContingut()
        try {
            modificarContingut = new ControladorContingut();
            modificarContingut.afegirContingut("test 1.");
            modificarContingut.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a modificarContingutPath()
        try {
            modificarContingutPath = new ControladorContingut();
            modificarContingutPath.afegirContingut("test 1.");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    @Test
    public void getConjuntContinguts() {
        try {
            List<String> esperat = new ArrayList<String>();
            esperat.add("contingut 1.");
            esperat.add("contingut 2., ");
            assertEquals(esperat, getConjuntContinguts.getConjuntContinguts());
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
            String [] esperat = {"prova", "1"};
            assertArrayEquals(esperat, obtenirParaulesContingut.obtenirParaulesContingut(0));
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
            assertEquals("prova 1.", eliminarContingut.getContingut(0));
            int n = eliminarContingut.getConjuntContinguts().size();
            eliminarContingut.eliminarContingut(0);
            assertEquals("prova 2.", eliminarContingut.getContingut(0));
            assertEquals(n-1, eliminarContingut.getConjuntContinguts().size());
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
            assertEquals("prova 2.", getContingut.getContingut(1));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que es retorna el k elements més rellevants ordenats correctament amb l'assignació de pesos.
     */
    @Test
    public void kRellevants() {
        try {

            String [] paraules = {"test"};
            int [] res = kRellevants.kRellevants(paraules, 1, 0);
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
            assertEquals("test 1.", modificarContingut.getContingut(0));
            modificarContingut.modificarContingut(0, "prova 1.");
            assertEquals("prova 1.", modificarContingut.getContingut(0));
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
            assertEquals("test 1.", modificarContingutPath.getContingut(0));
            String path = Paths.get("data/data.txt").toAbsolutePath().toString();
            modificarContingutPath.modificarContingutPath(0, path);
            assertEquals("prova 1.", modificarContingutPath.getContingut(0));
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