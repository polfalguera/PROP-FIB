package FONTS.Test;
import FONTS.Domini.ControladorDomini;
import org.junit.BeforeClass;
import org.junit.Test;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControladorDominiTest {
    private static ControladorDomini queryCrearDocumentTest;
    private static ControladorDomini queryCrearDocumentPathTest;
    private static ControladorDomini queryEliminarDocumentTest;
    private static ControladorDomini queryModificarAutorTest;
    private static ControladorDomini queryModificarTitolTest;
    private static ControladorDomini queryModificarContingutTest;
    private static ControladorDomini queryModificarContingutPathTest;
    private static ControladorDomini queryLlistarTitolsAutorTest;
    private static ControladorDomini queryLlistarAutorsPrefixTest;
    private static ControladorDomini queryGetContingutDocumentTest;
    private static ControladorDomini queryObtenirKSemblantsTest;
    private static ControladorDomini queryObtenirKRellevantsTest;
    private static ControladorDomini queryCrearExpressioBooleanaTest;
    private static ControladorDomini queryEliminarExpressioBooleanaTest;
    private static ControladorDomini queryModificarExpressioBooleanaTest;
    private static ControladorDomini queryConsultaExpressioBooleanaTest;
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
        //Creació del document per a queryModificarContingutPathTest.
        try {
            queryModificarContingutPathTest = new ControladorDomini();
            queryModificarContingutPathTest.queryCrearDocument("Pol","El test de modificar contingut path","Contingut");
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
        //Creació de document per a queryObtenirKSemblantsTest.
        try {
            queryObtenirKSemblantsTest = new ControladorDomini();
            queryObtenirKSemblantsTest.queryCrearDocument("Pol","títol 1","Les maduixes són les fruites més fresques del mercat.");
            queryObtenirKSemblantsTest.queryCrearDocument("Marc","títol 2","Les fruites més fresques que existeixen actualment són les taronges.");
            queryObtenirKSemblantsTest.queryCrearDocument("Alex","títol 3","Els cotxes elèctrics contribueixen en el benestar del planeta.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació de document per a queryObtenirKRellevantsTest.
        try {
            queryObtenirKRellevantsTest = new ControladorDomini();
            queryObtenirKRellevantsTest.queryCrearDocument("Pol","títol 1","Les maduixes són les fruites més fresques del mercat.");
            queryObtenirKRellevantsTest.queryCrearDocument("Marc","títol 2","Les fruites més fresques que existeixen actualment són les taronges.");
            queryObtenirKRellevantsTest.queryCrearDocument("Alex","títol 3","Els cotxes elèctrics contribueixen en el benestar del planeta.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació d'expressió booleana per a queryEliminarExpressioBooleana.
        try {
            queryEliminarExpressioBooleanaTest = new ControladorDomini();
            queryEliminarExpressioBooleanaTest.queryCrearExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació d'expressió booleana per a queryModificarExpressioBooleana.
        try {
            queryModificarExpressioBooleanaTest = new ControladorDomini();
            queryModificarExpressioBooleanaTest.queryCrearExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Creació de documents per a queryConsultaExpressioBooleanaTest.
        try {
            //"{pol pau} & (\"hola i adéu\" | pep) & !joan"
            queryConsultaExpressioBooleanaTest = new ControladorDomini();
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Pol","títol 1", "hola i adéu li va dir en pol al pau.");
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Marc","títol 2","hola i deu li va dir en pol al joan.");
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Alex","títol 3","hola i adéu li va dir en pep al joan.");
            queryConsultaExpressioBooleanaTest.queryCrearDocument("Jin","títol 4","hola i deu els va dir en pep al pol i al pau.");
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
     * Test que comprova que un document es creï correctament afegint el contingut a partir d'un path.
     */
    @Test
    public void queryCrearDocumentPathTest() {
        try {
            queryCrearDocumentPathTest = new ControladorDomini();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        boolean existeix = queryCrearDocumentPathTest.queryExisteixDocument("Pol","El test de crear amb path");
        assertEquals(false,existeix);
        try {
            String path = Paths.get("DATA/data.txt").toAbsolutePath().toString();
            queryCrearDocumentPathTest.queryCrearDocumentPath("Pol","El test de crear",path);
            existeix = queryCrearDocumentPathTest.queryExisteixDocument("Pol", "El test de crear");
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
     * Test que comprova que el contingut d'un document es modifica correctament afegint el nou contingut a partir d'un path.
     */
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
            List<String> documentsRetornats0 = queryObtenirKSemblantsTest.queryObtenirKSemblants("Pol","títol 1",2,0);
            List<String> documentsRetornats1 = queryObtenirKSemblantsTest.queryObtenirKSemblants("Pol","títol 1",2,1);
            assertEquals(documentsEsperats,documentsRetornats0);
            assertEquals(documentsEsperats,documentsRetornats1);
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
            List<String> documentsRetornats0 = queryObtenirKSemblantsTest.queryObtenirKRellevants("maduixa fruites",2,0);
            List<String> documentsRetornats1 = queryObtenirKSemblantsTest.queryObtenirKRellevants("maduixa fruites",2,1);
            assertEquals(documentsEsperats,documentsRetornats0);
            assertEquals(documentsEsperats,documentsRetornats1);
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
            queryCrearExpressioBooleanaTest = new ControladorDomini();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        boolean existeix = queryCrearExpressioBooleanaTest.queryExisteixExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
        assertEquals(false,existeix);
        try {
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
        boolean existeix = queryEliminarExpressioBooleanaTest.queryExisteixExpressioBooleana("{pol pau marc} & (\"hola adéu\" | pep) & !joan");
        assertEquals(true,existeix);
        try {
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
        String antigaEx = "{pol pau marc} & (\"hola adéu\" | pep) & !joan";
        String novaEx = "{jin lluc fruita} & (\"benvingut a la FIB\") & !pastanaga";
        boolean existeixAntigaEx = queryModificarExpressioBooleanaTest.queryExisteixExpressioBooleana(antigaEx);
        boolean existeixNovaEx = queryModificarExpressioBooleanaTest.queryExisteixExpressioBooleana(novaEx);
        assertEquals(true,existeixAntigaEx);
        assertEquals(false,existeixNovaEx);

        try {
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
            List<String> documentsRetornats = queryConsultaExpressioBooleanaTest.queryConsultaExpressioBooleana("{pol pau} & (\"hola i adéu\" | pep) & !joan");
            assertEquals(documentsEsperats,documentsRetornats);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}