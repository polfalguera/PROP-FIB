package src;
import java.io.BufferedReader;
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
    private static HashMap<String, List<Integer>> paraulaDocuments;
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
        paraulaDocuments = new HashMap<String, List<Integer>>();
        stopWords = assignarStopWords();
    }

    public void afegirContingutPath(String path, String[] status) throws IOException {
        status[0] = "";
        String line;
        FileReader file = new FileReader(path);
        BufferedReader br = new BufferedReader(file);

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        StringBuilder contingut = new StringBuilder();

        int id = Contingut.size();
        boolean primer = true;

        while((line = br.readLine()) != null) {
            if (primer) {
                contingut.append(line);
                primer =  false;
            }
            else contingut.append("\n"+line);

            //Splits each line into words
            String[] words = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (String word : words) {
                word = word.toLowerCase();
                if (!stopWords.contains(word) && word != "") {
                    if (!text.containsKey(word)) {
                        text.put(word, 1);
                        if (paraulaDocuments.containsKey(word)) paraulaDocuments.get(word).add(id);
                        else {
                            List<Integer> l = new ArrayList<Integer>();
                            l.add(id);
                            paraulaDocuments.put(word, l);
                        }
                    }
                    else text.put(word, text.get(word)+1);
                }
            }
        }
        if (contingut.isEmpty()) {
            status[0] = "Error, contingut buit";
            return;
        }
        freqContingut.add(text);
        Contingut.add(contingut.toString());
    }

    public void modificarContingutPath(int id, String path, String[] status) throws IOException {
        status[0] = "";
        if (freqContingut.size() <= id) {
            status[0] = "Error, no es pot modificar un contingut no existent";
            return;
        }

        for (Map.Entry<String, List<Integer>> set : paraulaDocuments.entrySet())
            if (set.getValue().contains(id)) set.getValue().remove(id);

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
            String[] words = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (String word : words) {
                word = word.toLowerCase();
                if (!stopWords.contains(word) && word != "") {
                    if (!text.containsKey(word)) {
                        text.put(word, 1);
                        if (paraulaDocuments.containsKey(word)) paraulaDocuments.get(word).add(id);
                        else {
                            List<Integer> l = new ArrayList<Integer>();
                            l.add(id);
                            paraulaDocuments.put(word, l);
                        }
                    }
                    else text.put(word, text.get(word)+1);
                }
            }
        }
        if (contingut.isEmpty()) {
            status[0] = "Error, contingut buit";
            return;
        }
        freqContingut.add(id, text);
        Contingut.set(id, contingut.toString());
    }

    public void afegirContingut(String contingut, String[] status) throws IOException {
        status[0] = "";
        if (contingut == "") {
            status[0] = "Error, contingut buit";
            return;
        }

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split((" |,|\\.|!|¡|\\?|¿"));
        int id = Contingut.size();

        for (String word : words) {
            word = word.toLowerCase();
            if (!stopWords.contains(word) && word != "") {
                if (!text.containsKey(word)) {
                    text.put(word, 1);
                    if (paraulaDocuments.containsKey(word)) paraulaDocuments.get(word).add(id);
                    else {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(id);
                        paraulaDocuments.put(word, l);
                    }
                }
                else text.put(word, text.get(word)+1);
            }
        }

        Contingut.add(contingut);
        freqContingut.add(text);
    }

    public void modificarContingut(int id, String contingut, String[] status) {
        status[0] = "";
        if (freqContingut.size() <= id) {
            status[0] = "Error, no es pot modificar un contingut no existent";
            return;
        }
        if (contingut == "") {
            status[0] = "Error, contingut buit";
            return;
        }

        for (Map.Entry<String, List<Integer>> set : paraulaDocuments.entrySet())
            if (set.getValue().contains(id)) set.getValue().remove(id);

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split((" |,|\\.|!|¡|\\?|¿"));

        for (String word : words) {
            word = word.toLowerCase();
            if (!stopWords.contains(word) && word != "") {
                if (!text.containsKey(word)) {
                    text.put(word, 1);
                    if (paraulaDocuments.containsKey(word)) paraulaDocuments.get(word).add(id);
                    else {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(id);
                        paraulaDocuments.put(word, l);
                    }
                }
                else text.put(word, text.get(word)+1);
            }
        }

        Contingut.add(id, contingut);
        freqContingut.add(id, text);
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

    //mode = 0 -> term frequency/freqTotal
    //mode = 1 -> log(1+freq)
    //mode != [0,1] --> 0 per defecte
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
        for (double j : tfidf) System.out.println(j);
        return IndexValuePair.krellevants(tfidf, k);
    }

    public void escriureContingut(int id) {
        System.out.println(Contingut.get(id));
    }

    public String getContingut(int index) {
        return Contingut.get(index);
    }

    public void eliminarContingut(int id) {
        if (Contingut.size() <= id) return;
        List<String> delete = new ArrayList<String>();
        for (Map.Entry<String, List<Integer>> set : paraulaDocuments.entrySet()) {
            List<Integer> l = new ArrayList<Integer>();
            for (Integer s : set.getValue()) {
                if (s < id) l.add(s);
                if (s > id) l.add(s - 1);
            }
            if (!l.isEmpty()) set.setValue(l);
            else delete.add(set.getKey());
        }
        for(String key : delete) paraulaDocuments.remove(key);
    }

    public String[] obtenirParaulesContingut(int id) {
        return freqContingut.get(id).keySet().toArray(new String[0]);
    }

    public List<String> getConjuntContinguts() { return Contingut; }

    public static void main(String[] args) throws IOException {
        String status[] = {""};
        ControladorContingut c = new ControladorContingut();
        c.afegirContingutPath("/Users/alexares/Desktop/subgrup-prop11.3/src/data.txt", status);
        c.afegirContingut("Lorem.", status);
        String[] paraules = {"Lorem", "ipsum", "dolor"};
        int[] sol = c.termsTfIdf(paraules, 4, 0);
        for (int i : sol) {
            System.out.println(Contingut.get(i));
            System.out.println(Arrays.toString(c.obtenirParaulesContingut(i)));
        }
    }
}
