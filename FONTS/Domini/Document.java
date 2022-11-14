package FONTS.Domini;

public class Document {

    /**
     * Titol del document
     */
    private String titol;

    /**
     * Autor del document
     */
    private String autor;

    /**
     * Constructora del document
     * Inicialitza les variables titol i autor
     */
    public Document(String nouTitol, String nouAutor) {
        this.titol = nouTitol;
        this.autor = nouAutor;
    }

    /**
     * Consultora
     * @return Retorna el titol
     * */
    public String getTitol() {
        return this.titol;
    }

    /**
     * Consultora
     * @return Retorna l'autor
     * */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Modificadora
     * @param nouTitol es el titol que pasara a tenir el document
     * */
    public void setTitol(String nouTitol) {
        this.titol = nouTitol;
    }

    /**
     * Modificadora
     * @param nouAutor es l'autor que pasara a tenir el document
     * */
    public void setAutor(String nouAutor) {
        this.autor = nouAutor;
    }

}
