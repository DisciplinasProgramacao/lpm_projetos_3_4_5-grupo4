import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class GeneroTest {
    Filme filme;
    Serie serie;

    @Test
    public void TestefilmeGeneroAleatorio() {
        Generos genero = Generos.randomGeneros();
        filme = new Filme("Filme Teste", genero.name(), "Português", 120, new Date(), false);
        assertEquals(genero.name(), filme.getGenero());
    }

    @Test
    public void testefilmeRomance() {
        filme = new Filme("Filme Teste", "Romance", "Português", 120, new Date(), false);
        assertEquals("Romance", filme.getGenero());

    }

    @Test
    public void testefilmeAcao() {
        filme = new Filme("Filme Teste", "acao", "Português", 120, new Date(), false);
        assertEquals("Acao", filme.getGenero());

    }

    @Test
    public void testfilmeComedia() {
        filme = new Filme("Filme Teste", "Comédia", "Português", 120, new Date(), false);
        assertEquals("Comédia", filme.getGenero());

    }

    @Test
    public void testeSerieComedia() {
        serie = new Serie("Filme Teste", "Comédia", "Português", new Date(), 120, false);
        assertEquals("Comédia", serie.getGenero());
    }

    @Test
    public void testeSerieAcao() {
        serie = new Serie("Filme Teste", "acao", "Português", new Date(), 120, false);
        assertEquals("Acao", serie.getGenero());
    }

    @Test
    public void testeVarios() {
        Filme filme1 = new Filme("Filme Teste", "Romance", "Português", 120, new Date(), false);
        Serie serie1 = new Serie("Calcinha Preta Documentário", "Acao", "catalão", new Date(), 11, false);
        Serie serie2 = new Serie("Quebrando camas", "Comédia", "russo", new Date(), 13, false);
        Serie serie3 = new Serie("Filme Teste", "Documentário", "Português", new Date(), 120, false);

        assertEquals("Romance", filme1.getGenero());
        assertEquals("Acao", serie1.getGenero());
        assertEquals("Comédia", serie2.getGenero());
        assertEquals("Documentário", serie3.getGenero());

    }
}
