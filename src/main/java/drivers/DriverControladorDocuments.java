package drivers;

import capaDomini.ConjuntDocuments;
import java.util.*;

public class DriverControladorDocuments {

    private static final ConjuntDocuments CtrlDocuments = new ConjuntDocuments();
    private static final String numero_comanda = "0";
    private static final String crearDocument = "1";
    private static final String eliminarDocument = "2";
    private static final String modificarAutor = "3";
    private static final String modificarTitol = "4";
    private static final String llistarTitolsAutor = "5";
    private static final String llistarAutorsPrefix = "6";
    private static final String tancar_driver = "7";

    private static final String AJUDA = "Números associats a cada comanada del driver:  \n"+
            " "+numero_comanda+" Llista els números associats a cada comanda del driver\n"+
            " "+crearDocument+" Crea un document\n"+
            " "+eliminarDocument+" Elimina un document\n"+
            " "+modificarAutor+" Actualitza l'autor d'un document\n"+
            " "+modificarTitol+" Actualitza el titol d'un document\n"+
            " "+llistarTitolsAutor+" Llista els títols dels documents d'un autor\n"+
            " "+llistarAutorsPrefix+" Llista els autors que comencen per un prefix donat\n"+
            " "+tancar_driver+" Finalitza l'execució del driver";

    public static void llegirConjuntDocuments(int op) {
        Scanner sc = new Scanner(System.in);
        if (op == 0) {
            System.out.println("Introdueix parelles autor-títol, cada element en una línia diferent:");
            while (sc.hasNext()) {
                try  {
                    CtrlDocuments.crearDocument(sc.nextLine(),sc.nextLine());
                } catch (Exception e) {
                    System.out.println(e.toString());
                    return;
                }
            }
        }
        else {
            System.out.println("Funció PATH no implementada. :(");
        }
    }
    public static void testCrearDocument() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu un autor per al document a crear: ");
        String autor = sc.nextLine();
        System.out.println("Escriu un títol per al document a crear: ");
        String titol = sc.nextLine();
        try  {
            CtrlDocuments.crearDocument(autor,titol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }

        System.out.println("Document creat correctament.");
    }

    public static void testEliminarDocument() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a eliminar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a eliminar: ");
        String titol = sc.nextLine();

        if (CtrlDocuments.existeixDocument(autor,titol)) {
            try {
                CtrlDocuments.eliminarDocument(autor,titol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
            System.out.println("Document eliminat correctament.");
        }
        else System.out.println("ERROR: no existeix cap document amb l'autor i el títol introduïts.");
    }

    public static void testModificarAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a modificar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a modificar: ");
        String titol = sc.nextLine();
        System.out.println("Escriu el nou autor a assignar: ");
        String nouAutor = sc.nextLine();

        if (CtrlDocuments.existeixDocument(autor,titol)) {
            try {
                CtrlDocuments.modificarAutor(autor, nouAutor, titol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
            System.out.println("Autor del document actualitzat correctament.");
        }
        else System.out.println("ERROR: no existeix cap document amb l'autor i el títol introduïts.");
    }

    public static void main(String[] args) {
        DriverControladorDocuments dcd = new DriverControladorDocuments();
        System.out.println("| Driver de la classe ControladorDocuments |\n");
        System.out.println("Abans de testejar funcionalitats cal declarar un conjunt de documents sobre els quals treballar.");
        System.out.println("Proporcioneu un conjunt de documents, introduint el seu autor i el seu títol.");
        System.out.println("Escull una opció:\n- 0 Introduir autors i titols per terminal\n- 1 Escriure path d'un fitxer .txt amb els autors i títols");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        llegirConjuntDocuments(op);

        System.out.println(AJUDA+"\n");
        int comanda = 0;
        while (comanda != 7) {
            System.out.println("Selecciona el número de la comanda que vulguis executar: ");
            Scanner sc1 = new Scanner(System.in);
            comanda = sc1.nextInt();

            switch(comanda) {
                case 0:
                    System.out.println(dcd.AJUDA);
                    break;
                case 1:
                    dcd.testCrearDocument();
                    break;
                case 2:
                    dcd.testEliminarDocument();
                    break;
                case 3:
            }
        }

    }

}
