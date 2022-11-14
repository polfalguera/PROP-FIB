package FONTS.Drivers;

import java.util.Scanner;

import FONTS.Domini.Expressio;

/**
 * És el driver de la classe Expressio.
 * @author haonan.jin
 */
public class DriverExpressio {
    /**
     * Representa la comanda per crear una expressio
     * */
    private static final String CREAEXPRESSIO = "1";
    /**
     * Representa la comanda per sortir del programa.
     * */
    private static final String EXIT = "2";

    private static final String HELPTEXT = "Selecciona el número de la comanda que vulguis executar: \n" +
            "  " + CREAEXPRESSIO       + "-Crea una expressio\n"       +
            "  " + EXIT                + "-Sortir del programa\n"

            ;

    /**
     * Representa la expressio emmagatzemades en forma d'un arbol binari.
     * */
    private static Expressio ex;

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
    public static void testCreaExpressio(Scanner scanner) throws Exception {
        System.out.println("Escrigui l'expressio que vols formalitzar:");
        try {
            ex = new Expressio(readLine(scanner));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        System.out.println("\n| Driver de la classe Expressio |\n");
        System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run) {
            run = commands(in.nextLine(), in);
            if (run) System.out.println(HELPTEXT);
        }
        System.out.println("| Execució del driver finalitzada |");
    }

}
