import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmeTest {
    Filme filme;
    @BeforeEach
    void setUp(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        filme = new Filme("Filme Teste", "Ação", "Português", 120, new Date());
    }


    @Test
    public void testGetDuracaoSegundos() {
        Integer duracaoSegundos = filme.getDuracaoSegundos();
        assertEquals(Integer.valueOf(7200), duracaoSegundos);
    }

    @Test
    public void testToString() {
        String expected = "Media{nome='Filme Teste', genero='Ação', idioma='Português', audiencia=0}" +
                "\nFilme{duracao=120, dataLancamento=" + filme.getDataLancamento() + "}";
        String result = filme.toString();
        assertEquals(expected, result);
    }

    @Test
    public void salvarTodosFilmesTest() throws IOException {
        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme);
        filmes.add(new Filme("Filme 2", "Comédia", "Inglês", 90, new Date()));

        Filme.salvarTodosFilmes(filmes);

        assertTrue(new File("filmes.dat").exists());

        new File("filmes.dat").delete();
    }

    @Test
    public void carregarTodosFilmesTest() throws IOException, ClassNotFoundException {
        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme);
        filmes.add(new Filme("Filme 2", "Comédia", "Inglês", 90, new Date()));
        Filme.salvarTodosFilmes(filmes);
        List<Filme> filmesCarregados = Filme.carregarTodosFilmes();
        assertEquals(filmes.toString(), filmesCarregados.toString());
        new File("filmes.dat").delete();
    }

}