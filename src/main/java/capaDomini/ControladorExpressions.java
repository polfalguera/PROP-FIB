package capaDomini;

import java.util.*;

/**
 * Representa el controlador de la classe Expressio
 * @author haonan jin
 */

public class ControladorExpressions {
    /**
     * Representa el conjunt d'expressions.
     */
    private HashMap<String,Expressio> expressions;

    /**
     * Constructora d'un conjunt d'expressions
     */
    public ControladorExpressions() {
        this.expressions = new HashMap<>();
    }

    /**
     * Constructora d'una expressio.
     * @param ex es l'expressio donat pel usuari
     * @return Retorna l'expressio arbre binari indentificat pel el parametre ex.
     */
    public Expressio crearExpressio(String ex) {
        Expressio aux = new Expressio(ex);
        return aux;
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
        try {
            return expressions.get(ex);
        }
        catch (Exception e) {
            System.out.println("L'expressio no existeix");
        }
        return null;
    }

    /**
     * Consultora d'una expressio.
     * @param key es l'expressio donat pel usuari
     * @return Retorna l'expressio indentificat pel el parametre ex.
     */
    public boolean ExistExpressio(String key) {
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
     * Afegeix l'expressio donada pel usuari al conjunt d'expressions
     * @param ex es l'expressio donat pel usuari.
     */
    public boolean anadir_expressio(String ex) {
        if (!expressions.containsKey(ex)) {
            Expressio new_ex = new Expressio(ex);
            //ex -> l'expressio de frase
            //new_ex -> l'expression convertida en arbol binari
            if (new_ex.isEs_correcte()) {
                expressions.put(ex,new_ex);
                System.out.println("S'ha afegit correctament la nova expressio");
                return true;
            }
        }
        System.out.println("No s'ha pogut afegir l'expressio donada");
        return  false;
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
     * Modificadora
     * @param key es l'indentificador d'una expressio d'arbre binari.
     * Modifica l'expressio d'arbol binari del conjunt d'expressions indentificat per key.
     */
    public boolean setExpressio(String key,String nova_ex) {
        if (deleteExpressio(key)) {
            if (anadir_expressio(nova_ex)) {
                System.out.println("S'ha modificat coorrectament");
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Consultora si la frase cumpleix l'expressio d'arbol binari.
     *
     * @param r es el node root d'un arbre d'expressio.
     * @param frase es l'arbre de l'expressio.
     * @return Retorna true si la frase cumpleix l'expressio d'arbol binari.
     * */
    //parametre frase sera una llista amb un conjunt de frases
    private boolean evaluateTree(Expressio.Node r, String frase) {
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

    public List<Integer> ConsultaExpressioBooleana(String ex,List<String> cont) {
        List<Integer> id_docs = new ArrayList<>();

        if (anadir_expressio(ex)) {
            Expressio expressio_avaluar = expressions.get(ex);

            //iteracio dels documents
            for (int i = 0; i < cont.size(); ++i) {
                boolean compleix_doc = false;
                String[] frases = cont.get(i).split(".");
                //iteracio de les frases
                for (int j = 0; j  < frases.length && !compleix_doc; ++j) {
                    if (evaluateTree(expressio_avaluar.getTheTree().root, frases[j])) {
                        compleix_doc = true;
                        id_docs.add(i);
                    }
                }
            }
        }
        return id_docs;
    }

}
