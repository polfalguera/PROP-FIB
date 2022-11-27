package FONTS.Persistencia;

import java.io.*;
import java.nio.file.Paths;


public class Persistencia {
    public static void nouDocument(String autor, String titol, String contingut) throws Exception {
        StringBuilder fileName = new StringBuilder("");
        fileName.append(autor.replaceAll(" ", "_"));
        fileName.append("-");
        fileName.append(titol.replaceAll(" ", "_"));
        fileName.append(".txt");

        StringBuilder path = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        path.append("/DATA/Documents");

        File file = new File(path.toString(), fileName.toString());
        if (!file.createNewFile()) throw new Exception("Error, ja existeix el document");

        path.append("/").append(fileName);
        FileWriter fitxer = new FileWriter(path.toString());
        BufferedWriter writer = new BufferedWriter(fitxer);
        writer.write(autor+"\n");
        writer.write(titol+"\n");
        writer.write(contingut);

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
        if (nouAutor != "" && nouTitol != "") nouDocument(nouAutor, nouTitol, Contingut);
        else if (nouAutor != "") nouDocument(nouAutor, titol, Contingut);
        else if (nouTitol != "") nouDocument(autor, nouTitol, Contingut);
        else nouDocument(autor, titol, Contingut);
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

        String line;
        StringBuilder Contingut = new StringBuilder("");
        FileReader file = new FileReader(path.toString());
        BufferedReader br = new BufferedReader(file);
        int i = 0;
        while((line = br.readLine()) != null) {
            if (i == 0) {
                if (!line.equals(autor)) throw new Exception("Error, Document corrupt");
            }
            else if (i == 1) {
                if (!line.equals(titol)) throw new Exception("Error, Document corrupt");
            }
            else {
                Contingut.append(line);
            }
            ++i;
        }
        return Contingut.toString();
    }

    public static void main(String[] args) throws Exception {
        nouDocument("hola", "adeu", "hola que tal estas");
        modificarDocument("hola", "adeu", "", "", "contingut modificat");
        modificarDocument("hola", "adeu", "nouHola", "", "contingut modificat");
        modificarDocument("nouHola", "adeu", "", "nouAdeu", "contingut final");
        System.out.println(obtenirContingut("nouHola", "nouAdeu"));
        eliminarDocument("nouHola", "nouAdeu");
    }
}
