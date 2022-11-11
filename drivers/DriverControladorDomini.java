package drivers;

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

    public static void llegirConjuntDocuments(int op) {
        Scanner sc = new Scanner(System.in);
        if (op == 0) {
            System.out.println("Introdueix parelles autor-títol, cada element en una línia diferent:");
            int i = 0;
            while (i < 3) {
                try  {
                    CtrlDomini.queryCrearDocument(sc.nextLine(),sc.nextLine());
                } catch (Exception e) {
                    System.out.println(e.toString());
                    return;
                }
                ++i;
            }
        }
        else {
            System.out.println("Funció PATH no implementada. :(");
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

        if (CtrlDomini.queryExisteixDocument(autor,titol)) {
            try {
                CtrlDomini.queryEliminarDocument(autor,titol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
            System.out.println("Document eliminat correctament.");
        }
        else System.out.println("ERROR: no existeix cap document amb l'autor i el títol introduïts.");
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

        if (CtrlDomini.queryExisteixDocument(autor,titol)) {
            try {
                CtrlDomini.queryModificarAutor(autor, nouAutor, titol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
            System.out.println("Autor del document actualitzat correctament.");
        }
        else System.out.println("ERROR: no existeix cap document amb l'autor i el títol introduïts.");
    }

    public static void testQueryModificarTitol() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu l'autor del document a modificar: ");
        String autor = sc.nextLine();
        System.out.println("Escriu el títol del document a modificar: ");
        String titol = sc.nextLine();
        System.out.println("Escriu el nou títol a assignar: ");
        String nouTitol = sc.nextLine();

        if (CtrlDomini.queryExisteixDocument(autor,titol)) {
            try {
                CtrlDomini.queryModificarTitol(autor, titol, nouTitol);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
            System.out.println("Títol del document actualitzat correctament.");
        }
        else System.out.println("ERROR: no existeix cap document amb l'autor i el títol introduïts.");
    }

    public static void main(String[] args) {
        DriverControladorDomini dcd = new DriverControladorDomini();
        CtrlDomini = new ControladorDomini();
        System.out.println("\n| Driver de la classe ControladorDomini |\n");
        System.out.println("Abans de testejar funcionalitats cal declarar un conjunt de documents sobre els quals testejar, un conjunt" +
                           " de continguts i un controlador d'expressions booleanes.\n");
        //System.out.println("Proporcioneu un conjunt de documents, introduint el seu autor i el seu títol i el contingut.");
        System.out.println("Escull una opció:\n- 0 Introduir autors, titols i contingut per terminal\n" +
                           "- 1 Escriure path d'un fitxer .txt amb els autors, títols i continguts");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        llegirConjuntDocuments(op);

        System.out.println(AJUDA+"\n");
        int comanda = 0;
        while (comanda != 10) {
            System.out.println("Selecciona el número de la comanda que vulguis executar: ");
            Scanner sc1 = new Scanner(System.in);
            comanda = sc1.nextInt();

            switch(comanda) {
                case 0:
                    System.out.println(dcd.AJUDA);
                    break;
                case 1:
                    dcd.testQueryCrearDocument();
                    break;
                case 2:
                    dcd.testQueryEliminarDocument();
                    break;
                case 3:
                    dcd.testQueryModificarAutor();
                    break;
                case 4:
                    dcd.testQueryModificarTitol();
                    break;

            }
        }

    }

}
