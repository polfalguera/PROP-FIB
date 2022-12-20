package FONTS.Persistencia;

import FONTS.Presentacio.ControladorPresentacio;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Persistencia {

    public static void persisitirExpressio(List<String> expressions) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append("expressions.txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/expressions");

        File file = new File(path.toString(), fileName.toString());
        //l'elimino i el creo buit
        file.delete();
        file.createNewFile();

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        for (String s: expressions) writer.write(s+"\n");

        writer.close();
    }

    //Contingut sera el contingut del document formatejat en txt
    public static void nouDocument(String autor, String titol, String contingut) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/documents");

        File file = new File(path.toString(), fileName.toString());
        if (!file.createNewFile()) throw new Exception("Error, ja existeix el document");

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        writer.write(contingut);

        writer.close();
    }

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
    }

    public static void eliminarDocument(String autor, String titol) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/Documents");

        File file = new File(path.toString(), fileName.toString());
        if (!file.delete()) throw new Exception("Error, no existeix el document");
    }

    public static void modificarDocument(String autor, String titol, String nouAutor, String nouTitol, String Contingut) throws Exception {
        eliminarDocument(autor, titol);
        nouDocument(nouAutor, nouTitol, Contingut);

        /*if (nouAutor != "" && nouTitol != "") nouDocument(nouAutor, nouTitol, Contingut);
        else if (nouAutor != "") nouDocument(nouAutor, titol, Contingut);
        else if (nouTitol != "") nouDocument(autor, nouTitol, Contingut);
        else nouDocument(autor, titol, Contingut);*/
    }

    public static String obtenirContingut(String autor, String titol) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/Documents");
        path.append("/").append(fileName);

        StringBuilder contingut = new StringBuilder("");

        FileReader file = new FileReader(path.toString());
        BufferedReader br = new BufferedReader(file);
        String line;
        while((line = br.readLine()) != null) contingut.append(line+"\n");

        return contingut.toString();
    }

    public static void persitirFrequencies(String autor, String titol, HashMap<String, Integer> freq) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/frequencies");

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
    }

    public static List<String> recuperarExpressions() throws Exception {
        List<String> expressions = new ArrayList<String>();

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/expressions/expressions.txt");

        FileReader file = new FileReader(path.toString());
        BufferedReader br = new BufferedReader(file);
        String line;
        while((line = br.readLine()) != null && !line.equals("")) {
            expressions.add(line);
        }
        return expressions;
    }

    public static List<HashMap<String, Integer>> recuperarFreq() throws Exception {
        List<HashMap<String, Integer>> freq = new ArrayList<HashMap<String, Integer>>();

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/frequencies");

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
                }
            }
        }
        return freq;
    }

    public static List <String> recuperarDocuments() throws Exception {
        List<String> resultat = new ArrayList<String>();
        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/documents");

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
                }
            }
        }
        return resultat;
    }

    public static void main (String[] args) throws Exception{
        try {
            List<HashMap<String, Integer>> res = recuperarFreq();
            for (int i = 0; i < res.size(); ++i) {
                HashMap<String, Integer> aux = res.get(i);
                for (HashMap.Entry<String, Integer> entry : aux.entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    System.out.println(key+" "+value+"\n");
                }
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
