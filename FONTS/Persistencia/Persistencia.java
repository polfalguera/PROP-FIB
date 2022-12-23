package FONTS.Persistencia;

import FONTS.Presentacio.ControladorPresentacio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;

public class Persistencia {

    /**
     * Persisteix totes les expressiosn que hi ha en el sistema.
     * @param expressions es una llista d'String que conte l'String de cada una de les expressions del sistema.
     */
    public static void persisitirExpressio(List<String> expressions) throws Exception {
        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/expressions/expressions.txt");

        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        for (String s: expressions) writer.write(s+"\n");
        writer.close();
        fitxer.close();
    }

    /**
     * Persisteix un nou Document.
     * @param autor es l'autor del document a persistir.
     * @param titol es el titol del document a persistir.
     * @param contingut es el contingut del document formatejat en txt
     */
    public static void nouDocument(String autor, String titol, String contingut) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/documents");

        File file = new File(path.toString(), fileName.toString());
        if (!file.createNewFile()) throw new Exception("Error, ja existeix el document");

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        writer.write(contingut);
        writer.close();
        fitxer.close();
    }

    /**
     * Persisteix un nou document donat el seu, titol, autor, contingut, direccio i format.
     * @param autor es l'autor del document a persistir.
     * @param titol es el titol del document a persistir.
     * @param contingutFitxer es el contingut del document formatejat en format
     * @param direccio es la direccio on persistir el document
     * @param format es el format en el que es persistira el document
     */
    public static void persistirADireccio(String titol, String autor, String contingutFitxer, String direccio, String format) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append("." + format);

        StringBuilder path = new StringBuilder("");
        path.append(direccio);

        File file = new File(path.toString(), fileName.toString());
        if (!file.createNewFile()) throw new Exception("Error, ja existeix el document");

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        writer.write(contingutFitxer);
        writer.close();
        fitxer.close();
    }

    /**
     * Elimina dels documents persistits el document amb autor titol proporcionat.
     * @param autor es l'autor del document a eliminar.
     * @param titol es el titol del document a eliminar.
     */
    public static void eliminarDocument(String autor, String titol) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/Documents/");
        path.append(fileName);
        
        File file = new File(path.toString());

        if (!Files.deleteIfExists(Paths.get(path.toString()))) throw new Exception("Error, no existeix el document");
    }

    /**
     * Modifica el document amb autor i titol per un amb nouAutor, nouTitol amb Contingut
     * @param autor es l'autor del document a modificar.
     * @param titol es el titol del document a modificar.
     * @param nouAutor es l'autor del nou document
     * @param nouTitol es el titol del nou document
     * @param Contingut es el Contingut en format txt del document
     */
    public static void modificarDocument(String autor, String titol, String nouAutor, String nouTitol, String Contingut, boolean modCont) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(nouAutor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(nouTitol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/documents");

        File file = new File(path.toString(), fileName.toString());
        if (file.exists() && !modCont) throw new Exception("Error, ja existeix el document");

        eliminarDocument(autor, titol);
        nouDocument(nouAutor, nouTitol, Contingut);
    }

    /**
     * Obte el contingut del document amb autor i titol
     * @param autor es l'autor del document.
     * @param titol es el titol del document.
     * @return retorna el contingut del document persitir amb autor i titol en format txt
     */
    public static String obtenirContingut(String autor, String titol) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/Documents");
        path.append("/").append(fileName);

        StringBuilder contingut = new StringBuilder("");

        FileReader file = new FileReader(path.toString());
        BufferedReader br = new BufferedReader(file);
        String line;
        while((line = br.readLine()) != null) contingut.append(line+"\n");
        br.close();
        file.close();

        return contingut.toString();
    }

    /**
     * Buida tot el que hi ha en la carpeta RESOURCES/frequencies
     */
    public static void buidarFrequencies() throws Exception {
        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/frequencies");

        File carpeta = new File(path.toString());
        File[] arxius = carpeta.listFiles();
        if (arxius != null && arxius.length != 0) {
            for (int i = 0; i < arxius.length; i++) {
                File arxiu = arxius[i];
                if (arxiu.isFile() && (!arxiu.getName().equals("dummy.txt"))) arxiu.delete();
            }
        }
    }
    /**
     * Persisteix les frequencies de les paraules del document amb autor i titol
     * @param autor es l'autor del document.
     * @param titol es el titol del document.
     * @param freq son les frequencies de les paraules del document amb autor i titol
     */
    public static void persitirFrequencies(String autor, String titol, HashMap<String, Integer> freq) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/frequencies");

        File file = new File(path.toString(), fileName.toString());
        if (!file.createNewFile()) throw new Exception("Error, ja existeix el document");

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        for (HashMap.Entry<String, Integer> entry : freq.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            writer.write(key+" "+value+"\n");
        }
        writer.close();
        fitxer.close();

    }

    /**
     * Recupera totes les expressions persistides de l'execució anterior
     * @return retorna una llista d'String amb les expressions de l'execucio anterior
     */
    public static List<String> recuperarExpressions() throws Exception {
        List<String> expressions = new ArrayList<String>();

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/expressions/expressions.txt");

        FileReader file = new FileReader(path.toString());
        BufferedReader br = new BufferedReader(file);
        String line;
        while((line = br.readLine()) != null && !line.equals("")) {
            expressions.add(line);
        }
        br.close();
        file.close();
        return expressions;
    }

    /**
     * Recupera totes les frequencies de les paraules dels documents persistits de l'execució anterior
     * @return retorna una llista on cada posició és un document amb les frequencies de les paraules del document
     */
    public static List<HashMap<String, Integer>> recuperarFreq() throws Exception {
        List<HashMap<String, Integer>> freq = new ArrayList<HashMap<String, Integer>>();

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/frequencies");

        File carpeta = new File(path.toString());
        File[] arxius = carpeta.listFiles();
        if (arxius == null || arxius.length == 0) {
            return freq;
        } else {
            for (int i = 0; i < arxius.length; i++) {
                File arxiu = arxius[i];
                if (arxiu.isFile() && (!arxiu.getName().equals("dummy.txt"))) {
                    HashMap<String, Integer> paraules = new HashMap<String, Integer>();
                    String line;
                    FileReader f = new FileReader(arxiu);
                    BufferedReader br = new BufferedReader(f);
                    while ((line = br.readLine()) != null) {
                        String[] l = line.split(" ");
                        paraules.put(l[0], Integer.valueOf(l[1]));
                    }
                    freq.add(paraules);
                    br.close();
                    f.close();
                }
            }
        }
        return freq;
    }

    /**
     * Recupera el contingut de tots els documents persistits
     * @return retorna una llista d'String on cada posició representa un document i conte el contingut en format txt
     */
    public static List <String> recuperarDocuments() throws Exception {
        List<String> resultat = new ArrayList<String>();
        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/RESOURCES/documents");

        File carpeta = new File(path.toString());
        File[] arxius = carpeta.listFiles();
        if (arxius == null || arxius.length == 0) {
            return resultat;
        } else {
            for (int i = 0; i < arxius.length; i++) {
                File arxiu = arxius[i];
                if (arxiu.isFile() && (!arxiu.getName().equals("dummy.txt"))) {
                    String line;
                    StringBuilder contingut = new StringBuilder("");
                    FileReader f = new FileReader(arxiu);
                    BufferedReader br = new BufferedReader(f);
                    while ((line = br.readLine()) != null) contingut.append(line+"\n");
                    resultat.add(contingut.toString());
                    br.close();
                    f.close();
                }
            }
        }
        return resultat;
    }
}
