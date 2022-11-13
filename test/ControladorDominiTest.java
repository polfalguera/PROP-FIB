package test;
import capaDomini.ControladorDomini;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.ldap.Control;

import java.awt.*;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControladorDominiTest {
    private static ControladorDomini queryCrearDocumentTest;
    private static ControladorDomini queryEliminarDocumentTest;
    private static ControladorDomini queryModificarAutorTest;
    private static ControladorDomini queryModificarTitolTest;
    private static ControladorDomini queryModificarContingutTest;
    private static ControladorDomini queryLlistarTitolsAutorTest;
    private static ControladorDomini queryLlistarAutorsPrefixTest;
    private static ControladorDomini queryGetContingutDocumentTest;
    @BeforeClass
    public static void inicialitzacio() {
        //Creació del document per queryEliminarDocumentTest.
        try {
            queryEliminarDocumentTest = new ControladorDomini();
            queryEliminarDocumentTest.queryCrearDocument("Pol","El test d'eliminar","Contingut");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació del document per queryModificarAutorTest.
        try {
            queryModificarAutorTest = new ControladorDomini();
            queryModificarAutorTest.queryCrearDocument("Pol","El test de modificar autor","Contingut");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació del document per queryModificarTitolTest.
        try {
            queryModificarTitolTest = new ControladorDomini();
            queryModificarTitolTest.queryCrearDocument("Pol","El test de modificar títol","Contingut");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació del document per a queryModificarContingutTest.
        try {
            queryModificarContingutTest = new ControladorDomini();
            queryModificarContingutTest.queryCrearDocument("Pol","El test de modificar contingut","Contingut");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        //Creació dels documents per a queryLlistarTitolsAutorTest.
        try {
            queryLlistarTitolsAutorTest = new ControladorDomini();
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 1", "Contingut 1");
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 2", "Contingut 2");
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 3", "Contingut 3");
            queryLlistarTitolsAutorTest.queryCrearDocument("Alex","Títol 4", "Contingut 4");
            queryLlistarTitolsAutorTest.queryCrearDocument("Jin","Títol 1", "Contingut 1");
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 6", "Contingut 6");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació dels documents per a queryLlistarAutorsPrefixTest.
        try {
            queryLlistarAutorsPrefixTest = new ControladorDomini();
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marc", "Titol 1","Contingut 1");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Mar", "Titol 2","Contingut 2");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marcel", "Titol 3","Contingut 3");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marta", "Titol 4","Contingut 4");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Pol", "Titol 5","Contingut 5");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Lluc", "Titol 1","Contingut 1");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marc", "Titol 6","Contingut 6");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació del document per a queryGetContingutDocumentTest.
        try {
            queryGetContingutDocumentTest = new ControladorDomini();
            queryGetContingutDocumentTest.queryCrearDocument("Pol","El test d'obtenir contingut","Contingut a obtenir");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que un document es creï correctament.
     */
    @Test
    public void queryCrearDocumentTest() {
        try {
            queryCrearDocumentTest = new ControladorDomini();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        boolean existeix = queryCrearDocumentTest.queryExisteixDocument("Pol","El test de crear");
        assertEquals(false,existeix);
        try {
            queryCrearDocumentTest.queryCrearDocument("Pol","El test de crear","Contingut");
            existeix = queryCrearDocumentTest.queryExisteixDocument("Pol", "El test de crear");
            assertEquals(existeix, true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que un document s'elimini correctament.
     */
    @Test
    public void queryEliminarDocumentTest() {
        boolean existeix = queryEliminarDocumentTest.queryExisteixDocument("Pol","El test d'eliminar");
        assertEquals(true,existeix);
        try {
            queryEliminarDocumentTest.queryEliminarDocument("Pol","El test d'eliminar");
            existeix = queryEliminarDocumentTest.queryExisteixDocument("Pol","El test d'eliminar");
            assertEquals(false,existeix);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que l'autor d'un document es modifica correctament.
     */
    @Test
    public void queryModificarAutorTest() {
        boolean existeixPol = queryModificarAutorTest.queryExisteixDocument("Pol","El test de modificar autor");
        boolean existeixAlex = queryModificarAutorTest.queryExisteixDocument("Alex","El test de modificar autor");
        assertEquals(true,existeixPol);
        assertEquals(false, existeixAlex);
        try {
            queryModificarAutorTest.queryModificarAutor("Pol","Alex","El test de modificar autor");
            existeixPol = queryModificarAutorTest.queryExisteixDocument("Pol","El test de modificar autor");
            existeixAlex = queryModificarAutorTest.queryExisteixDocument("Alex","El test de modificar autor");
            assertEquals(false,existeixPol);
            assertEquals(true, existeixAlex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que el títol d'un document es modifica correctament.
     */
    @Test
    public void queryModificarTitolTest() {
        boolean existeixAnticTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol");
        boolean existeixNouTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol modificat");
        assertEquals(true,existeixAnticTitol);
        assertEquals(false, existeixNouTitol);
        try {
            queryModificarTitolTest.queryModificarTitol("Pol","El test de modificar títol","El test de modificar títol modificat");
            existeixAnticTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol");
            existeixNouTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol modificat");
            assertEquals(false,existeixAnticTitol);
            assertEquals(true, existeixNouTitol);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que el contingut d'un document es modifica correctament.
     */
    @Test
    public void queryModificarContingutTest() {
        try {
            assertEquals("Contingut",queryModificarContingutTest.queryGetContingutDocument("Pol","El test de modificar contingut"));
            queryModificarContingutTest.queryModificarContingut("Pol","El test de modificar contingut","Contingut modificat");
            assertEquals("Contingut modificat",queryModificarContingutTest.queryGetContingutDocument("Pol","El test de modificar contingut"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que es llisten correctament els títols d'un autor.
     */
    @Test
    public void queryLlistarTitolsAutorTest() {
        List<String> titolsEsperats = new ArrayList<String>();
        titolsEsperats.add("Títol 1");
        titolsEsperats.add("Títol 2");
        titolsEsperats.add("Títol 3");
        titolsEsperats.add("Títol 6");

        try {
            List<String> titolsRetornats = queryLlistarTitolsAutorTest.queryLlistarTitolsAutor("Pol");
            assertEquals(titolsEsperats,titolsRetornats);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que es llisten correctament els autors que contenen un prefix donat.
     */
    @Test
    public void queryLlistarAutorsPrefixTest() {
        List<String> autorsEsperats = new ArrayList<String>();
        autorsEsperats.add("Mar");
        autorsEsperats.add("Marc");
        autorsEsperats.add("Marcel");
        autorsEsperats.add("Marta");
        try {
            List<String> autorsRetornats = queryLlistarAutorsPrefixTest.queryLlistarAutorsPrefix("Mar");
            assertEquals(autorsEsperats,autorsRetornats);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que s'obté el contingut d'un document correctament.
     */
    @Test
    public void queryGetContingutDocumentTest() {
        try {
            String contingutRetornat = queryGetContingutDocumentTest.queryGetContingutDocument("Pol","El test d'obtenir contingut");
            assertEquals("Contingut a obtenir",contingutRetornat);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}