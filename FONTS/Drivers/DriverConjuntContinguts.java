package FONTS.Drivers;

import FONTS.Domini.ConjuntContinguts;

import java.util.*;

public class DriverConjuntContinguts {

    private static ConjuntContinguts CtrlContingut;
    private static final String numero_comanda = "0";
    private static final String afegirContingut = "1";
    private static final String eliminarContingut = "2";
    private static final String modificarContingut = "3";
    private static final String obtenirkRellevants = "4";
    private static final String obtenirContingut = "5";
    private static final String obtenirTotsContinguts = "6";
    private static final String obtenirParaulesContingut = "7";
    private static final String tancar_driver = "8";

    private static final String AJUDA = "Números associats a cada comanada del driver:  \n"+
            " "+afegirContingut+" Afegeix un nou contingut\n"+
            " "+eliminarContingut+" Elimina un contingut\n"+
            " "+modificarContingut+" Modificar un contingut\n"+
            " "+obtenirkRellevants+" Obté els k continguts més rellevants a un llistat de paraules\n"+
            " "+obtenirContingut+" Obté un contingut\n"+
            " "+obtenirTotsContinguts+" Obté tots els continguts\n"+
            " "+obtenirParaulesContingut+" Obté les paraules d'un contingut\n"+
            " "+tancar_driver+" Finalitza l'execució del driver";


    private void testAfegirContingut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("0 si vols afegir el contingut per terminal (per defecte)");
        System.out.println("1 si vols afegir el contingut a traves del path d'un fitxer");
        int path = Integer.parseInt(sc.nextLine());
        if (path == 1) {
            System.out.println("Introdueix el path del fitxer: ");
            String p = sc.nextLine();
            try  {
                CtrlContingut.afegirContingutPath(p);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
        else {
            System.out.println("Introdueix el contingut: ");
            String contingut = sc.nextLine();
            try  {
                CtrlContingut.afegirContingut(contingut);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
        System.out.println("Contingut creat correctament.");
    }

    private void testEliminarContingut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix l'index del contingut a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        try {
            CtrlContingut.eliminarContingut(id);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("Contingut eliminat correctament.");
    }
    private void testModificarContingut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix l'index del contingut a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("0 si vols modificar el contingut per terminal (per defecte)");
        System.out.println("1 si vols modificar el contingut a traves del path d'un fitxer");
        int path = Integer.parseInt(sc.nextLine());
        if (path == 1) {
            System.out.println("Introdueix el path del fitxer: ");
            String p = sc.nextLine();
            try  {
                CtrlContingut.modificarContingutPath(id, p);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
        else {
            System.out.println("Introdueix el contingut: ");
            String contingut = sc.nextLine();
            try  {
                CtrlContingut.modificarContingut(id, contingut);
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
        System.out.println("Contingut modificat correctament.");
    }
    private void testObtenirkRellevants() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nombre de contingut rellevants: ");
        int k = Integer.parseInt(sc.nextLine());
        System.out.println("Introdueix el nombre de paraules per buscar els rellevants: ");
        int n = Integer.parseInt(sc.nextLine());
        String [] paraules = new String[n];
        for (int i = 0; i < n; ++i) {
            System.out.println("Introdueix la paraula "+i+1+": ");
            paraules[i] = sc.nextLine();
        }
        System.out.println("Introdueix el mode per assignar els pesos");
        System.out.println("0 freq*log(N/n) (per defecte)");
        System.out.println("1 log(1+freq)");
        int mode = Integer.parseInt(sc.nextLine());
        try {
            int [] res = CtrlContingut.kRellevants(paraules, k, mode);
            System.out.println("Els més rellevants són:\n");
            for (int i = 0; i < res.length; ++i) System.out.println(CtrlContingut.getContingut(res[i])+"\n");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    private void testObtenirContingut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix l'index del contingut que es vol obtenir: ");
        int id = Integer.parseInt(sc.nextLine());
        try {
            String contingut = CtrlContingut.getContingut(id);
            System.out.println("El contingut es: "+contingut+"\n");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    private void testObtenirTotsContinguts() {
        List<String> conjunt = CtrlContingut.getConjuntContinguts();
        System.out.println("Els continguts són: \n");
        for (String s : conjunt) System.out.println(s+"\n");
    }
    private void testObtenirParaulesContingut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix l'index del contingut del que es vol obtenir les paraules: ");
        int id = Integer.parseInt(sc.nextLine());
        try {
            String[] paraules = CtrlContingut.obtenirParaulesContingut(id);
            System.out.println("Les paraules del contingut son: "+Arrays.toString(paraules)+"\n");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        DriverConjuntContinguts dcc = new DriverConjuntContinguts();
        System.out.println("\n| Driver de la classe ControladorContingut |\n");
        try {
            CtrlContingut = new ConjuntContinguts();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        String comanda = numero_comanda;
        while (!comanda.equals(tancar_driver)) {
            System.out.println("Selecciona el número de la comanda que vulguis executar: ");
            System.out.println(AJUDA+"\n");
            Scanner sc1 = new Scanner(System.in);
            comanda = sc1.nextLine();

            switch (comanda) {
                case numero_comanda -> System.out.println(AJUDA);
                case afegirContingut -> dcc.testAfegirContingut();
                case eliminarContingut -> dcc.testEliminarContingut();
                case modificarContingut -> dcc.testModificarContingut();
                case obtenirkRellevants -> dcc.testObtenirkRellevants();
                case obtenirContingut -> dcc.testObtenirContingut();
                case obtenirTotsContinguts -> dcc.testObtenirTotsContinguts();
                case obtenirParaulesContingut -> dcc.testObtenirParaulesContingut();
                case tancar_driver -> {
                    System.out.println("| Execució del driver finalitzada |");
                    return;
                }
                default -> System.out.println("ERROR: Número de comanda no vàlid.");
            }
        }
    }
}
