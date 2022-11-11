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
    public void crearExpressio() {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        Expressio aux = ctrlExpre.crearExpressio("hola & adeu");
        boolean result = aux.isEs_correcte();
        assertTrue("Test Crear Expressio",result);
    }
    /**
     * Test que retorna una expressio correctament
     */
    @Test
    public void getExpressio() {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        boolean add = ctrlExpre.anadir_expressio("hola & adeu");
        Expressio result = ctrlExpre.getExpressio("hola & adeu");
        assertNotNull("Test get Expressio",result);
    }
    /**
     * Test que comprova que donat una expressio existeix correctament
     */
    @Test
    public void existExpressio() {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        boolean add = ctrlExpre.anadir_expressio("hola & adeu");
        boolean result = ctrlExpre.ExistExpressio("hola & adeu");
        assertTrue("Test existeix Expressio",result);
    }
    /**
     * Test que comprova que retorna el nombre de expressions creats correctament
     */
    @Test
    public void getNumExpressions() {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        boolean add = ctrlExpre.anadir_expressio("hola & adeu");
        assertEquals(1,ctrlExpre.getNumExpressions());

    }
    /**
     * Test que comprova que donat una expressio s'ha afegit correctament l'expressio
     */
    @Test
    public void anadir_expressio() {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        boolean result = ctrlExpre.anadir_expressio("hola & adeu");
        assertTrue("Test Afegir Expressio",result);
    }
    /**
     * Test que comprova que donat una expressio s'ha eliminat correctament l'expressio
     */
    @Test
    public void deleteExpressio() {
        String ex = "hola & adeu";
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        boolean result = ctrlExpre.anadir_expressio(ex);
        boolean fin = false;
        if (result) {
            fin = ctrlExpre.deleteExpressio(ex);
        }
        assertTrue("Test Eliminar Expressio",fin);
    }
    /**
     * Test que comprova que donat una expressio s'ha modificat correctament l'expressio
     */
    @Test
    public void setExpressio() {
        String modificar = "hola & adeu";
        String nova_ex = "hola | adeu & que";
        ControladorExpressions ctrlExpre = new ControladorExpressions();

        boolean add = ctrlExpre.anadir_expressio(modificar);
        boolean result = false;
        if (add) {
            result = ctrlExpre.setExpressio(modificar,nova_ex);
        }
        assertTrue("Test Modificar Expressio",result);
    }
    /**
     * Test que comprova que una consulta retorna els documents correctament
     */
    @Test
    public void ConsultaExpressioBooleana() {
        ControladorExpressions ctrlExpre = new ControladorExpressions();
        List<String> cont = new ArrayList<>();

        cont.add("hola adeu soc joan. M'agrada.");
        cont.add("m'agrada les coses blaves i el joan. Adeu i hola joan");
        cont.add("hola soc en Sol. Estem tots.");
        List<Integer> result = ctrlExpre.ConsultaExpressioBooleana("(hola | adeu) & !joan",cont);
        assertEquals(1,result.size());
    }

}