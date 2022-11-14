package FONTS.Domini;

import java.util.*;
/**
 * Representa una expressio.
 * Una expressio conte un conjunt d'expressions booleanes formades pels operadors "&" "|" i "!".
 * @author haonan jin
 */

public class Expressio {
    /**
     * Representa la expressio emmagatzemades en forma d'un arbol binari.
     * */
    private BinaryTree theTree;
    private boolean es_correcte;
    /**
     * Constructora d'una expressio buida.
     * */
    public Expressio() {
        this.theTree = new BinaryTree();
        this.es_correcte = false;
    }

    /**
     * Constructora d'una expressio.
     * @param ex es l'expressio donat pel usuari.
     * */
    public Expressio(String ex) throws Exception {
        if (ex.equals("")) {
            throw new Exception("Expressio buida");
        }
        String aux = "("+ex+")";
        if (esta_ben_Formalitzat(aux)) {
            this.theTree = new BinaryTree();
            tractarExpressio(aux,this.theTree);
            this.es_correcte = true;
        }else {
            this.es_correcte = false;
        }
    }
    /**
     * Consultora de l'expressio esta ben formalitzat
     *
     * @return Retorna si l'expressio esta ben formalitzat
     * */
    public boolean isEs_correcte() {
        return es_correcte;
    }

    /**
     * Consultora de l'expressio en forma d'arbol binari.
     *
     * @return Retorna l'expressio en forma d'arbol binari.
     */
    public BinaryTree getTheTree() {
        return theTree;
    }
    /**
     * Consultora si dos arbres son iguals
     *
     * @return Retorna si dos arbres son iguals.
     */
    public boolean equals(Node root1, Node root2) {
        // Shortcut for reference equality; also handles equals(null, null)
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.word.equals(root2.word) &&
                equals(root1.leftChild, root2.leftChild) &&
                equals(root1.rightChild, root2.rightChild);
    }

    /**
     * Representa una expressio booleana en forma d'arbre binari.
     * */
    public static class BinaryTree {
        /**
         * Representa el node del arbol binari
         * */
         Node root;
        /**
         * @return Retorna el root del arbre
         * */
        public Node getRoot() {
            return root;
        }

        /**
         * Afegeix nodes de manera preordreTranseversar a l'arbre.
         * @param c el valor del node que volem afegir.
         * @param word l'expressio que volem afegir.
         * */
        public void addNodePre(boolean c, String word) {
            //System.out.println(word);
            Node newNode = new Node(c,word);
            if (root == null) {
                root = newNode;
            }
            else {
                Node focusNode = root;

                Node parent;

                int i = 0;
                Stack<Node> st = new Stack<Node>();
                while (true) {
                    parent = focusNode;
                    if (focusNode.leftChild != null && es_op(focusNode.leftChild.word)) {
                        st.push(focusNode);
                        //System.out.println(1);
                        focusNode = focusNode.leftChild;
                    }
                    else if (focusNode.leftChild == null && es_op(focusNode.word)) {
                        focusNode = focusNode.leftChild;
                        //System.out.println(2);
                        if (focusNode == null) {
                            parent.leftChild = newNode;
                            return;
                        }
                    }
                    else if(focusNode.rightChild == null && es_op(focusNode.word)){
                        focusNode = focusNode.rightChild;
                        //System.out.println(3);
                        if (focusNode == null) {
                            parent.rightChild = newNode;
                            return;
                        }
                    }
                    else if (focusNode.rightChild != null && es_op(focusNode.rightChild.word)) {
                        //System.out.println(4);
                        focusNode = focusNode.rightChild;
                    }
                    else {
                        Node aux = st.peek();
                        focusNode = aux.rightChild;
                        st.pop();
                        if (focusNode == null) {
                            aux.rightChild = newNode;
                            return;
                        }
                    }

                }
            }
        }
        /**
         * Consultora de si es tracta d'un operador.
         *
         * @param op es una paraula o una sequencia de paraules.
         * @return Retorna si el parametre op es tracta d'un operador.
         * */
        private boolean es_op(String op) {
            return op == "&" || op == "|" || op == "!";
        }
    }

    /**
     * Consultora de si el caracter donat es tracta d'un operador.
     *
     * @param op es una paraula o una sequencia de paraules.
     * @return Retorna si el parametre op es tracta d'un operador("&","|").
     * */
    private static boolean es_operador(String op) {
        return op.equals("&") || op.equals("|");
    }

    /**
     * Consultora de si la sequencia de paraules donat conte un operador.
     *
     * @param op es una paraula o una sequencia de paraules.
     * @return Retorna si el parametre op conte d'un operador.
     * */
    private static boolean contiene_op(String op) {
        return op.contains("&") || op.contains("|");
    }
    /**
     * Converteix l'expressio donada pel usuari en una expressio de forma d'arbre binari.
     *
     * @param ex es l'expressio donat pel usuari.
     * @param r es l'arbre de l'expressio.
     * */
    private static void tractarExpressio(String ex, BinaryTree r) {
        if (ex != "") {
            //Per fer la tractacio quan hi ha un parentesis
            if (ex.substring(0,1).equals("(")) {
                boolean trobat = false;
                int i = 1;
                //variable aux per determinar el parentesis
                int aux = 0;
                while (i < ex.length() && !trobat) {
                    if (ex.substring(i,i+1).equals("(")) {
                        ++aux;
                    }
                    if (ex.substring(i,i+1).equals(")")) {
                        --aux;
                    }
                    if (es_operador(ex.substring(i,i+1)) && aux == 0) {
                        trobat = true;
                        if (ex.substring(i,i+1).equals("&")) {
                            r.addNodePre(true,"&");
                        }else {
                            r.addNodePre(true,"|");
                        }
                    }
                    ++i;
                }
                //Si em trobat un operador signifca que l'expressio es pot partir en 2 expressions
                if (trobat) {
                    //System.out.println("parentesis " + ex.substring(1,i-2));
                    tractarExpressio(ex.substring(1,i-2),r);
                    //System.out.println("parentesis 2 " + ex.substring(i+1,ex.length()-1));
                    tractarExpressio("("+ex.substring(i+1,ex.length()-1)+")",r);
                }
                //Si no em trobat un operador significa que no es pot partir i l'expressio que ens dona
                //molt probablement es d'aquesta manera ((p1 & p2 & p3)), es a dir, que el primer parentesis
                //no hi ha cap operador perque es pugui partir en () & ().
                else if (!trobat) {
                    tractarExpressio(ex.substring(1,ex.length()-1),r);
                }
            }
            //Quan no hi ha parentesis, i si trobat que hi ha un operador que el pot partir en 2 expressions
            //D'aquesta manera  << p1 & p2 | p3 >>, el parteix d'aquesta manera p1 & (p2 |p3).
            else if (contiene_op(ex)) {
                boolean trobat = false;
                int i = 1;
                while (i < ex.length() && !trobat) {
                    if (es_operador(ex.substring(i,i+1))) {
                        trobat = true;
                        if (ex.substring(i,i+1).equals("&")) {
                            r.addNodePre(true,"&");
                        }else {
                            r.addNodePre(true,"|");
                        }
                    }
                    ++i;
                }
                //System.out.println("expressio 1 " + ex.substring(0,i-2));
                tractarExpressio(ex.substring(0,i-2),r);

                if (contiene_op(ex.substring(i+1,ex.length()))) {
                    tractarExpressio("("+ex.substring(i+1,ex.length())+")",r);
                }
                else {
                    tractarExpressio(ex.substring(i+1,ex.length()),r);
                }
                //System.out.println("expressio 2 " + ex.substring(i+1,ex.length()));
            }
            //El cas quan tenim {p1 p2 p3 p4}
            //Cada espai suposa un & en el nostre arbre.
            else if (ex.substring(0,1).equals("{")) {
                ex = ex.substring(1,ex.length()-1);
                String[] aux = ex.split(" ");
                int i = 0;
                while ( i < aux.length-1) {
                    r.addNodePre(true,"&");
                    r.addNodePre(true,aux[i]);
                    ++i;
                }
                r.addNodePre(true,aux[i]);
            }
            //El cas quan tenim un "expresio"
            else if (ex.substring(0,1).equals("\"")) {
                r.addNodePre(true,ex.substring(1,ex.length()-1));
            }
            //El cas quan tenim un !, lo que fa es afegir un ! i el fill left afegeix un "."
            //i lo que s'ha de valoarar al fill right
            else if (ex.substring(0,1).equals("!")) {
                r.addNodePre(true,"!");
                r.addNodePre(true,".");
                tractarExpressio(ex.substring(1,ex.length()),r);
            }
            // Quan nomes queden paraules sense operadors
            else {
                String[] aux = ex.split(" ");
                if (aux[0] == "") {
                    r.addNodePre(true,aux[1]);
                }else {
                    r.addNodePre(true,aux[0]);
                }
            }
        }
        else {
            return;
        }

    }

    /**
     * Consultora
     *
     * @param expr es l'expressio donat pel usuari
     * @return Retorna si l'expressio donada esta ben formalitzat.
     */
    private boolean esta_ben_Formalitzat(String expr) throws Exception{
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack
                = new ArrayDeque<Character>();

        //variable aux controla el nombre de " que hi ha a l'expressio
        int aux = 0;
        //variable aux1 controla el nombre de { que hi ha a l'expressio
        int aux1 = 0;
        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            //Tractar els casos que els operadors estan d'estar separats per espais
            if ( aux1 == 0 && aux%2 == 0 && (x == '&' || x == '|') ) {
                if (expr.charAt(i-1) != ' ' || expr.charAt(i+1) != ' ') {
                    throw new Exception("Error, Els operadors & i | han de estar separats per espais");
                }
            }
            //Tractar els casos {}
            if (x == '{') {
                ++aux1;
            }else if (x == '}') {
                --aux1;
            }
            //Tractar els casos d'aquesta manera (hola a adeu)-> expressio mal escrita;
            if (x == ' ' && aux%2 == 0 && aux1 == 0) {
                if ( !((expr.charAt(i-1) == '&' || expr.charAt(i-1) == '|') ||
                        (expr.charAt(i+1) == '&' || expr.charAt(i+1) == '|'))) {
                    throw new Exception("Error, Has de servir operadors per separar paraules");
                }
            }

            //El cas quan hi ha "
            if (x == '\"') {
                ++aux;
            }

            if ( aux%2 == 0 &&  (x == '(' || x == '{') ) {
                // Push the element in the stack
                stack.push(x);
                continue;
            }
            // If current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty())
                return false;
            char check;
            if (aux%2 == 0) {
                switch (x) {
                    case ')':
                        check = stack.pop();
                        if (check == '{')
                            return false;
                        break;
                    case '}':
                        check = stack.pop();
                        if (check == '(' )
                            return false;
                        break;
                }
            }
        }
        // Check Empty Stack
        if (!stack.isEmpty()) {
            throw new Exception("Error, Expressio mal formalitzat");
        }
        return true;
    }

    /**
     * Representa un node d'un arbre binari.
     * */
      static class Node {
        /**
         * Representa la paraula o una sequencia de paraules del node.
         * */
        String word;
        /**
         * Representa el valor booleana del node.
         * */
        boolean correct;
        /**
         * Representa el fill esquerre del node pare.
         * */
        Node leftChild;
        /**
         * Representa el fill dret del node pare.
         * */
        Node rightChild;
        /**
         * Constructora d'un node
         * @param correct es el valor booleana del node.
         * @param word es la paraula o la sequencia de paraules del node
         * */
        Node(boolean correct,String word){
            this.correct = correct;
            this.word = word;
            leftChild = null;
            rightChild = null;
        }
        /**
         * Retorna el valor booleana del node i la sequencia de paraules
         * @return el valor booleana del node i la sequencia de paraules.
         * */
        public String toString() {
            return word + " " + correct;
        }

    }



}
