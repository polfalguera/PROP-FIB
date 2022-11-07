package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControladorContingut {
    public static class IndexValuePair {
        private int index;
        private double value;

        public IndexValuePair(int index, double value) {
            this.index = index;
            this.value = value;
        }
        private static int[] krellevants(double[] tfidf, int k) {
            //create sort able array with index and value pair
            IndexValuePair[] pairs = new IndexValuePair[tfidf.length];
            for (int i = 0; i < tfidf.length; i++) {
                pairs[i] = new IndexValuePair(i, tfidf[i]);
            }

            //sort
            Arrays.sort(pairs, new Comparator<IndexValuePair>() {
                public int compare(IndexValuePair o1, IndexValuePair o2) {
                    return Double.compare(o2.value, o1.value);
                }
            });

            //extract the indices
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = pairs[i].index;
            }
            return result;
        }
    }

    private static List<HashMap<String, Integer>> freqContingut;
    private static List<String> Contingut;
    private static Set<String> stopWords;

    private Set<String> assignarStopWords() throws IOException {
        Set<String> result = new HashSet<String>();
        //empty-ca.txt
        String line;
        FileReader file = new FileReader("/Users/alexares/Desktop/subgrup-prop11.3/src/empty-ca.txt");
        BufferedReader br = new BufferedReader(file);
        while((line = br.readLine()) != null) result.add(line);
        //empty-eng.txt
        file = new FileReader("/Users/alexares/Desktop/subgrup-prop11.3/src/empty-eng.txt");
        br = new BufferedReader(file);
        while((line = br.readLine()) != null) result.add(line);
        //empty-sp.txt
        file = new FileReader("/Users/alexares/Desktop/subgrup-prop11.3/src/empty-sp.txt");
        br = new BufferedReader(file);
        while((line = br.readLine()) != null) result.add(line);
        return result;
    }

    public ControladorContingut() throws IOException {
        freqContingut = new ArrayList<HashMap<String, Integer>>();
        Contingut = new ArrayList<String>();
        stopWords = assignarStopWords();
    }

    public void afegirContingutPath(String path, String status) throws IOException {
        String line;
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        StringBuilder contingut = new StringBuilder();
        if ((line = br.readLine()) != null) contingut.append(line);
        else {
            System.out.println("Error, contingut buit");
            return;
        }
        //Gets each line till end of file is reached
        while((line = br.readLine()) != null) {
            contingut.append("\n"+line);
            //Splits each line into words
            String[] words = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (String word : words) {
                if (!text.containsKey(word)) text.put(word, 1);
                else if (!stopWords.contains(word)) text.put(word, text.get(word) + 1);
            }
        }

        freqContingut.add(text);
        Contingut.add(contingut.toString());
    }

    public void modificarContingutPath(int id, String path, String status) throws IOException {
        if (freqContingut.size() <= id) {
            System.out.println("Error, no es pot modificar un contingut no existent");
            return;
        }

        String line;
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        StringBuilder contingut = new StringBuilder();
        if ((line = br.readLine()) != null) contingut.append(line);
        else {
            System.out.println("Error, contingut buit");
            return;
        }
        //Gets each line till end of file is reached
        while((line = br.readLine()) != null) {
            contingut.append("\n"+line);
            //Splits each line into words
            String[] words = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (String word : words) {
                if (!text.containsKey(word)) text.put(word, 1);
                else if (!stopWords.contains(word)) text.put(word, text.get(word) + 1);
            }
        }

        freqContingut.add(text);
        Contingut.add(contingut.toString());
    }

    public void afegirContingut(String contingut, String status) throws IOException {
        if (contingut == "") {
            System.out.println("Error, contingut buit");
            return;
        }

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split((" |,|\\.|!|¡|\\?|¿"));

        for (String word : words) {
            if (!text.containsKey(word)) text.put(word, 1);
            else if (!stopWords.contains(word)) text.put(word, text.get(word) + 1);
        }

        Contingut.add(contingut);
        freqContingut.add(text);
    }

    public void modificarContingut(int id, String contingut) {
        if (freqContingut.size() <= id) {
            System.out.println("Error, no es pot modificar un contingut no existent");
            return;
        }
        if (contingut == "") {
            System.out.println("Error, contingut buit");
            return;
        }

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split((" |,|\\.|!|¡|\\?|¿"));

        for (String word : words) {
            if (!text.containsKey(word)) text.put(word, 1);
            else if (!stopWords.contains(word)) text.put(word, text.get(word) + 1);
        }

        Contingut.add(id, contingut);
        freqContingut.add(id, text);
    }

    private double tf(String paraula, int id) {
        HashMap<String, Integer> freq = freqContingut.get(id);
        double n = 0;
        for (Map.Entry<String, Integer> set : freq.entrySet()) {
            n += set.getValue();
        }
        if (freqContingut.get(id).containsKey(paraula)) return freqContingut.get(id).get(paraula) / n;
        else return 0;
    }

    private static double idf(String paraula) {
        double n = 0;
        for (HashMap<String, Integer> doc : freqContingut) {
            if (doc.containsKey(paraula)) n += doc.get(paraula);
        }
        return Math.log(Contingut.size() / n);
    }

    public int[] termsTfIdf(String[] paraules, int k) {
        if (k > Contingut.size()) k = Contingut.size();
        double[] tfidf = new double[Contingut.size()];
        for (int i = 0; i < tfidf.length; ++i) tfidf[i] = 0;

        for (String paraula : paraules) {
            double idf = idf(paraula);
            for (int j = 0; j < Contingut.size(); ++j) {
                tfidf[j] += (tf(paraula, j) * idf);
            }
        }
        return IndexValuePair.krellevants(tfidf, k);
    }

    public void escriureContingut(int id) {
        System.out.println(Contingut.get(id));
    }

    public static void main(String[] args) throws IOException {
        String status = "";
        ControladorContingut c = new ControladorContingut();
        c.afegirContingutPath("/Users/alexares/Desktop/subgrup-prop11.3/src/data.txt", status);
        c.afegirContingut("hola que, espero que separi be ¡jaja!", status);
        String[] paraules = {"hola", "que", "tal"};
        int[] sol = c.termsTfIdf(paraules, 4);
        for (int i = 0; i < sol.length; ++i) c.escriureContingut(i);
    }
}
