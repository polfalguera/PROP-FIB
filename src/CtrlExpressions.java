package CtrlDomini;
import java.util.*;

/**
 * Representa el controlador de la classe Expressio
 * @author haonan jin
 */

public class CtrlExpressions {
    /**
     * Representa el conjunt d'expressions.
     */
    private HashMap<String,Expressio> expressions;

    /**
     * Constructora d'un conjunt d'expressions
     */
    public CtrlExpressions() {
        this.expressions = new HashMap<>();
    }

    /**
     * Consultora del conjunt d'expressions.
     * @return Retorna el conjunt d'expressions.
     */
    public HashMap<String, Expressio> getCjtExpressions() {
        return expressions;
    }

    /**
     * Consultora d'una expressio.
     * @param ex es l'expressio donat pel usuari
     * @return Retorna l'expressio arbre binari indentificat pel el parametre ex.
     */
    public Expressio getExpressio(String ex) {
        return expressions.get(ex);
    }

    /**
     * Consultora d'una expressio.
     * @param key es l'expressio donat pel usuari
     * @return Retorna l'expressio indentificat pel el parametre ex.
     */
    public boolean ExistKey(String key) {
        if (expressions.containsKey(key)) return true;
        return false;
    }
    /**
     * Consultora del nombre d'expressions.
     * @return Retorna el nombre d'expressions.
     */
    public int getNumExpressions() {
        return expressions.size();
    }

    /**
     * Modificadora
     * @param key es l'indentificador d'una expressio d'arbre binari.
     * Elimina l'expressio d'arbol binari del conjunt d'expressions indentificat per key.
     */
    public boolean deleteExpressio(String key) {
        if (expressions.containsKey(key)) {
            expressions.remove(key);
            System.out.println("S'ha eliminat correctament l'expressio");
            return true;
        }
        System.out.println("No existeix l'expressio donat");
        return false;
    }
    /**
     * Consultora
     *
     * @param expr es l'expressio donat pel usuari
     * @return Retorna si l'expressio donada esta ben formalitzat.
     */
    private boolean areBracketsBalanced(String expr){
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
                    System.out.println("Els operadors & i | han de estar separats per espais");
                    return false;
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
                    System.out.println("Has de servir operadors per separar paraules");
                    return false;
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
        return (stack.isEmpty());
    }
    /**
     * Afegeix l'expressio donada pel usuari al conjunt d'expressions
     * @param ex es l'expressio donat pel usuari.
     */
    public boolean anadir_expressio(String ex) {
        if (!expressions.containsKey(ex) && areBracketsBalanced(ex)) {
            Expressio new_ex = new Expressio(ex);
            //ex -> l'expressio de frase
            //new_ex -> l'expression convertida en arbol binari
            expressions.put(ex,new_ex);
            System.out.println("S'ha afegit correctament la nova expressio");
            return true;
        }
        System.out.println("No s'ha pogut afegir l'expressio donada");
        return  false;
    }
    public void anadir_expressio123(String ex) {
        Expressio new_ex = new Expressio(ex);
        //ex -> l'expressio de frase
        //new_ex -> l'expression convertida en arbol binari
        expressions.put(ex,new_ex);
        System.out.println("S'ha afegit correctament la nova expressio");
    }
    /**
     * Consultora si la frase cumpleix l'expressio d'arbol binari.
     *
     * @param r es el node root d'un arbre d'expressio.
     * @param frase es l'arbre de l'expressio.
     * @return Retorna true si la frase cumpleix l'expressio d'arbol binari.
     * */
    //parametre frase sera una llista amb un conjunt de frases
    public boolean evaluateTree(Expressio.Node r, String frase) {
        if (r != null) {
            if (r.leftChild == null && r.rightChild == null) return frase.contains(r.word);

            //Si es | evalua el fill left o el right
            if (r.word.equals("|")) {
                return evaluateTree(r.leftChild,frase) || evaluateTree(r.rightChild,frase);
            }
            //Si es & ha d'evaluar el fill left i right
            else if (r.word.equals("&")) {
                return evaluateTree(r.leftChild,frase) && evaluateTree(r.rightChild,frase);
            }
            //Si es ! nomes ha de evaluar el fill right, (el dret es nomes un "." per tenir una estructura de binTree)
            else if (r.word.equals("!")) {
                return !(evaluateTree(r.rightChild,frase));
            }
        }
        return false;
    }

    public void ConsultaExpreBooleana(String ex,ArrayList<String> docs) {
        if (anadir_expressio(ex)) {

        }

    }




    public static void main(String[] args) {
        CtrlExpressions asd = new CtrlExpressions();
        if (asd.anadir_expressio("({p1 p2 p3} & (\"hola adeu\" | pep) & !joan)")) {
            System.out.println(123);
            Expressio aux = asd.expressions.get("({p1 p2 p3} & (\"hola adeu\" | pep) & !joan)");

            if ( asd.evaluateTree(aux.getTheTree().root, "p1 p2 p3 pep")) {
                System.out.println("frase trobat");
            }
        }


    }




}
