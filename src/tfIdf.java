package src;
import java.util.*;

public class tfIdf {
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

    public static double[] termsTfIdf(String[] paraules, List<String[]> documents, int k) {
        double[] tfidf = new double[documents.size()];
        for (int i = 0; i < tfidf.length; ++i) tfidf[i] = 0;

        for (int i = 0; i < paraules.length; ++i) {
            double idf = idf(paraules[i], documents);
            for (int j = 0; j < documents.size(); ++j) {
                tfidf[j] += (tf(paraules[i], documents.get(j)) * idf);
            }
        }
        return tfidf;
    }

    public static void main(String[] args) {

        String[] doc1 = new String[]{"Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum"};
        String[] doc2 =  new String[]{"Vituperata", "incorrupte", "at", "ipsum", "pro", "quo"};
        String[] doc3 =  new String[]{"Has", "persius", "disputationi", "id", "simul"};
        List<String[]> documents = Arrays.asList(doc1, doc2, doc3);

        String[] paraules = new String[]{"Lorem", "sit", "id"};

        double[] tfidf = termsTfIdf(paraules, documents, 0);
        for (int i = 0; i < tfidf.length; ++i) {
            System.out.print(tfidf[i] + " ");
        }
    }
}

/*
    **DOCUMENTATION:
        https://towardsdatascience.com/relevance-ranking-simplified-e8eeea829713
 */