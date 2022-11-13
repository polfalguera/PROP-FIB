package test;
import capaDomini.ControladorExpressions;
import capaDomini.Expressio;

import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;


public class ExpressioTest {

    private static Expressio is_Correcte;

    private static Expressio getTree;

    private static Expressio equalTree;

    private static Expressio nodeAddPre;

    /**
     * Test que comprova que donat una expressio s'ha creat correctament l'expressio
     */
    @Test
    public void testisEs_correcte() throws Exception {
        try {
            is_Correcte = new Expressio("hola");
            Expressio aux1 = new Expressio("hola & adeu");
            assertTrue(is_Correcte.isEs_correcte());
            assertTrue(aux1.isEs_correcte());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que retorna un arbre bintree correctament
     */
    @Test
    public void testgetTree() throws Exception {
        try {
            getTree = new Expressio("hola & adeu");
            Expressio.BinaryTree BinaryTree = new Expressio.BinaryTree();
            BinaryTree.addNodePre(true,"&");
            BinaryTree.addNodePre(true,"hola");
            BinaryTree.addNodePre(true,"adeu");
            assertTrue(getTree.equals(getTree.getTheTree().getRoot(), BinaryTree.getRoot()));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Test que comprova que retorna un arbre bintree correctament
     */
    @Test
    public void testEqualTree() throws Exception {
        try {
            equalTree= new Expressio();

            Expressio.BinaryTree tree1 = equalTree.getTheTree();
            tree1.addNodePre(true,"&");
            tree1.addNodePre(true,"hola");
            tree1.addNodePre(true,"adeu");
            Expressio.BinaryTree tree2 = new Expressio.BinaryTree();
            tree2.addNodePre(true,"&");
            tree2.addNodePre(true,"hola");
            tree2.addNodePre(true,"adeu");
            assertTrue(equalTree.equals(tree1.getRoot(), tree2.getRoot()));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Test que comprova que afegeix els nodes correctament
     */
    @Test
    public void testaddNodePreTree() throws Exception {
        try {
            nodeAddPre = new Expressio();

            Expressio.BinaryTree tree1 = nodeAddPre.getTheTree();
            tree1.addNodePre(true,"&");
            tree1.addNodePre(true,"|");
            tree1.addNodePre(true,"adeu");
            tree1.addNodePre(true,"hola");
            tree1.addNodePre(true,"passatgers");

            Expressio.BinaryTree tree2 = new Expressio.BinaryTree();
            tree2.addNodePre(true,"&");
            tree2.addNodePre(true,"|");
            tree2.addNodePre(true,"adeu");
            tree2.addNodePre(true,"hola");
            tree2.addNodePre(true,"passatgers");
            assertTrue(nodeAddPre.equals(tree1.getRoot(), tree2.getRoot()));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }



}
