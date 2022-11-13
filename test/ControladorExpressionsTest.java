package test;

import capaDomini.ControladorExpressions;
import capaDomini.Expressio;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControladorExpressionsTest {
    /**
     * Test que comprova que donat una expressio s'ha creat correctament l'expressio
     */
    @Test
    public void testcrearExpressio() throws Exception {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try{
            Expressio aux = ctrlExpre.crearExpressio("hola & adeu");
            boolean result = aux.isEs_correcte();
            assertTrue("Test Crear Expressio",result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Test que comprova que donat una expressio s'ha creat correctament l'expressio
     */
    @Test
    public void testgetCjtExpressions() throws Exception {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try{
            ctrlExpre.anadir_expressio("hola & adeu");
            ctrlExpre.anadir_expressio("hola");
            ctrlExpre.anadir_expressio("adeu");
            assertTrue("Test getCjtExpressio",ctrlExpre.ExistExpressio("hola & adeu"));
            assertTrue("Test getCjtExpressio",ctrlExpre.ExistExpressio("hola"));
            assertTrue("Test getCjtExpressio",ctrlExpre.ExistExpressio("adeu"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que retorna una expressio correctament
     */
    @Test
    public void testgetExpressio() throws Exception {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try {
            ctrlExpre.anadir_expressio("hola & adeu");
            Expressio result = ctrlExpre.getExpressio("hola & adeu");
            assertNotNull("Test get Expressio",result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que donat una expressio existeix correctament
     */
    @Test
    public void testexistExpressio() throws Exception {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try {
            ctrlExpre.anadir_expressio("hola & adeu");
            boolean result = ctrlExpre.ExistExpressio("hola & adeu");
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
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try {
            ctrlExpre.anadir_expressio("hola & adeu");
            ctrlExpre.anadir_expressio("hola");
            ctrlExpre.anadir_expressio("adeu");
            assertEquals(3,ctrlExpre.getNumExpressions());
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
    /**
     * Test que comprova que donat una expressio s'ha afegit correctament l'expressio
     */
    @Test
    public void testanadir_expressio() throws Exception {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try {
            ctrlExpre.anadir_expressio("hola & adeu");
            assertTrue("Test anadir_expressio",ctrlExpre.ExistExpressio("hola & adeu"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
    /**
     * Test que comprova que donat una expressio s'ha eliminat correctament l'expressio
     */
    @Test
    public void testdeleteExpressio() throws Exception {
        String ex = "hola & adeu";
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try {
            ctrlExpre.anadir_expressio(ex);
            ctrlExpre.deleteExpressio(ex);
            assertEquals(0,ctrlExpre.getNumExpressions());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que donat una expressio s'ha modificat correctament l'expressio
     */
    @Test
    public void testsetExpressio() throws Exception {
        String modificar = "hola & adeu";
        String nova_ex = "hola | adeu & que";
        try {
            ControladorExpressions ctrlExpre = new ControladorExpressions();
            ctrlExpre.anadir_expressio(modificar);
            ctrlExpre.setExpressio(modificar,nova_ex);
            assertFalse("Test set expressio",ctrlExpre.ExistExpressio("hola & adeu"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Test que comprova que una consulta retorna els documents correctament
     */
    @Test
    public void testConsultaExpressioBooleana() throws Exception {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        try {
            List<String> cont = new ArrayList<>();

            cont.add("hola adeu soc joan. M'agrada.");
            cont.add("m'agrada les coses blaves i el joan. Adeu i hola joan");
            cont.add("hola soc en Sol. Estem tots.");
            List<Integer> result = ctrlExpre.ConsultaExpressioBooleana("(hola | adeu) & !joan",cont);
            assertEquals(1,result.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}