package drivers;

import capaDomini.ControladorDocuments;
import java.util.*;

public class DriverControladorDocuments {

    private static ControladorDocuments CtrlDocuments;
    private static final String numero_comanda = "0";
    private static final String crearDocument = "1";
    private static final String eliminarDocument = "2";
    private static final String modificarAutor = "3";
    private static final String modificarTitol = "4";
    private static final String llistarTitolsAutor = "5";
    private static final String llistarAutorsPrefix = "6";
    private static final String tancar_driver = "7";

    private static final String AJUDA = "Números associats a cada comanada del driver:  \n"+
            " "+crearDocument+" Crea un document\n"+
            " "+eliminarDocument+" Elimina un document\n"+
            " "+modificarAutor+" Actualitza l'autor d'un document\n"+
            " "+modificarTitol+" Actualitza el titol d'un document\n"+
            " "+llistarTitolsAutor+" Llista els títols dels documents d'un autor\n"+
            " "+llistarAutorsPrefix+" Llista els autors que comencen per un prefix donat\n"+
            " "+tancar_driver+" Finalitza l'execució del driver";
    /**
     * Llegex un conjunt de documents introduits per titol i autor
     * */
    private static void llegirConjuntDocuments() {
        CtrlDocuments = new ControladorDocuments();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digues el nombre de documents que vols introduir: ");
        int nDocs = sc.nextInt();
        sc.nextLine();
        System.out.println("Introdueix " + nDocs + " parelles autor-títol, cada autor i titol en una línia diferent:");
        for (int i = 0; i < nDocs; ++i) {
            try  {
                System.out.println("Autor: ");
                String autor = sc.nextLine();
                System.out.println("Titol: ");
                String titol = sc.nextLine();
                CtrlDocuments.crearDocument(autor, titol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
    }

    /**
     * Crea un document
     * */
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

    /**
     * Elimina un document
     * */
    public static void testEliminarDocument() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a eliminar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a eliminar: ");
        String titol = sc.nextLine();

        try {
            CtrlDocuments.eliminarDocument(autor,titol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Document eliminat correctament.");

    }

    /**
     * Modifica l'autor d'un document
     * */
    public static void testModificarAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a modificar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a modificar: ");
        String titol = sc.nextLine();
        System.out.println("Escriu el nou autor a assignar: ");
        String nouAutor = sc.nextLine();

        try {
            CtrlDocuments.modificarAutor(autor, nouAutor, titol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Autor del document actualitzat correctament.");
    }

    /**
     * Modifica el titol d'un document
     * */
    public static void testModificarTitol() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a modificar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a modificar: ");
        String titol = sc.nextLine();
        System.out.println("Escriu el nou titol a assignar: ");
        String noutitol = sc.nextLine();

        try {
            CtrlDocuments.modificarTitol(autor, titol, noutitol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Titol del document actualitzat correctament.");
    }

    /**
     * Llista els titols d'un autor
     * */
    public static void testLlistarTitolsAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor desitjat");
        String autor = sc.nextLine();
        try {
            List<String> titols = CtrlDocuments.llistarTitolsAutor(autor);
            System.out.println("Llista de titols de l'autor " + autor + ":");
            titols.forEach((titol) -> {
                System.out.println(titol);
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Titols de l'autor llistats correctament");
    }

    /**
     * Llista els autors que contenen un prefix
     * */
    public static void testLlistarAutorsPrefix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu el prefix desitjat");
        String prefix = sc.nextLine();
        try {
            List<String> autors = CtrlDocuments.llistarAutorsPrefix(prefix);
            System.out.println("Llista d'autors amb prefix " + prefix + ":");
            autors.forEach((autor) -> {
                System.out.println(autor);
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Titols de l'autor llistats correctament");
    }

    /**
     * Main on es van llegint les comandes a executar
     * */
    public static void main(String[] args) {
        DriverControladorDocuments dcd = new DriverControladorDocuments();
        System.out.println("\n| Driver de la classe ControladorDocuments |\n");
        System.out.println("Abans de testejar funcionalitats cal declarar un conjunt de documents sobre els quals treballar.");
        llegirConjuntDocuments();

        String comanda = numero_comanda;
        while (comanda != tancar_driver) {
            System.out.println("Selecciona el número de la comanda que vulguis executar: ");
            System.out.println(AJUDA+"\n");
            Scanner sc1 = new Scanner(System.in);
            comanda = sc1.nextLine();

            switch(comanda) {
                case numero_comanda:
                    System.out.println(dcd.AJUDA);
                    break;
                case crearDocument:
                    dcd.testCrearDocument();
                    break;
                case eliminarDocument:
                    dcd.testEliminarDocument();
                    break;
                case modificarAutor:
                    dcd.testModificarAutor();
                    break;
                case modificarTitol:
                    dcd.testModificarTitol();
                    break;
                case llistarTitolsAutor:
                    dcd.testLlistarTitolsAutor();
                    break;
                case llistarAutorsPrefix:
                    dcd.testLlistarAutorsPrefix();
                    break;
                case tancar_driver:
                    System.out.println("| Execució del driver finalitzada |");
                    return;
                default:
                    System.out.println("ERROR: Número de comanda no vàlid.");
                    break;
            }
        }

    }

}
