import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmeTest {
    Filme filme;
    @BeforeEach
    void setUp(){
        filme = new Filme("Filme Teste", "Ação", "Português", 120, new Date());
    }

    @Test
    public void testGetDuracaoSegundos() {
        Integer duracaoSegundos = filme.getDuracaoSegundos();
        assertEquals(Integer.valueOf(7200), duracaoSegundos);
    }

    @Test
    public void salvarTodosFilmesTest() throws IOException {
        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme);
        filmes.add(new Filme("Filme 2", "Comédia", "Inglês", 90, new Date()));

        Filme.salvarTodosFilmes(filmes);

        assertTrue(new File("data/filmes.dat").exists());

        new File("data/filmes.dat").delete();
    }

    @Test
    public void carregarTodosFilmesTest() throws IOException, ClassNotFoundException {
        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme);
        filmes.add(new Filme("Filme 2", "Comédia", "Inglês", 90, new Date()));
        Filme.salvarTodosFilmes(filmes);
        List<Filme> filmesCarregados = Filme.carregarTodosFilmes();
        assertEquals(filmes.toString(), filmesCarregados.toString());
        new File("data/filmes.dat").delete();
    }

    @Test
    public void testaMediaDeAvaliacoes() throws IOException, ClassNotFoundException {
        Cliente cliente1 = new Cliente("Nilocan2022", "sabhsjabjhbsajh");
        Cliente cliente2 = new Cliente("Felipe Melo", "ssssaa");
        Cliente cliente3 = new Cliente("Luva", "popyedoskank");

        Avaliacao avaliacao1 = new Avaliacao(cliente1, 3);
        Avaliacao avaliacao2 = new Avaliacao(cliente2, 4);
        Avaliacao avaliacao3 = new Avaliacao(cliente3, 5);

        filme.addAvaliacao(avaliacao1);
        filme.addAvaliacao(avaliacao2);
        filme.addAvaliacao(avaliacao3);

        assertEquals(4, filme.mediaDeAvaliacoes());
    }
}
