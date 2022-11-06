package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class tfIdf {
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

    private static double tf(String paraula, List<String> contingut) {
        double result = 0;
        for (int i = 0; i < contingut.size(); ++i) {
            if (contingut.get(i).equalsIgnoreCase(paraula)) ++result;
        }
        return result / contingut.size();
    }

    private static double idf(String paraula, List<List<String>> documents) {
        double n = 0;
        for (List<String> doc : documents) {
            for (String word : doc) {
                if (paraula.equalsIgnoreCase(word)) {
                    ++n;
                }
            }
        }
        return Math.log(documents.size() / n);
    }

    public static int[] termsTfIdf(String[] paraules, List<List<String>> documents, int k) {
        if (k > documents.size()) k = documents.size();
        double[] tfidf = new double[documents.size()];
        for (int i = 0; i < tfidf.length; ++i) tfidf[i] = 0;

        for (int i = 0; i < paraules.length; ++i) {
            double idf = idf(paraules[i], documents);
            for (int j = 0; j < documents.size(); ++j) {
                tfidf[j] += (tf(paraules[i], documents.get(j)) * idf);
            }
        }
        return IndexValuePair.krellevants(tfidf, k);
    }

    public static List<String> llegirFromText(String pathFile) throws IOException {
        String line;
        FileReader file = new FileReader(pathFile);
        BufferedReader br = new BufferedReader(file);
        List<String> text = new ArrayList<String>();
        //Gets each line till end of file is reached
        while((line = br.readLine()) != null) {
            //Splits each line into words
            String words[] = line.split((" |,|\\.|!|¡|\\?|¿"));
            for (int i = 0; i < words.length; ++i)
                if (words[i] != "") text.add(words[i]);
        }
        return text;
    }

    public static List<String> llegirFromString(String contingut) {
        List<String> text = new ArrayList<String>();
        String words[] = contingut.split((" |,|\\.|!|¡|\\?|¿"));
        for (int i = 0; i < words.length; ++i)
            if (words[i] != "") text.add(words[i]);
        return text;
    }

    public static void main(String[] args) throws IOException {
        List<String> text = llegirFromText("/Users/alexares/Desktop/subgrup-prop11.3/src/data.txt");
        System.out.println(text);
        text = llegirFromString("hola que tal, espero que separi be ¡jaja!");
        System.out.println(text);
    }
}

/*
    **DOCUMENTATION:
        https://towardsdatascience.com/relevance-ranking-simplified-e8eeea829713
 */