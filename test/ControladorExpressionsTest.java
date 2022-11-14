package test;

import capaDomini.ControladorExpressions;
import capaDomini.Expressio;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControladorExpressionsTest {
    private static ControladorExpressions getCjtExpreesions;
    private static ControladorExpressions ExistExpressio;
    private static ControladorExpressions getNumExpressions;
    private static ControladorExpressions addExpressions;
    private static ControladorExpressions deleteExpressio;
    private static ControladorExpressions setExpressio;
    private static ControladorExpressions ConsultaExpressioBooleana;

    @BeforeClass
    public static void inicialitzacio() {
        try {
            getCjtExpreesions = new ControladorExpressions();
            getCjtExpreesions.anadir_expressio("hola & adeu");
            getCjtExpreesions.anadir_expressio("hola");
            getCjtExpreesions.anadir_expressio("adeu");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            ExistExpressio = new ControladorExpressions();
            ExistExpressio.anadir_expressio("hola & adeu");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            getNumExpressions = new ControladorExpressions();
            getNumExpressions.anadir_expressio("hola & adeu");
            getNumExpressions.anadir_expressio("hola");
            getNumExpressions.anadir_expressio("adeu");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            addExpressions = new ControladorExpressions();
            addExpressions.anadir_expressio("hola & adeu");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            String ex = "hola & adeu";
            deleteExpressio = new ControladorExpressions();
            deleteExpressio.anadir_expressio(ex);
            deleteExpressio.deleteExpressio(ex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            String modificar = "hola & adeu";
            String nova_ex = "hola | adeu & que";
            setExpressio = new ControladorExpressions();
            setExpressio.anadir_expressio(modificar);
            setExpressio.setExpressio(modificar,nova_ex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            ConsultaExpressioBooleana = new ControladorExpressions();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que donat una expressio s'ha creat correctament l'expressio
     */
    @Test
    public void testgetCjtExpressions() throws Exception {
        try{
            assertTrue("Test getCjtExpressio",getCjtExpreesions.ExistExpressio("hola & adeu"));
            assertTrue("Test getCjtExpressio",getCjtExpreesions.ExistExpressio("hola"));
            assertTrue("Test getCjtExpressio",getCjtExpreesions.ExistExpressio("adeu"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que donat una expressio existeix correctament
     */
    @Test
    public void testexistExpressio() throws Exception {
        try {
            boolean result = ExistExpressio.ExistExpressio("hola & adeu");
            assertTrue("Test existeix Expressio",result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que retorna el nombre de expressions creats correctament
     */
    @Test
    public void testgetNumExpressions() throws Exception {
        try {
            assertEquals(3,getNumExpressions.getNumExpressions());
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
    /**
     * Test que comprova que donat una expressio s'ha afegit correctament l'expressio
     */
    @Test
    public void testanadir_expressio() throws Exception {
        try {
            assertTrue("Test anadir_expressio",addExpressions.ExistExpressio("hola & adeu"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
    /**
     * Test que comprova que donat una expressio s'ha eliminat correctament l'expressio
     */
    @Test
    public void testdeleteExpressio() throws Exception {
        try {
            assertEquals(0,deleteExpressio.getNumExpressions());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que donat una expressio s'ha modificat correctament l'expressio
     */
    @Test
    public void testsetExpressio() throws Exception {
        try {
            assertFalse("Test set expressio",setExpressio.ExistExpressio("hola & adeu"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que una consulta retorna els documents correctament
     */
    @Test
    public void testConsultaExpressioBooleana() throws Exception {
        try {
            List<String> cont = new ArrayList<>();
            cont.add("hola adeu soc joan. M'agrada.");
            cont.add("m'agrada les coses blaves i el joan. Adeu i hola joan");
            cont.add("hola soc en Sol. Estem tots.");
            List<Integer> result = ConsultaExpressioBooleana.ConsultaExpressioBooleana("(hola | adeu) & !joan",cont);
            assertEquals(1,result.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}