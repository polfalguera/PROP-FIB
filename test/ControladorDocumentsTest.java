package test;

import org.junit.BeforeClass;
import org.junit.Test;
import capaDomini.ControladorDocuments;

import java.util.*;

import static org.junit.Assert.*;

public class ControladorDocumentsTest {
    private static ControladorDocuments testEliminaDocument;
    private static ControladorDocuments testModificaAutor;

    private static ControladorDocuments testModificaTitol;
    private static ControladorDocuments testLlistaTitols;
    private static ControladorDocuments testLlistaAutorsPrefix;
    private static ControladorDocuments testIndexDocument;

    private static ControladorDocuments testGetAutorTitolIndex;
    @BeforeClass
    public static void inicialitzacio() {
        //Creacio del document per testEliminarDocument()
        testEliminaDocument = new ControladorDocuments();
        try {
            testEliminaDocument.crearDocument("Marc", "El test d'eliminar");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del document per testModificarAutor()
        testModificaAutor = new ControladorDocuments();
        try {
            testModificaAutor.crearDocument("Marc", "El test de modificar autor");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del document per testModificarTitol()
        testModificaTitol = new ControladorDocuments();
        try {
            testModificaTitol.crearDocument("Marc", "El test de modificar titol");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio dels documents per TestLlistarTitolsAutor()
        testLlistaTitols = new ControladorDocuments();
        try {
            testLlistaTitols.crearDocument("Marc", "Titol 1");
            testLlistaTitols.crearDocument("Marc", "Titol 2");
            testLlistaTitols.crearDocument("Marc", "Titol 3");
            testLlistaTitols.crearDocument("Lluc", "Titol 4");
            testLlistaTitols.crearDocument("Mar", "Titol 5");
            testLlistaTitols.crearDocument("Marcel", "Titol 1");
            testLlistaTitols.crearDocument("Marc", "Titol 6");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio dels documents per TestLlistarAutorsPrefix()
        testLlistaAutorsPrefix = new ControladorDocuments();
        try {
            testLlistaAutorsPrefix.crearDocument("Marc", "Titol 1");
            testLlistaAutorsPrefix.crearDocument("Mar", "Titol 2");
            testLlistaAutorsPrefix.crearDocument("Marcel", "Titol 3");
            testLlistaAutorsPrefix.crearDocument("Marta", "Titol 4");
            testLlistaAutorsPrefix.crearDocument("Pol", "Titol 5");
            testLlistaAutorsPrefix.crearDocument("Lluc", "Titol 1");
            testLlistaAutorsPrefix.crearDocument("Marc", "Titol 6");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del document per TestIndexDocuement()
        testIndexDocument = new ControladorDocuments();
        try {
            testIndexDocument.crearDocument("Marc", "Document amb index 0");
            testIndexDocument.crearDocument("Marc", "Document amb index 1");
            testIndexDocument.crearDocument("Marc", "Document amb index 2");
            testIndexDocument.crearDocument("Marc", "Document amb index 3");
            testIndexDocument.crearDocument("Marc", "Document amb index 4");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Creacio del document per TestGetAutorTitolIndex

        testGetAutorTitolIndex = new ControladorDocuments();
        try {
            testGetAutorTitolIndex.crearDocument("Marc", "Document amb index 0");
            testGetAutorTitolIndex.crearDocument("Marc", "Document amb index 1");
            testGetAutorTitolIndex.crearDocument("Marc", "Document amb index 2");
            testGetAutorTitolIndex.crearDocument("Marc", "Document amb index 3");
            testGetAutorTitolIndex.crearDocument("Marc", "Document amb index 4");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     *
     */
    @Test
    public void testCrearDocument() {
        ControladorDocuments testCreaDocument = new ControladorDocuments();
        boolean existeix = testCreaDocument.existeixDocument("Marc", "El test de crear");
        assertEquals(existeix, false);
        try {
            testCreaDocument.crearDocument("Marc", "El test de crear");
            existeix = testCreaDocument.existeixDocument("Marc", "El test de crear");
            assertEquals(existeix, true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *
     */
    @Test
    public void testEliminarDocument() {
        boolean existeix = testEliminaDocument.existeixDocument("Marc", "El test d'eliminar");
        assertEquals(existeix, true);
        try {
            testEliminaDocument.eliminarDocument("Marc", "El test d'eliminar");
            existeix = testEliminaDocument.existeixDocument("Marc", "El test d'eliminar");
            assertEquals(existeix, false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *
     */
    @Test
    public void testModificarAutor() {
        String autor = "Marc";
        String titol = "El test de modificar autor";
        String nouAutor = "Lluc";
        boolean existeixMarc = testModificaAutor.existeixDocument(autor, titol);
        boolean existeixLluc = testModificaAutor.existeixDocument(nouAutor, titol);
        assertEquals(existeixMarc, true);
        assertEquals(existeixLluc, false);
        try {
            testModificaAutor.modificarAutor(autor, nouAutor, titol);
            existeixMarc = testModificaAutor.existeixDocument(autor, titol);
            existeixLluc = testModificaAutor.existeixDocument(nouAutor, titol);
            assertEquals(existeixMarc, false);
            assertEquals(existeixLluc, true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *
     */
    @Test
    public void testModificarTitol() {
        String autor = "Marc";
        String titol = "El test de modificar titol";
        String nouTitol = "El test de modificar titol modificat";
        boolean existeixTitol = testModificaTitol.existeixDocument(autor, titol);
        boolean existeixNouTitol = testModificaTitol.existeixDocument(autor, nouTitol);
        assertEquals(existeixTitol, true);
        assertEquals(existeixNouTitol, false);
        try {
            testModificaTitol.modificarTitol(autor, titol, nouTitol);
            existeixTitol = testModificaTitol.existeixDocument(autor, titol);
            existeixNouTitol = testModificaTitol.existeixDocument(autor, nouTitol);
            assertEquals(existeixTitol, false);
            assertEquals(existeixNouTitol, true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *
     */
    @Test
    public void TestLlistarTitolsAutor() {
        List<String> titolsEsperats = new ArrayList<String>();
        titolsEsperats.add("Titol 1");
        titolsEsperats.add("Titol 2");
        titolsEsperats.add("Titol 3");
        titolsEsperats.add("Titol 6");
        try {
            List<String> titolsRetornats =  testLlistaTitols.llistarTitolsAutor("Marc");
            int i = 0;
            while ( i < titolsRetornats.size() && i < titolsEsperats.size()) {
                assertEquals(titolsRetornats.get(i), titolsEsperats.get(i));
                ++i;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    /**
     *
     */
    @Test
    public void TestLlistarAutorsPrefix() {
        List<String> autorsEsperats = new ArrayList<String>();
        autorsEsperats.add("Mar");
        autorsEsperats.add("Marc");
        autorsEsperats.add("Marcel");
        autorsEsperats.add("Marta");
        String prefix = "Mar";
        try {
            List<String> autorsRetornats = testLlistaAutorsPrefix.llistarAutorsPrefix(prefix);
            int i = 0;
            while ( i < autorsRetornats.size() && i < autorsEsperats.size()) {
                assertEquals(autorsRetornats.get(i), autorsEsperats.get(i));
                ++i;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *
     */
    @Test
    public void TestIndexDocument() {
        String autor = "Marc";
        String titol = "Document amb index 2";
        try {
            int indexRetornat =  testIndexDocument.indexDocument(autor, titol);
            assertEquals(indexRetornat, 2);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *
     */
    @Test
    public void TestGetAutorTitolIndex() {
        String autorEsperat = "Marc";
        String titolEsperat = "Document amb index 2";
        int index = 2;
        try {
            List<String> AutorTitolRetornat =  testIndexDocument.getAutorTitolIndex(index);
            assertEquals(AutorTitolRetornat.get(0), autorEsperat);
            assertEquals(AutorTitolRetornat.get(1), titolEsperat);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}