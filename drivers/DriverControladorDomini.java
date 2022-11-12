package drivers;

import capaDomini.ControladorDocuments;
import capaDomini.ControladorDomini;
import java.util.*;

public class DriverControladorDomini {

    private static ControladorDomini CtrlDomini;

    private static final String numero_comanda = "0";
    private static final String queryCrearDocument = "1";
    private static final String queryEliminarDocument = "2";
    private static final String queryModificarAutor = "3";
    private static final String queryModificarTitol = "4";
    private static final String queryLlistarTitolsAutor = "5";
    private static final String queryLlistarAutorsPrefix = "6";
    private static final String queryGetContingutDocument = "7";
    private static final String queryTObtenirKSemblants = "8";
    private static final String queryConsultaExpressioBooleana = "9";
    private static final String tancar_driver = "10";

    private static final String AJUDA = "\nNúmeros associats a cada comanada del driver:  \n"+
            " "+numero_comanda+" Llista els números associats a cada comanda del driver.\n"+
            " "+queryCrearDocument+" Crea un document.\n"+
            " "+queryEliminarDocument+" Elimina un document.\n"+
            " "+queryModificarAutor+" Actualitza l'autor d'un document.\n"+
            " "+queryModificarTitol+" Actualitza el títol d'un document.\n"+
            " "+queryLlistarTitolsAutor+" Llista els títols dels documents d'un autor.\n"+
            " "+queryLlistarAutorsPrefix+" Llista els autors que comencen per un prefix donat.\n"+
            " "+queryGetContingutDocument+" Donat un document retorna el seu contingut.\n"+
            " "+queryTObtenirKSemblants+" Donat un document i un enter k retorna els k documents més semblants al document d'entrada.\n"+
            " "+queryConsultaExpressioBooleana+" Donada una expressió booleana, retorna els documents que la satisfan."+
            " "+tancar_driver+" Finalitza l'execució del driver";

    private static void llegirConjuntDocuments() {
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
                CtrlDomini.queryCrearDocument(autor, titol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
    }
    public static void testQueryCrearDocument() {
        System.out.println("queryCrearDocument");
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu un autor per al document a crear: ");
        String autor = sc.nextLine();
        System.out.println("Escriu un títol per al document a crear: ");
        String titol = sc.nextLine();
        try  {
            CtrlDomini.queryCrearDocument(autor,titol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Document creat correctament.");
    }

    public static void testQueryEliminarDocument() {
        System.out.println("queryEliminarDocument");
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a eliminar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a eliminar: ");
        String titol = sc.nextLine();

        try {
            CtrlDomini.queryEliminarDocument(autor,titol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Document eliminat correctament.");

    }

    public static void testQueryModificarAutor() {
        System.out.println("queryModificarAutor");
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a modificar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a modificar: ");
        String titol = sc.nextLine();
        System.out.println("Escriu el nou autor a assignar: ");
        String nouAutor = sc.nextLine();

        try {
            CtrlDomini.queryModificarAutor(autor, nouAutor, titol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Autor del document actualitzat correctament.");

    }

    public static void testQueryModificarTitol() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a modificar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a modificar: ");
        String titol = sc.nextLine();
        System.out.println("Escriu el nou títol a assignar: ");
        String nouTitol = sc.nextLine();

        try {
            CtrlDomini.queryModificarTitol(autor, titol, nouTitol);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Títol del document actualitzat correctament.");

    }

    public static void testQueryLlistarTitolsAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del qual llistar els seus títols:");
        String autor = sc.nextLine();
        try {
            List<String> titols = CtrlDomini.queryLlistarTitolsAutor(autor);
            System.out.println("Llista de títols de l'autor "+autor+":\n"+titols);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Títols de l'autor llistats correctament.");
    }

    public static void main(String[] args) {
        DriverControladorDomini dcd = new DriverControladorDomini();
        try {
            CtrlDomini = new ControladorDomini();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("\n| Driver de la classe ControladorDomini |\n");
        System.out.println("Abans de testejar funcionalitats cal declarar un conjunt de documents sobre els quals testejar i un conjunt" +
                           " de continguts.\n");
        llegirConjuntDocuments();

        System.out.println(AJUDA+"\n");
        String comanda = numero_comanda;
        while (comanda != tancar_driver) {
            System.out.println("Selecciona el número de la comanda que vulguis executar: ");
            Scanner sc1 = new Scanner(System.in);
            comanda = sc1.nextLine();

            switch(comanda) {
                case numero_comanda:
                    System.out.println(dcd.AJUDA);
                    break;
                case queryCrearDocument:
                    dcd.testQueryCrearDocument();
                    break;
                case queryEliminarDocument:
                    dcd.testQueryEliminarDocument();
                    break;
                case queryModificarAutor:
                    dcd.testQueryModificarAutor();
                    break;
                case queryModificarTitol:
                    dcd.testQueryModificarTitol();
                    break;
                case queryLlistarTitolsAutor:
                    dcd.testQueryLlistarTitolsAutor();
                    break;

                case tancar_driver:
                    return;

            }
        }

    }

}
