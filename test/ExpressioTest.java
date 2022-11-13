package test;
import capaDomini.Expressio;

import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;


public class ExpressioTest {
    /**
     * Test que comprova que donat una expressio s'ha creat correctament l'expressio
     */
    @Test
    public void testisEs_correcte() throws Exception {
        try {
            Expressio aux = new Expressio("hola");
            Expressio aux1 = new Expressio("hola & adeu");
            assertTrue(aux.isEs_correcte());
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
            Expressio aux = new Expressio("hola & adeu");
            Expressio.BinaryTree BinaryTree = new Expressio.BinaryTree();
            BinaryTree.addNodePre(true,"&");
            BinaryTree.addNodePre(true,"hola");
            BinaryTree.addNodePre(true,"adeu");
            assertTrue(aux.equals(aux.getTheTree().getRoot(), BinaryTree.getRoot()));
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
            Expressio aux = new Expressio();

            Expressio.BinaryTree tree1 = aux.getTheTree();
            tree1.addNodePre(true,"&");
            tree1.addNodePre(true,"hola");
            tree1.addNodePre(true,"adeu");
            Expressio.BinaryTree tree2 = new Expressio.BinaryTree();
            tree2.addNodePre(true,"&");
            tree2.addNodePre(true,"hola");
            tree2.addNodePre(true,"adeu");
            assertTrue(aux.equals(tree1.getRoot(), tree2.getRoot()));
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
            Expressio aux = new Expressio();

            Expressio.BinaryTree tree1 = aux.getTheTree();
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
            assertTrue(aux.equals(tree1.getRoot(), tree2.getRoot()));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }



}
