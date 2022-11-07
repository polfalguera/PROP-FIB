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

    public ControladorContingut() {
        this.freqContingut = new ArrayList<HashMap<String, Integer>>();
        this.Contingut = new ArrayList<String>();
    }

    public void afegirContingutPath(String path, String status) throws IOException {
        status = "";
        String line;
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String contingut = "";
        //Gets each line till end of file is reached
        while((line = br.readLine()) != null) {
            contingut += line;
            //Splits each line into words
            String words[] = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (int i = 0; i < words.length; ++i){
                if (!text.containsKey(words[i])) text.put(words[i], 1);
                else text.put(words[i], text.get(words[i])+1);
            }
        }

        this.freqContingut.add(text);
        this.Contingut.add(contingut);
    }

    public void modificarContingutPath(int id, String path, String status) throws IOException {
        status = "";
        if (freqContingut.size() <= id) {
            status = "Error, no es pot modificar un contingut no existent";
        }
        String line;
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String contingut = "";
        //Gets each line till end of file is reached
        while((line = br.readLine()) != null) {
            contingut += line;
            //Splits each line into words
            String words[] = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (int i = 0; i < words.length; ++i){
                if (!text.containsKey(words[i])) text.put(words[i], 1);
                else text.put(words[i], text.get(words[i])+1);
            }
        }

        this.freqContingut.add(text);
        this.Contingut.add(contingut);
    }

    public void afegirContingut(String contingut, String status) throws IOException {
        status = "";
        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String words[] = contingut.split((" |,|\\.|!|¡|\\?|¿"));

        for (int i = 0; i < words.length; ++i) {
            if (!text.containsKey(words[i])) text.put(words[i], 1);
            else text.put(words[i], text.get(words[i])+1);
        }

        this.Contingut.add(contingut);
        this.freqContingut.add(text);
    }

    public void modificarContingut(int id, String contingut, String status) {
        status = "";
        if (freqContingut.size() <= id) {
            status = "Error, no es pot modificar un contingut no existent";
        }
        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String words[] = contingut.split((" |,|\\.|!|¡|\\?|¿"));

        for (int i = 0; i < words.length; ++i) {
            if (!text.containsKey(words[i])) text.put(words[i], 1);
            else text.put(words[i], text.get(words[i])+1);
        }

        Contingut.add(id, contingut);
        freqContingut.add(id, text);
    }

    private double tf(String paraula, int id) {
        HashMap<String, Integer> freq = this.freqContingut.get(id);
        double n = 0;
        for (Map.Entry<String, Integer> set : freq.entrySet()) {
            n += set.getValue();
        }
        if (this.freqContingut.get(id).containsKey(paraula)) return this.freqContingut.get(id).get(paraula) / n;
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
        if (k > this.Contingut.size()) k = this.Contingut.size();
        double[] tfidf = new double[this.Contingut.size()];
        for (int i = 0; i < tfidf.length; ++i) tfidf[i] = 0;

        for (int i = 0; i < paraules.length; ++i) {
            double idf = idf(paraules[i]);
            for (int j = 0; j < this.Contingut.size(); ++j) {
                tfidf[j] += (tf(paraules[i], j) * idf);
            }
        }
        return IndexValuePair.krellevants(tfidf, k);
    }

    public static void main(String[] args) throws IOException {
        ControladorContingut c = new ControladorContingut();
        c.afegirContingutPath("/Users/alexares/Desktop/subgrup-prop11.3/src/data.txt", "");
        c.afegirContingut("hola que, espero que separi be ¡jaja!", "");
        String[] paraules = {"hola", "que", "tal"};
        int[] sol = c.termsTfIdf(paraules, 2);
        for (int i = 0; i < sol.length; ++i) System.out.println(c.Contingut.get(i));
    }
}
