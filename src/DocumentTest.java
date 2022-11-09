package src;
import org.junit.Test;

import src.Document;
import static org.junit.Assert.*;


class DocumentTest {
    /**
     * Test que comprova que donat un document retorni correctament el seu autor.
     */
    @Test
    void getAutorDoc_returnAutorDoc() {
        Document doc = new Document("autor","titol");
        assertEquals("autor",doc.getAutor());
    }

    /**
     * Test que comprova que donat un document retorni correctament el seu títol.
     */
    @Test
    void getTitolDoc_returnTitolDoc() {
        Document doc = new Document("autor","titol");
        assertEquals("titol",doc.getTitol());
    }

    /**
     * Test que comprova que donat un document actualitzi el seu autor correctament.
     */
    @Test
    void setNouAutorDoc_returnNouAutorDoc() {
        Document doc = new Document("autor","titol");
        doc.setAutor("nouAutor");
        assertEquals("nouAutor",doc.getAutor());
    }

    /**
     * Test que comprova que donat un document actualitzi el seu títol correctament.
     */
    @Test
    void setNouTitolDoc_returnNouTitolDoc() {
        Document doc = new Document("autor","titol");
        doc.setTitol("nouTitol");
        assertEquals("nouTitol",doc.getTitol());
    }
}