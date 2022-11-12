package drivers;

import capaDomini.ControladorExpressions;
import capaDomini.Expressio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * És el driver de la classe ControladorExpressions.
 * @author haonan.jin
 */
public class DriverControladorExpressions {
    /**
     * Representa la comanda per crear una expressio
     * */
    private static final String CREAEXPRESSIO = "1";

    private static final String ADDEXPRESSIO = "2";

    private static final String DELETEEXPRESSIO = "3";

    private static final String SETEXPRESSIO = "4";

    private static final String GETEXPRESSIO = "5";

    private static final String CONSULTAEXPRE = "6";

    private static final String EXISTEXPRESSIO = "7";


    /**
     * Representa la comanda per sortir del programa.
     * */
    private static final String EXIT = "10";

    private static final String HELPTEXT = "Introduïu un dels següents números per executar la corresponent comanda:\n" +
            "  " + CREAEXPRESSIO       + "-Crea una expressio\n"                        +
            "  " + ADDEXPRESSIO        + "-Afegeix un nova expressio\n"                 +
            "  " + DELETEEXPRESSIO     + "-Elimina una expressio\n"                     +
            "  " + SETEXPRESSIO        + "-Modifica una expressio\n"                    +
            "  " + GETEXPRESSIO        + "-Retorna una expressio\n"                     +
            "  " + CONSULTAEXPRE       + "-Consulta l'expressio en els contingus que has proposat\n"   +
            "  " + EXISTEXPRESSIO      + "-Comprova si una expressios existeix\n"       +

            "  " + EXIT                + "-Sortir del programa\n"

            ;


    private static final ControladorExpressions Ctrlexpressions = new ControladorExpressions();

    /**
     * Llegeix una línia per la terminal.
     *
     * @param scanner mètode per fer l'input.
     * @return Retorna un String del que ha llegit per la terminal.
     * */
    private static String readLine(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Crea una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCreaExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols formalitzar:");
        try {
            Expressio aux = Ctrlexpressions.crearExpressio(readLine(scanner));

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Dona d'alta una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddExpressio(Scanner scanner) throws Exception {
        System.out.println("Escrigui l'expressio que vols afegir:");
        try {
            Ctrlexpressions.anadir_expressio(readLine(scanner));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Dona baixa una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteExpressio(Scanner scanner) throws Exception {
        System.out.println("Escrigui l'expressio que vols eliminar:");
        try {
            Ctrlexpressions.deleteExpressio(readLine(scanner));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    /**
     * Modificar una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSetExpressio(Scanner scanner) throws Exception {
        System.out.println("Escrigui l'expressio que vols modificar:");
        String mod = readLine(scanner);

        System.out.println("Introdueix l'expressio nova");
        String nova_ex = readLine(scanner);

        try {
            Ctrlexpressions.setExpressio(mod,nova_ex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Comprova si una expressio es donat l'alta
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testExistExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols consultar:");
        if (Ctrlexpressions.ExistExpressio(readLine(scanner))) {
            System.out.println("Si que existeix");
        }else {
            System.out.println("No existeix");
        }
    }
    /**
     * Consultora retorna l'expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testGetExpressio(Scanner scanner) throws Exception {
        System.out.println("Escrigui l'expressio que vols consultar:");
        String aux = readLine(scanner);
        try {
            Ctrlexpressions.getExpressio(aux);
            System.out.println("Si que existeix expressio");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public static void testConsultaExpre(Scanner scanner, List<String> cont) throws Exception {
        System.out.println("Escrigui l'expressio que vols evaluar:");
        String ex = readLine(scanner);
        try {
            List<Integer> aux = Ctrlexpressions.ConsultaExpressioBooleana(ex,cont);
            //Per comprovar seria mostrar d'alguna forma els documents trobats.
            System.out.println("S'han trobat els seguents index dels documents");
            for (Integer integer : aux) {
                System.out.println(integer);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    private static void llegirConjuntDocuments(List<String> docs) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digues el nombre de documents que vols introduir: ");
        int nDocs = sc.nextInt();
        sc.nextLine();
        System.out.println("Introdueix els diferents continguts acabats en punt \".\":");
        for (int i = 0; i < nDocs; ++i) {
            try  {
                System.out.println("Contingut "+i+": ");
                String contingut = sc.nextLine();
                docs.add(contingut);
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
    }

    /**
     * Representa totes les comandes que accepta el driver.
     *
     * @param command la comanda que verificarà.
     * @param scanner mètode per fer l'input.
     * */
    private static boolean commands(String command, Scanner scanner) throws Exception {
        switch (command) {
            case CREAEXPRESSIO:
                testCreaExpressio(scanner);
                break;
            case ADDEXPRESSIO:
                testAddExpressio(scanner);
                break;
            case DELETEEXPRESSIO:
                testDeleteExpressio(scanner);
                break;
            case SETEXPRESSIO:
                testSetExpressio(scanner);
                break;
            case GETEXPRESSIO:
                testGetExpressio(scanner);
                break;
            case EXISTEXPRESSIO:
                testExistExpressio(scanner);
                break;
            case CONSULTAEXPRE:
                List<String> cont = new ArrayList<>();
                llegirConjuntDocuments(cont);
                testConsultaExpre(scanner,cont);
                break;
            case EXIT:
                return false;
            default:
                System.out.println("ERROR: Número de comanda no vàlid.");
                break;
        }
        return true;
    }

    /**
     * Punt d'inici del programa.
     *
     * @param args accepta un argument d'un path d'un arxiu txt en cas de voler introduir les comandes des d'un arxiu.
     * */
    public static void main(String[] args) throws Exception {
        boolean run  = true;
        System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run) {
            run = commands(in.nextLine(), in);
            System.out.println(HELPTEXT);
        }

        System.out.println("Gràcies per utilitzar el programa DriverControladorExpressions");

    }



}