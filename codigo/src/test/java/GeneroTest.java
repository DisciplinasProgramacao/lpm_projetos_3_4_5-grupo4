import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class GeneroTest {
    Filme filme;
    Serie serie;

    @Test
    public void TestefilmeGeneroAleatorio() {
        Generos genero = Generos.randomGeneros();
        filme = new Filme("Filme Teste", genero.name(), "Português", 120, new Date());
        assertEquals(genero.name(), filme.getGenero());
    }

    @Test
    public void testefilmeRomance() {
        filme = new Filme("Filme Teste", "Romance", "Português", 120, new Date());
        assertEquals("Romance", filme.getGenero());

    }

    @Test
    public void testefilmeAcao() {
        filme = new Filme("Filme Teste", "acao", "Português", 120, new Date());
        assertEquals("Acao", filme.getGenero());

    }

    @Test
    public void testfilmeComedia() {
        filme = new Filme("Filme Teste", "Comédia", "Português", 120, new Date());
        assertEquals("Comédia", filme.getGenero());

    }

    @Test
    public void testeSerieComedia() {
        serie = new Serie("Filme Teste", "Comédia", "Português", new Date(), 120);
        assertEquals("Comédia", serie.getGenero());
    }

    @Test
    public void testeSerieAcao() {
        serie = new Serie("Filme Teste", "acao", "Português", new Date(), 120);
        assertEquals("Acao", serie.getGenero());
    }

    @Test
    public void testeVarios() {
        Filme filme1 = new Filme("Filme Teste", "Romance", "Português", 120, new Date());
        Serie serie1 = new Serie("Calcinha Preta Documentário", "Acao", "catalão", new Date(), 11);
        Serie serie2 = new Serie("Quebrando camas", "Comédia", "russo", new Date(), 13);
        Serie serie3 = new Serie("Filme Teste", "Documentário", "Português", new Date(), 120);

        assertEquals("Romance", filme1.getGenero());
        assertEquals("Acao", serie1.getGenero());
        assertEquals("Comédia", serie2.getGenero());
        assertEquals("Documentário", serie3.getGenero());

    }
}
