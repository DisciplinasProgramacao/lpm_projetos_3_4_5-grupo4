import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

class SerieTest {

    private Serie serie;
    @Test
    void testVerificaAudiencia()  {
        serie = new Serie("a volta dos que não foram", "comédia", "pt-br", 3);
        serie.registrarAudiencia();
        assertEquals(1, serie.getAudiencia());
    }

    @Test
    void testInstanciaSerie(){
        assertThrows(Error.class, () -> new Serie("a volta dos que não foram", "comédia", "pt-br", 1));
    }

    @Test
    public void testGeraDadosSerie() {
        Serie serie2 = new Serie("Stranger Things", "comédia", "pt-br", 4);

        String dadosSerie = serie2.geraDadosSerie();

        String[] campos = dadosSerie.split(";");
        assertEquals(3, campos.length);
        assertTrue(campos[0].matches("\\d+"));
        assertEquals("Stranger Things", campos[1]);
        assertEquals("4", campos[2]);
    }
}
