package test;
import org.junit.Test;
import capaDomini.Document;

import static org.junit.Assert.*;


public class DocumentTest {
    /**
     * Test que comprova que donat un document retorni correctament el seu autor.
     */
    @Test
    public void getAutorDoc_returnAutorDoc() {
        Document doc = new Document("titol","autor");
        assertEquals("autor",doc.getAutor());
    }

    /**
     * Test que comprova que donat un document retorni correctament el seu títol.
     */
    @Test
    public void getTitolDoc_returnTitolDoc() {
        Document doc = new Document("titol","autor");
        assertEquals("titol",doc.getTitol());
    }

    /**
     * Test que comprova que donat un document actualitzi el seu autor correctament.
     */
    @Test
    public void setNouAutorDoc_returnNouAutorDoc() {
        Document doc = new Document("autor","titol");
        doc.setAutor("nouAutor");
        assertEquals("nouAutor",doc.getAutor());
    }

    /**
     * Test que comprova que donat un document actualitzi el seu títol correctament.
     */
    @Test
    public void setNouTitolDoc_returnNouTitolDoc() {
        Document doc = new Document("autor","titol");
        doc.setTitol("nouTitol");
        assertEquals("nouTitol",doc.getTitol());
    }
}