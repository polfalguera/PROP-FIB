package src;
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

    private static double tf(String paraula, String[] contingut) {
        double result = 0;
        for (int i = 0; i < contingut.length; ++i) {
            if (contingut[i].equalsIgnoreCase(paraula)) ++result;
        }
        return result / contingut.length;
    }

    private static double idf(String paraula, List<String[]> documents) {
        double n = 0;
        for (String[] doc : documents) {
            for (String word : doc) {
                if (paraula.equalsIgnoreCase(word)) {
                    ++n;
                }
            }
        }
        return Math.log(documents.size() / n);
    }

    public static int[] termsTfIdf(String[] paraules, List<String[]> documents, int k) {
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
}

/*
    **DOCUMENTATION:
        https://towardsdatascience.com/relevance-ranking-simplified-e8eeea829713
 */