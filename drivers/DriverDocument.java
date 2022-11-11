package drivers;

import capaDomini.Document;

import java.util.*;

public class DriverDocument {

    private static Document document;
    private static final String numero_comanda = "0";
    private static final String getTitol = "2";
    private static final String getAutor = "1";
    private static final String setTitol = "4";
    private static final String setAutor = "3";

    private static final String tancar_driver = "5";

    private static final String AJUDA = "Números associats a cada comanada del driver:  \n"+
            " "+getAutor+" Obte l'autor del document\n"+
            " "+getTitol+" Obte el titol del document\n"+
            " "+setAutor+" Actualitza l'autor del document\n"+
            " "+setTitol+" Actualitza el titol del document\n"+
            " "+tancar_driver+" Finalitza l'execució del driver";
    private static void  llegirDocument() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix l'autor en una linia i el titol en la seguent:");
        System.out.println("Autor: ");
        String autor = sc.nextLine();
        System.out.println("Titol: ");
        String titol = sc.nextLine();
        document = new Document(titol, autor);
    }

    public static void testGetTitol() {
        System.out.println(document.getTitol());
    }
    public static void testGetAutor() {
        System.out.println(document.getAutor());
    }
    public static void testSetTitol() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nou titol.");
        System.out.println("Titol: ");
        String titol = sc.nextLine();
        document.setTitol(titol);

    }
    public static void testSetAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nou autor.");
        System.out.println("Autor: ");
        String autor = sc.nextLine();
        document.setAutor(autor);
    }
    public static void main(String[] args) {
        DriverDocument dd = new DriverDocument();
        System.out.println("| Driver de la classe Document |\n");
        System.out.println("Abans de testejar funcionalitats cal declarar un document sobre el qual treballar.");
        llegirDocument();

        System.out.println(AJUDA+"\n");
        String comanda = numero_comanda;
        while (comanda != tancar_driver) {
            System.out.println("Selecciona el número de la comanda que vulguis executar: ");
            Scanner sc1 = new Scanner(System.in);
            comanda = sc1.nextLine();

            switch(comanda) {
                case numero_comanda:
                    System.out.println(dd.AJUDA);
                    break;
                case getAutor:
                    dd.testGetAutor();
                    break;
                case getTitol:
                    dd.testGetTitol();
                    break;
                case setAutor:
                    dd.testSetAutor();
                    break;
                case setTitol:
                    dd.testSetTitol();
                    break;
                case tancar_driver:
                    return;
            }
        }

    }
}
