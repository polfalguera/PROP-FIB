package capaDomini;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

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

    public ControladorContingut() throws Exception {
        freqContingut = new ArrayList<HashMap<String, Integer>>();
        Contingut = new ArrayList<String>();
        try {
            stopWords = assignarStopWords();
        } catch (Exception e) {
            throw e;
        }
    }

    private Set<String> assignarStopWords() throws Exception {
        try {
            Set<String> result = new HashSet<String>();
            //empty-ca.txt
            String line;
            String path = Paths.get("data/empty-ca-utf8.txt").toAbsolutePath().toString();
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            //empty-eng.txt
            path = Paths.get("data/empty-eng-utf8.txt").toAbsolutePath().toString();
            file = new FileReader(path);
            br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            //empty-sp.txt
            path = Paths.get("data/empty-sp-utf8.txt").toAbsolutePath().toString();
            file = new FileReader(path);
            br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            return result;
        } catch (Exception e) {
            throw new Exception("Error, no s'han pogut carregar les stopwords");
        }
    }
    private double tf(String paraula, int id, int mode) {
        HashMap<String, Integer> freq = freqContingut.get(id);
        double n = 0;
        for (Map.Entry<String, Integer> set : freq.entrySet()) {
            n += set.getValue();
        }
        if (mode == 1 && freq.containsKey(paraula)) return Math.log(1+freq.get(paraula));
        else if (mode != 1 && freq.containsKey(paraula)) return freq.get(paraula);
        else return 0;
    }
    private static double idf(String paraula, int mode) {
        double n = 0;
        for (HashMap<String, Integer> doc : freqContingut) {
            if (doc.containsKey(paraula)) n += doc.get(paraula);
        }
        if (mode == 1) return 1;
        else if (n == 0) return 0;
        else return Math.log(Contingut.size() / n);
    }

    public void afegirContingutPath(String path) throws Exception {
        try {
            String line;
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);

            HashMap<String, Integer> text = new HashMap<String, Integer>();
            StringBuilder contingut = new StringBuilder();
            boolean primer = true;

            while((line = br.readLine()) != null) {
                if (primer) {
                    contingut.append(line);
                    primer =  false;
                }
                else contingut.append("\n"+line);
                //Splits each line into words
                String[] words = line.split("\\p{Punct}| |\\n|¿|¡");
                for (String word : words) {
                    word = word.toLowerCase();
                    if (!stopWords.contains(word) && word != "") {
                        if (!text.containsKey(word)) text.put(word, 1);
                        else text.put(word, text.get(word)+1);
                    }
                }
            }

            if (contingut.isEmpty()) throw new Exception("Error, contingut buit");
            freqContingut.add(text);
            Contingut.add(contingut.toString());
        } catch (Exception e) {
            throw new Exception("Error, path del document incorrecte");
        }
    }
    public void modificarContingutPath(int id, String path) throws Exception {
        if (freqContingut.size() <= id) throw new Exception("Error, index out of bounds");
        try {
            String line;
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);

            HashMap<String, Integer> text = new HashMap<String, Integer>();
            StringBuilder contingut = new StringBuilder();
            boolean primer = true;

            while((line = br.readLine()) != null) {
                if (primer) {
                    contingut.append(line);
                    primer =  false;
                }
                else contingut.append("\n"+line);
                //Splits each line into words
                String[] words = line.split("\\p{Punct}| |\\n|¿|¡");
                for (String word : words) {
                    word = word.toLowerCase();
                    if (!stopWords.contains(word) && word != "") {
                        if (!text.containsKey(word)) text.put(word, 1);
                        else text.put(word, text.get(word)+1);
                    }
                }
            }

            if (contingut.isEmpty()) throw new Exception("Error, contingut buit");
            freqContingut.add(id, text);
            Contingut.set(id, contingut.toString());
        } catch (Exception e) {
            throw new Exception("Error, path del document incorrecte");
        }
    }
    public void afegirContingut(String contingut) throws Exception {
        if (contingut == "") throw new Exception("Error, contingut buit");

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split("\\p{Punct}| |\\n|¿|¡");
        int id = Contingut.size();

        for (String word : words) {
            word = word.toLowerCase();
            if (!stopWords.contains(word) && word != "") {
                if (!text.containsKey(word)) text.put(word, 1);
                else text.put(word, text.get(word)+1);
            }
        }

        Contingut.add(contingut);
        freqContingut.add(text);
    }
    public void modificarContingut(int id, String contingut) throws Exception {
        if (freqContingut.size() <= id) throw new Exception("Error, index out of bounds");
        if (contingut == "") throw new Exception("Error, contingut buit");

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split("\\p{Punct}| |\\n|¿|¡");

        for (String word : words) {
            word = word.toLowerCase();
            if (!stopWords.contains(word) && word != "") {
                if (!text.containsKey(word)) text.put(word, 1);
                else text.put(word, text.get(word)+1);
            }
        }

        Contingut.add(id, contingut);
        freqContingut.add(id, text);
    }
    public int[] termsTfIdf(String[] paraules, int k, int mode) {
        if (k > Contingut.size()) k = Contingut.size();
        double[] tfidf = new double[Contingut.size()];
        for (int i = 0; i < tfidf.length; ++i) tfidf[i] = 0;

        for (String paraula : paraules) {
            paraula = paraula.toLowerCase();
            double idf = idf(paraula, mode);
            for (int j = 0; j < Contingut.size(); ++j) {
                tfidf[j] += (tf(paraula, j, mode) * idf);
            }
        }
        return IndexValuePair.krellevants(tfidf, k);
    }
    public String getContingut(int id) throws Exception {
        if (id >= Contingut.size()) throw new Exception("Error, index out of bounds");
        return Contingut.get(id);
    }
    public void eliminarContingut(int id) throws Exception {
        if (id >= Contingut.size()) throw new Exception("Error, index out of bounds");
        freqContingut.remove(id);
        Contingut.remove(id);
    }
    public String[] obtenirParaulesContingut(int id) throws Exception {
        if (id >= Contingut.size()) throw new Exception("Error, index out of bounds");
        return Contingut.get(id).split("\\p{Punct}| |\\n|¿|¡");
    }
    public List<String> getConjuntContinguts() {
        return Contingut;
    }
}