import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Test
    public void salvarTodasSeries() throws IOException {
        serie = new Serie("a volta dos que não foram", "comédia", "pt-br", 3);
        Serie.salvarTodasSeries(List.of(serie));
        assertTrue(new File("series.dat").exists());
        new File("series.dat").delete();
    }

    @Test
    public void carregarTodasSeries() throws IOException, ClassNotFoundException {
        serie = new Serie("a volta dos que não foram", "comédia", "pt-br", 3);
        Serie.salvarTodasSeries(List.of(serie));
        List<Serie> seriesCarregadas = Serie.carregarTodasSeries();
        assertEquals(seriesCarregadas.toString(), List.of(serie).toString());
        new File("series.dat").delete();
    }
}
