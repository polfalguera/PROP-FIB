package FONTS.Test;
import FONTS.Domini.ControladorDomini;
import FONTS.Presentacio.ControladorPresentacio;
import org.junit.BeforeClass;
import org.junit.Test;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControladorDominiTest {
    /**
     * Test que comprova que un document es creï correctament.
     */
    @Test
    public void queryCrearDocumentTest() {
        try {
            ControladorDomini queryCrearDocumentTest = new ControladorDomini();
            boolean existeix = queryCrearDocumentTest.queryExisteixDocument("Pol","El test de crear");
            assertEquals(false,existeix);
            queryCrearDocumentTest.queryCrearDocument("Pol","El test de crear","Contingut");
            existeix = queryCrearDocumentTest.queryExisteixDocument("Pol", "El test de crear");
            assertEquals(existeix, true);
            queryCrearDocumentTest.queryEliminarDocument("Pol", "El test de crear");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que un document es creï correctament afegint el contingut a partir d'un path.
     */
    /* Ara que ja tenim el controlador de format i la capa de persistencia ja no es necessaria
    // aquest test
    @Test
    public void queryCrearDocumentPathTest() {
        try {
            ControladorDomini queryCrearDocumentPathTest = new ControladorDomini();
            boolean existeix = queryCrearDocumentPathTest.queryExisteixDocument("Pol","El test de crear amb path");
            assertEquals(false,existeix);
            String path = Paths.get("DATA/data.txt").toAbsolutePath().toString();
            queryCrearDocumentPathTest.queryCrearDocumentPath("Pol","El test de crear",path);
            existeix = queryCrearDocumentPathTest.queryExisteixDocument("Pol", "El test de crear");
            assertEquals(existeix, true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }*/

    /**
     * Test que comprova que un document s'elimini correctament.
     */
    @Test
    public void queryEliminarDocumentTest() {
        try {
            ControladorDomini queryEliminarDocumentTest = new ControladorDomini();
            queryEliminarDocumentTest.queryCrearDocument("Pol","El test d'eliminar","Contingut");
            boolean existeix = queryEliminarDocumentTest.queryExisteixDocument("Pol","El test d'eliminar");
            assertEquals(true,existeix);
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
        try {
            ControladorDomini queryModificarAutorTest = new ControladorDomini();
            queryModificarAutorTest.queryCrearDocument("Pol","El test de modificar autor","Contingut");
            boolean existeixPol = queryModificarAutorTest.queryExisteixDocument("Pol","El test de modificar autor");
            boolean existeixAlex = queryModificarAutorTest.queryExisteixDocument("Alex","El test de modificar autor");
            assertEquals(true,existeixPol);
            assertEquals(false, existeixAlex);
            queryModificarAutorTest.queryModificarAutor("Pol","Alex","El test de modificar autor");
            existeixPol = queryModificarAutorTest.queryExisteixDocument("Pol","El test de modificar autor");
            existeixAlex = queryModificarAutorTest.queryExisteixDocument("Alex","El test de modificar autor");
            assertEquals(false,existeixPol);
            assertEquals(true, existeixAlex);
            queryModificarAutorTest.queryEliminarDocument("Alex", "El test de modificar autor");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que el títol d'un document es modifica correctament.
     */
    @Test
    public void queryModificarTitolTest() {
        try {
            ControladorDomini queryModificarTitolTest = new ControladorDomini();
            queryModificarTitolTest.queryCrearDocument("Pol","El test de modificar títol","Contingut");
            boolean existeixAnticTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol");
            boolean existeixNouTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol modificat");
            assertEquals(true,existeixAnticTitol);
            assertEquals(false, existeixNouTitol);
            queryModificarTitolTest.queryModificarTitol("Pol","El test de modificar títol","El test de modificar títol modificat");
            existeixAnticTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol");
            existeixNouTitol = queryModificarTitolTest.queryExisteixDocument("Pol","El test de modificar títol modificat");
            assertEquals(false,existeixAnticTitol);
            assertEquals(true, existeixNouTitol);
            queryModificarTitolTest.queryEliminarDocument("Pol", "El test de modificar títol modificat");
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
            ControladorDomini queryModificarContingutTest = new ControladorDomini();
            queryModificarContingutTest.queryCrearDocument("Pol","El test de modificar contingut","Contingut");
            assertEquals("Contingut",queryModificarContingutTest.queryGetContingutDocument("Pol","El test de modificar contingut"));
            queryModificarContingutTest.queryModificarContingut("Pol","El test de modificar contingut","Contingut modificat");
            assertEquals("Contingut modificat",queryModificarContingutTest.queryGetContingutDocument("Pol","El test de modificar contingut"));
            queryModificarContingutTest.queryEliminarDocument("Pol", "El test de modificar contingut");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que el contingut d'un document es modifica correctament afegint el nou contingut a partir d'un path.
     */
    /*
    @Test
    public void queryModificarContingutPathTest() {
        try {
            String path = Paths.get("DATA/data.txt").toAbsolutePath().toString();
            assertEquals("Contingut",queryModificarContingutPathTest.queryGetContingutDocument("Pol","El test de modificar contingut path"));
            queryModificarContingutPathTest.queryModificarContingutPath("Pol","El test de modificar contingut path",path);
            assertEquals("prova 1.",queryModificarContingutPathTest.queryGetContingutDocument("Pol","El test de modificar contingut path"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    */
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
            ControladorDomini queryLlistarTitolsAutorTest = new ControladorDomini();
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 1", "Contingut 1");
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 2", "Contingut 2");
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 3", "Contingut 3");
            queryLlistarTitolsAutorTest.queryCrearDocument("Alex","Títol 4", "Contingut 4");
            queryLlistarTitolsAutorTest.queryCrearDocument("Jin","Títol 1", "Contingut 1");
            queryLlistarTitolsAutorTest.queryCrearDocument("Pol","Títol 6", "Contingut 6");
            List<String> titolsRetornats = queryLlistarTitolsAutorTest.queryLlistarTitolsAutor("Pol");
            assertEquals(titolsEsperats,titolsRetornats);
            queryLlistarTitolsAutorTest.queryEliminarDocument("Pol","Títol 1");
            queryLlistarTitolsAutorTest.queryEliminarDocument("Pol","Títol 2");
            queryLlistarTitolsAutorTest.queryEliminarDocument("Pol","Títol 3");
            queryLlistarTitolsAutorTest.queryEliminarDocument("Alex","Títol 4");
            queryLlistarTitolsAutorTest.queryEliminarDocument("Jin","Títol 1");
            queryLlistarTitolsAutorTest.queryEliminarDocument("Pol","Títol 6");
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
            ControladorDomini queryLlistarAutorsPrefixTest = new ControladorDomini();
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marc", "Titol 1","Contingut 1");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Mar", "Titol 2","Contingut 2");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marcel", "Titol 3","Contingut 3");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marta", "Titol 4","Contingut 4");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Pol", "Titol 5","Contingut 5");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Lluc", "Titol 1","Contingut 1");
            queryLlistarAutorsPrefixTest.queryCrearDocument("Marc", "Titol 6","Contingut 6");
            List<String> autorsRetornats = queryLlistarAutorsPrefixTest.queryLlistarAutorsPrefix("Mar");
            assertEquals(autorsEsperats,autorsRetornats);
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Marc", "Titol 1");
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Mar", "Titol 2");
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Marcel", "Titol 3");
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Marta", "Titol 4");
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Pol", "Titol 5");
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Lluc", "Titol 1");
            queryLlistarAutorsPrefixTest.queryEliminarDocument("Marc", "Titol 6");
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
            ControladorDomini queryGetContingutDocumentTest = new ControladorDomini();
            queryGetContingutDocumentTest.queryCrearDocument("Pol","El test d'obtenir contingut","Contingut a obtenir");
            String contingutRetornat = queryGetContingutDocumentTest.queryGetContingutDocument("Pol","El test d'obtenir contingut");
            assertEquals("Contingut a obtenir",contingutRetornat);
            queryGetContingutDocumentTest.queryEliminarDocument("Pol", "El test d'obtenir contingut");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que donat un document es retornen els k documents més semblants.
     */
    @Test
    public void queryObtenirKSemblantsTest() {
        List<String> documentsEsperats = new ArrayList<String>();
        documentsEsperats.add("Pol");
        documentsEsperats.add("títol 1");
        documentsEsperats.add("Marc");
        documentsEsperats.add("títol 2");
        try {
            ControladorDomini queryObtenirKSemblantsTest = new ControladorDomini();
            queryObtenirKSemblantsTest.queryCrearDocument("Pol","títol 1","Les maduixes són les fruites més fresques del mercat.");
            queryObtenirKSemblantsTest.queryCrearDocument("Marc","títol 2","Les fruites més fresques que existeixen actualment són les taronges.");
            queryObtenirKSemblantsTest.queryCrearDocument("Alex","títol 3","Els cotxes elèctrics contribueixen en el benestar del planeta.");
            List<String> documentsRetornats0 = queryObtenirKSemblantsTest.queryObtenirKSemblants("Pol","títol 1",2,0);
            List<String> documentsRetornats1 = queryObtenirKSemblantsTest.queryObtenirKSemblants("Pol","títol 1",2,1);
            assertEquals(documentsEsperats,documentsRetornats0);
            assertEquals(documentsEsperats,documentsRetornats1);
            queryObtenirKSemblantsTest.queryEliminarDocument("Pol", "títol 1");
            queryObtenirKSemblantsTest.queryEliminarDocument("Marc", "títol 2");
            queryObtenirKSemblantsTest.queryEliminarDocument("Alex", "títol 3");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que donades unes paraules es retornen els k documents més rellevants.
     */
    @Test
    public void queryObtenirKRellevantsTest() {
        List<String> documentsEsperats = new ArrayList<String>();
        documentsEsperats.add("Pol");
        documentsEsperats.add("títol 1");
        documentsEsperats.add("Marc");
        documentsEsperats.add("títol 2");
        try {
            ControladorDomini queryObtenirKRellevantsTest = new ControladorDomini();
            queryObtenirKRellevantsTest.queryCrearDocument("Pol","títol 1","Les maduixes són les fruites més fresques del mercat.");
            queryObtenirKRellevantsTest.queryCrearDocument("Marc","títol 2","Les fruites més fresques que existeixen actualment són les taronges.");
            queryObtenirKRellevantsTest.queryCrearDocument("Alex","títol 3","Els cotxes elèctrics contribueixen en el benestar del planeta.");
            List<String> documentsRetornats0 = queryObtenirKRellevantsTest.queryObtenirKRellevants("maduixa fruites",2,0);
            List<String> documentsRetornats1 = queryObtenirKRellevantsTest.queryObtenirKRellevants("maduixa fruites",2,1);
            assertEquals(documentsEsperats,documentsRetornats0);
            assertEquals(documentsEsperats,documentsRetornats1);
            queryObtenirKRellevantsTest.queryEliminarDocument("Pol", "títol 1");
            queryObtenirKRellevantsTest.queryEliminarDocument("Marc", "títol 2");
            queryObtenirKRellevantsTest.queryEliminarDocument("Alex", "títol 3");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que es crea una expressió booleana correctament.
     */
    @Test
    public void queryCrearExpressioBooleanaTest() {
        try {
            ControladorDomini queryCrearExpressioBooleanaTest = new ControladorDomini();
            boolean existeix = queryCrearExpressioBooleanaTest.queryExisteixExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            assertEquals(false,existeix);
            queryCrearExpressioBooleanaTest.queryCrearExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            existeix = queryCrearExpressioBooleanaTest.queryExisteixExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            assertEquals(true,existeix);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que s'elimina una expressió booleana correctament.
     */
    @Test
    public void queryEliminarExpressioBooleanaTest() {
        try {
            ControladorDomini queryEliminarExpressioBooleanaTest = new ControladorDomini();
            queryEliminarExpressioBooleanaTest.queryCrearExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            boolean existeix = queryEliminarExpressioBooleanaTest.queryExisteixExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            assertEquals(true,existeix);
            queryEliminarExpressioBooleanaTest.queryEliminarExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            existeix = queryEliminarExpressioBooleanaTest.queryExisteixExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            assertEquals(false,existeix);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que es modifica una expressió booleana correctament.
     */
    @Test
    public void queryModificarExpressioBooleanaTest() {
        try {
            ControladorDomini queryModificarExpressioBooleanaTest = new ControladorDomini();
            queryModificarExpressioBooleanaTest.queryCrearExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
            String antigaEx = "{pol pau marc} & (\"hola adéu\" | pep) & !joan";
            String novaEx = "{jin lluc fruita} & (\"benvingut a la FIB\") & !pastanaga";
            boolean existeixAntigaEx = queryModificarExpressioBooleanaTest.queryExisteixExpressioBooleana(antigaEx);
            boolean existeixNovaEx = queryModificarExpressioBooleanaTest.queryExisteixExpressioBooleana(novaEx);
            assertEquals(true,existeixAntigaEx);
            assertEquals(false,existeixNovaEx);
            queryModificarExpressioBooleanaTest.queryModificarExpressioBooleana(antigaEx,novaEx);
            existeixAntigaEx = queryModificarExpressioBooleanaTest.queryExisteixExpressioBooleana(antigaEx);
            existeixNovaEx = queryModificarExpressioBooleanaTest.queryExisteixExpressioBooleana(novaEx);
            assertEquals(false,existeixAntigaEx);
            assertEquals(true,existeixNovaEx);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que es retornin els documents corresponents donada una expressió booleana.
     */
    @Test
    public void queryConsultarExpressioBooleanaTest() {
        List<String> documentsEsperats = new ArrayList<>();
        documentsEsperats.add("Pol");
        documentsEsperats.add("títol 1");
        documentsEsperats.add("Jin");
        documentsEsperats.add("títol 4");
        try {
            ControladorDomini queryConsultaExpressioBooleanaTest = new ControladorDomini();
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Pol","títol 1", "hola i adéu li va dir en pol al pau.");
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Marc","títol 2","hola i deu li va dir en pol al joan.");
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Alex","títol 3","hola i adéu li va dir en pep al joan.");
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Jin","títol 4","hola i deu els va dir en pep al pol i al pau.");
            List<String> documentsRetornats = queryConsultaExpressioBooleanaTest.queryConsultaExpressioBooleana("{pol pau} & (\"hola i adéu\" | pep) & !joan");
            assertEquals(documentsEsperats,documentsRetornats);
            queryConsultaExpressioBooleanaTest.queryEliminarDocument("Pol", "títol 1");
            queryConsultaExpressioBooleanaTest.queryEliminarDocument("Marc", "títol 2");
            queryConsultaExpressioBooleanaTest.queryEliminarDocument("Alex", "títol 3");
            queryConsultaExpressioBooleanaTest.queryEliminarDocument("Jin", "títol 4");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}