package FONTS.Test;
import FONTS.Domini.ConjuntContinguts;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ConjuntContingutsTest {
    /**
     * Test que comprova que es retornen els continguts de forma correcta.
     */
    private static ConjuntContinguts getConjuntContinguts;
    private static ConjuntContinguts obtenirParaulesContingut;
    private static ConjuntContinguts eliminarContingut;
    private static ConjuntContinguts getContingut;
    private static ConjuntContinguts kRellevants;
    private static ConjuntContinguts modificarContingut;
    private static ConjuntContinguts modificarContingutPath;

    @BeforeClass
    public static void inicialitzacio() {
        //Creacio del contingut per a getConjuntContinguts()
        try {
            getConjuntContinguts = new ConjuntContinguts();
            getConjuntContinguts.afegirContingut("contingut 1.");
            getConjuntContinguts.afegirContingut("contingut 2., ");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a obtenirParaulesContingut()
        try {
            obtenirParaulesContingut = new ConjuntContinguts();
            obtenirParaulesContingut.afegirContingut("prova 1.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a eliminarContingut()
        try {
            eliminarContingut = new ConjuntContinguts();
            eliminarContingut.afegirContingut("prova 1.");
            eliminarContingut.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a getContingut()
        try {
            getContingut = new ConjuntContinguts();
            getContingut.afegirContingut("prova 1.");
            getContingut.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a kRellevants()
        try {
            kRellevants = new ConjuntContinguts();
            kRellevants.afegirContingut("test 1.");
            kRellevants.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a modificarContingut()
        try {
            modificarContingut = new ConjuntContinguts();
            modificarContingut.afegirContingut("test 1.");
            modificarContingut.afegirContingut("prova 2.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del contingut per a modificarContingutPath()
        try {
            modificarContingutPath = new ConjuntContinguts();
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
            ConjuntContinguts contingut = new ConjuntContinguts();
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
            String path = Paths.get("DATA/data.txt").toAbsolutePath().toString();
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
            ConjuntContinguts contingut = new ConjuntContinguts();
            String path = Paths.get("DATA/data.txt").toAbsolutePath().toString();
            contingut.afegirContingutPath(path);
            assertEquals("prova 1.", contingut.getContingut(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}