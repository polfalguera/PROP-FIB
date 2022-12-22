package FONTS.Domini;
import FONTS.Persistencia.Persistencia;

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
     * Representa una cache amb l'index dels continguts carregats a memoria.
     * En cada posició tenim els index de la cache LRU.
     * El primer element de la cua és aquell que s'ha accedit fa més temps
     */
    private Queue<Integer> cache;

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
        this.cache = new LinkedList<Integer>();
        try {
            this.stopWords = assignarStopWords();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Assigna les frequencies en freqContingut.
     * @param freq assigna freq a l'estructura de dades freqContingut.
     */
    public void setFrequencies(List<HashMap<String, Integer>> freq) {
        this.freqContingut = freq;
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
            String path = Paths.get("RESOURCES/empty-ca-utf8.txt").toAbsolutePath().toString();
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            //empty-eng.txt
            path = Paths.get("RESOURCES/empty-eng-utf8.txt").toAbsolutePath().toString();
            file = new FileReader(path);
            br = new BufferedReader(file);
            while((line = br.readLine()) != null) result.add(line);
            //empty-sp.txt
            path = Paths.get("RESOURCES/empty-sp-utf8.txt").toAbsolutePath().toString();
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
     * @param paraula és la paraula per la que es calcula idf.
     * @param id índex del contingut a obtenir les freqüències
     * @param mode és el mode per calcular el idf
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
     * @param paraula és la paraula per la que es calcula idf.
     * @param mode és el mode per calcular el idf
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
     * Inicialitza els Continguts a null perquè inicalment no tenim cap contingut carregat en memoria.
     * @param size és la mida que tindra Contignut inicialment.
     */
    public void inicializarContinguts(int size) {
        List<String> c = new ArrayList<String>();
        for (int i = 0; i < size; ++i) c.add(null);
        this.Contingut = c;
    }

    /**
     * Actualitza l'ordre de la cache seguit l'ordre LRU.
     * @param index és l'index que acabam de accdeir i per tant hem de actualitzar l'ordre de la cua.
     */
    private void actualitzarCache(int index) {
        List<Integer> aux = new ArrayList<>(cache.size());
        for (int i = 0; i < cache.size(); ++i) aux.add(i, cache.poll());
        boolean esta = false;
        for (int i = 0; i < aux.size(); ++i) {
            if (aux.get(i) != index) esta = true;
            else cache.add(aux.get(i));
        }
        if (esta) cache.add(index);
    }

    /**
     * Actualitza els valors de la cache donat un index esborrat.
     * @param index és el índex d'un contingut esborrat.
     */
    private void actualitzarCacheIndex(int index) {
        for (int i = 0; i < cache.size(); ++i) {
            int id = cache.poll();
            if (id > index) cache.add(id - 1);
            else cache.add(id);
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

        if (cache.size() < 5) cache.add(Contingut.size());
        else {
            int ultim = cache.poll();
            Contingut.set(ultim, null);
            cache.add(Contingut.size());
        }

        this.Contingut.add(contingut);
        this.freqContingut.add(text);
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

        if (cache.size() < 5) {
            if (cache.contains(id)) actualitzarCache(id);
            else cache.add(id);
        }
        else {
            if (cache.contains(id)) {
                actualitzarCache(id);
            }
            else {
                int ultim = cache.poll();
                Contingut.set(ultim, null);
                cache.add(id);
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
     *             1 --> freq*log(N/n).
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
        if (cache.contains(id)) return this.Contingut.get(id);
        else {
            throw new Exception("El contingut no està en memoria");
        }
    }
    /**
     * Eliminem el Contingut del Document amb índex id.
     * @param id és l'índex del Document.
     */
    public void eliminarContingut(int id) throws Exception {
        if (id >= this.Contingut.size()) throw new Exception("Error: index out of bounds");

        cache.remove(id);
        actualitzarCacheIndex(id);
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
    /**
     * Obtenim totes les Frequencies dels Documents que hi ha fins al moment.
     * @return retorna una llista amb les frequencies de cada un dels Documents.
     */
    public List<HashMap<String, Integer>> getFreqContingut() { return this.freqContingut; }
}