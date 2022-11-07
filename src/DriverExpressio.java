package src;

import src.Expressio;
import java.util.Scanner;

/**
 * És el driver de la classe Expressio.
 * @author haonan.jin
 */
public class DriverExpressio {

    private static final String CREAEXPRESSIO = "1";

    private static final String EXIT = "10";

    private static final String HELPTEXT = "Introduïu un dels següents números per executar la corresponent comanda:\n" +
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
    public static void testCreaExpressio(Scanner scanner) {
        System.out.println("Escrigui l'expressio que vols formalitzar:");
        ex = new Expressio(readLine(scanner));
    }


    public static void testEvaluateExpre(Scanner scanner) {
        System.out.println("Escrigui la frase/document que vols evaluar:");
        String frase = readLine(scanner);
        System.out.println("Escrigui l'expressio que vols evaluar:");

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
        boolean run  = true;

        if (run) System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run)
            run = commands(in.nextLine(), in);
        System.out.println("Gràcies per utilitzar el programa DriverDocument!");

    }

}
