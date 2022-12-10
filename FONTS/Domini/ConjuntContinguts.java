package FONTS.Domini;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import java.util.*;

/**
 * Representa el controlador del Contingut.
 * @author Alex Ares Marin.
 */
public class ConjuntContinguts {
    /**
     * Representa el conjunt de Continguts.
     */
    private static class IndexValuePair {
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

    /**
     * Representa la frequencia de les paraules de cada contingut.
     * Cada HashMap es troba en la mateixa posicio de la llista que la posicio
     * del seu contingut en l'estructura de dades Contingut
     */
    private List<HashMap<String, Integer>> freqContingut;

    /**
     * Representa els continguts de tots els documents.
     * A cada posicio conte el contigut del document que es troba
     * en la mateixa posicio en l'estructura de dades documents
     * del ControladorDocuments
     */
    private List<String> Contingut;

    /**
     * Representa les stopWords
     */
    private Set<String> stopWords;

    /**
     * Constructora d'un conjunt de Continguts.
     */
    public ConjuntContinguts() throws Exception {
        this.freqContingut = new ArrayList<HashMap<String, Integer>>();
        this.Contingut = new ArrayList<String>();
        try {
            this.stopWords = assignarStopWords();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Llegeix dels fitxers amb les paraules considerades StopWords.
     * @return Retorna un conjunt amb les paraules considerades StopWords.
     */
    private Set<String> assignarStopWords() throws Exception {
        try {
            Set<String> result = new HashSet<String>();
            //empty-ca.txt
            String line;
            String path = Paths.get("DATA/empty-ca-utf8.txt").toAbsolutePath().toString();
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            //empty-eng.txt
            path = Paths.get("DATA/empty-eng-utf8.txt").toAbsolutePath().toString();
            file = new FileReader(path);
            br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            //empty-sp.txt
            path = Paths.get("DATA/empty-sp-utf8.txt").toAbsolutePath().toString();
            file = new FileReader(path);
            br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            return result;
        } catch (Exception e) {
            throw new Exception("Error: no s'han pogut carregar les stopwords");
        }
    }
    /**
     * Calcula depenent del mode escollit el tf per a l'assignació de pesos.
     * @return retorna un double amb els càlculs corresponents.
     */
    private double tf(String paraula, int id, int mode) {
        HashMap<String, Integer> freq = this.freqContingut.get(id);
        double n = 0;
        for (Map.Entry<String, Integer> set : freq.entrySet()) {
            n += set.getValue();
        }
        if (mode == 1 && freq.containsKey(paraula)) return Math.log(1+freq.get(paraula));
        else if (mode != 1 && freq.containsKey(paraula)) return freq.get(paraula);
        else return 0;
    }
    /**
     * Calcula depenent del mode escollit l'idf per a l'assignació de pesos.
     * @return retorna un double amb els càlculs corresponents.
     */
    private double idf(String paraula, int mode) {
        double n = 0;
        for (HashMap<String, Integer> doc : this.freqContingut) {
            if (doc.containsKey(paraula)) n++;
        }
        if (mode == 1) return 1;
        else if (n == 0) return 0;
        else return Math.log(this.Contingut.size() / n);
    }

    /**
     * Afegeix un nou Contingut llegint d'un fitxer.
     * @param path és la ruta on es troba el fitxer.
     */
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
                    if (!this.stopWords.contains(word) && !word.equals("")) {
                        if (!text.containsKey(word)) text.put(word, 1);
                        else text.put(word, text.get(word)+1);
                    }
                }
            }

            if (contingut.isEmpty()) throw new Exception("Error: contingut buit");
            this.freqContingut.add(text);
            this.Contingut.add(contingut.toString());
        } catch (Exception e) {
            throw new Exception("Error: path del document incorrecte");
        }
    }
    /**
     * Afegeix un nou Contingut.
     * @param contingut és el Contingut a introduir.
     */
    public void afegirContingut(String contingut) throws Exception {
        if (contingut == "") throw new Exception("Error: contingut buit");

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split("\\p{Punct}| |\\n|¿|¡");

        for (String word : words) {
            word = word.toLowerCase();
            if (!this.stopWords.contains(word) && !word.equals("")) {
                if (!text.containsKey(word)) text.put(word, 1);
                else text.put(word, text.get(word)+1);
            }
        }

        this.Contingut.add(contingut);
        this.freqContingut.add(text);
    }
    /**
     * Modifica el Contingut llegint el nou Contingut d'un fitxer.
     * @param id és l'índex del Document.
     * @param path és la ruta on es troba el fitxer.
     */
    public void modificarContingutPath(int id, String path) throws Exception {
        if (this.freqContingut.size() <= id) throw new Exception("Error: index out of bounds");
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
                    if (!this.stopWords.contains(word) && word != "") {
                        if (!text.containsKey(word)) text.put(word, 1);
                        else text.put(word, text.get(word)+1);
                    }
                }
            }

            if (contingut.isEmpty()) throw new Exception("Error: contingut buit");
            this.freqContingut.set(id, text);
            this.Contingut.set(id, contingut.toString());
        } catch (Exception e) {
            throw new Exception("Error: path del document incorrecte");
        }
    }
    /**
     * Modifica el Contingut per un altre nou.
     * @param id és l'índex del Document.
     * @param contingut és el nou Contingut a reemplaçar.
     */
    public void modificarContingut(int id, String contingut) throws Exception {
        if (this.freqContingut.size() <= id) throw new Exception("Error: index out of bounds");
        if (contingut == "") throw new Exception("Error: contingut buit");

        HashMap<String, Integer> text = new HashMap<String, Integer>();
        String[] words = contingut.split("\\p{Punct}| |\\n|¿|¡");

        for (String word : words) {
            word = word.toLowerCase();
            if (!this.stopWords.contains(word) && word != "") {
                if (!text.containsKey(word)) text.put(word, 1);
                else text.put(word, text.get(word)+1);
            }
        }

        this.Contingut.set(id, contingut);
        this.freqContingut.set(id, text);
    }
    /**
     * Indica els índexs dels k Continguts més rellevants amb el conjunt de paraules.
     * @param paraules és el conjunt de paraules pel que buscar rellevancia.
     * @param k és el nombre de Continguts a buscar.
     * @param mode indica amb quin mode calcular l'assignació de pesos en tf-idf.
     *             0 --> freq*log(N/n).
     *             1 --> log(1+freq).
     * @return retorna un conjunt amb els índexs més rellevants.
     */
    public int[] kRellevants(String[] paraules, int k, int mode) {
        if (k > this.Contingut.size()) k = this.Contingut.size();
        double[] tfidf = new double[this.Contingut.size()];
        for (int i = 0; i < tfidf.length; ++i) tfidf[i] = 0;

        for (String paraula : paraules) {
            paraula = paraula.toLowerCase();
            double idf = idf(paraula, mode);
            for (int j = 0; j < this.Contingut.size(); ++j) {
                tfidf[j] += (tf(paraula, j, mode) * idf);
            }
        }
        return IndexValuePair.krellevants(tfidf, k);
    }
    /**
     * Obtenim el Contingut del Document amb índex id.
     * @param id és l'índex del Document.
     * @return retorna un String amb el Contingut del Document amb índex id.
     */
    public String getContingut(int id) throws Exception {
        if (id >= this.Contingut.size()) throw new Exception("Error: index out of bounds");
        return this.Contingut.get(id);
    }
    /**
     * Eliminem el Contingut del Document amb índex id.
     * @param id és l'índex del Document.
     */
    public void eliminarContingut(int id) throws Exception {
        if (id >= this.Contingut.size()) throw new Exception("Error: index out of bounds");
        this.freqContingut.remove(id);
        this.Contingut.remove(id);
    }
    /**
     * Obtenim totes les paraules del Contingut del Document amb índex id sense stopWords.
     * @param id és l'índex del Document.
     * @return retorna un vector de String amb les paraules del Document amb índex id.
     */
    public String[] obtenirParaulesContingut(int id) throws Exception {
        if (id >= this.Contingut.size()) throw new Exception("Error: index out of bounds");
        String[] aux = this.Contingut.get(id).split("\\p{Punct}| |\\n|¿|¡");
        ArrayList<String> res = new ArrayList<>();
        for (String word: aux) if (word != "" && !this.stopWords.contains(word)) res.add(word);
        return res.toArray(new String[0]);
    }
    /**
     * Obtenim tots els Continguts dels Documents que hi ha fins al moment.
     * @return retorna una llista amb el Contingut de cada un dels Documents.
     */
    public List<String> getConjuntContinguts() {
        return this.Contingut;
    }
}