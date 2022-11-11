package drivers;

import capaDomini.ControladorExpressions;
import capaDomini.Expressio;

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


    private static ControladorExpressions Ctrlexpressions = new ControladorExpressions();

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
        Expressio aux = new Expressio(readLine(scanner));
    }

    /**
     * Dona d'alta una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols afegir:");
        if (Ctrlexpressions.anadir_expressio(readLine(scanner))) {

        }
    }
    /**
     * Dona baixa una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols eliminar:");
        if (Ctrlexpressions.deleteExpressio(readLine(scanner))) {
            return;
        }
    }
    /**
     * Modificar una expressio
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSetExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols modificar:");
        String mod = readLine(scanner);
        if (Ctrlexpressions.ExistExpressio(mod)) {
            System.out.println("Introdueix l'expressio nova");
            String nova_ex = readLine(scanner);
            Ctrlexpressions.setExpressio(mod,nova_ex);
            return;
        }
        System.out.println("L'expressio que has introduit no existeix torna a intronduir un de nou");
        testSetExpressio(scanner);
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
    public static void testGetExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols consultar:");
        Ctrlexpressions.getExpressio(readLine(scanner));
    }
    public static void testConsultaExpre(Scanner scanner, List<String> cont) {
        System.out.println("Escrigui l'expressio que vols evaluar:");
        String frase = readLine(scanner);
        List<Integer> aux = Ctrlexpressions.ConsultaExpressioBooleana(frase,cont);
        //Per comprovar seria mostrar d'alguna forma els documents trobats.

    }

    /**
     * Representa totes les comandes que accepta el driver.
     *
     * @param command la comanda que verificarà.
     * @param scanner mètode per fer l'input.
     * */
    private static boolean commands(String command, Scanner scanner) {
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
                //testConsultaExpre(scanner,cont);
                break;
            case EXIT:
                return false;
        }
        return true;
    }

    /**
     * Punt d'inici del programa.
     *
     * @param args accepta un argument d'un path d'un arxiu txt en cas de voler introduir les comandes des d'un arxiu.
     * */
    public static void main(String[] args) {

        //List<String> cont = arguments;
        boolean run  = true;

        if (run) System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run) {
            run = commands(in.nextLine(), in);
            System.out.println(HELPTEXT);
        }

        System.out.println("Gràcies per utilitzar el programa DriverControladorExpressions");

    }



}