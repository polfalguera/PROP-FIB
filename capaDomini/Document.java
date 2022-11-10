package capaDomini;

public class Document {
    private String titol;
    private String autor;

    public Document(String nouTitol, String nouAutor) {
        this.titol = nouTitol;
        this.autor = nouAutor;
    }

    public String getTitol() {
        return this.titol;
    }
    public String getAutor() {
        return this.autor;
    }

    public void setTitol(String nouTitol) {
        this.titol = nouTitol;
    }
    public void setAutor(String nouAutor) {
        this.autor = nouAutor;
    }

}
