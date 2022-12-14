package FONTS.Persistencia;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import FONTS.Domini.*;


public class Persistencia {

    public static void persisitirExpressio(HashMap<String, Expressio> expressions) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append("expressions.txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/expressions");

        File file = new File(path.toString(), fileName.toString());
        if (!file.createNewFile()) throw new Exception("Error, ja existeix el document");

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        for (String s: expressions.keySet()) writer.write(s+"\n");

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

        ControladorFormat CtrlFormat = new ControladorFormat();

        List<String> fitxer = CtrlFormat.extractTitolAutorContingut(path.toString(), "txt");
        return fitxer.get(2);
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

    public static HashMap<String, Expressio> recuperarExpressions() throws Exception {
        HashMap<String, Expressio> expressions = new HashMap<String, Expressio>();

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/expressions/expressions.txt");

        FileReader file = new FileReader(path.toString());
        BufferedReader br = new BufferedReader(file);
        String line;
        while((line = br.readLine()) != null && line != "") {
            expressions.put(line, new Expressio(line));
        }
        return expressions;
    }

    public static List<HashMap<String, Integer>> recuperarFreq() throws Exception {
        List<HashMap<String,Integer>> freq = new ArrayList<HashMap<String, Integer>>();

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/frequencies");

        File carpeta = new File(path.toString());
        File[] arxius = carpeta.listFiles();
        if (arxius == null || arxius.length == 0) {
            return freq;
        }
        else {
            for (int i=0; i < arxius.length; i++) {
                File arxiu = arxius[i];
                if (arxiu.isFile() && (arxiu.getName() != "dummy.txt")) {
                    HashMap<String,Integer> paraules = new HashMap<String,Integer>();
                    String line;
                    FileReader f = new FileReader(arxiu);
                    BufferedReader br = new BufferedReader(f);
                    while((line = br.readLine()) != null) {
                        String[] l = line.split(" ");
                        paraules.put(l[0], Integer.valueOf(l[1]));
                    }
                    freq.add(paraules);
                }
            }
        }
        return freq;
    }
}
