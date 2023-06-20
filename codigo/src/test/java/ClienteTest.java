import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;
    private Serie serie1;
    private Serie serie2;
    private Filme filme1;
    private Serie serie3;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Nilocan2022", "sabhsjabjhbsajh");
        filme1 = new Filme("Filme Teste", "Romance", "Português", 120, new Date());
        serie1 = new Serie("Calcinha Preta Documentário", "Acao", "catalão", new Date(), 11);
        serie2 = new Serie("Quebrando camas", "Comédia", "russo", new Date(), 13);
    }

    @Test
    void testAdicionarNaLista() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        Set<Media> listaParaVer = cliente.getListaParaVer();

        assertEquals(2, listaParaVer.size());
        assertTrue(listaParaVer.contains(serie1));
        assertTrue(listaParaVer.contains(serie2));
    }

    @Test
    void testRetirarDaLista() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        cliente.retirarDaLista("Calcinha Preta Documentário");

        Set<Media> listaParaVer = cliente.getListaParaVer();

        assertEquals(1, listaParaVer.size());
        assertFalse(listaParaVer.contains(serie1));
        assertTrue(listaParaVer.contains(serie2));
    }

    @Test
    void testFiltrarPorGenero() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(filme1);

        List<Media> seriesFiltradas = cliente.filtrarPorGenero("Acao");
        assertEquals(3, cliente.listaParaVer.size());
        assertEquals(1, seriesFiltradas.size());
        assertTrue(seriesFiltradas.contains(serie1));
    }

    @Test
    void testFiltrarPorIdioma() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        List<Media> seriesFiltradas = cliente.filtrarPorIdioma("russo");

        assertEquals(1, seriesFiltradas.size());
        assertTrue(seriesFiltradas.contains(serie2));
    }

    @Test
    void testRegistrarAudiencia() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        cliente.registrarAudiencia(serie1);
        cliente.registrarAudiencia(serie2);

        assertEquals(2, cliente.getListaJaVistas().size());
        assertTrue(cliente.getListaJaVistas().contains(serie1));
        assertTrue(cliente.getListaJaVistas().contains(serie2));
    }

    @Test
    public void testAvaliar() {
        cliente.listaJaVistas.add(new ItemListaJaVista(serie1));
        cliente.avaliar("Calcinha Preta Documentário", 4);

        assertEquals(1, serie1.getAvaliacoes().size());
        assertEquals(4, serie1.getAvaliacoes().get(0).nota);
    }

    @Test
    public void testIsClienteEspecialista() {
        Filme f = new Filme("filme 1", "genero 1", "pt", 10, new Date());
        Filme f2 = new Filme("filme 2", "genero 2", "en", 100, new Date());
        Filme f3 = new Filme("filme 3", "genero 2", "en", 100, new Date());
        Filme f4 = new Filme("filme 4", "genero 2", "en", 100, new Date());
        Filme f5 = new Filme("filme 5", "genero 2", "en", 100, new Date());
        for (Filme filme : List.of(f, f2, f3, f4, f5)) {
            cliente.registrarAudiencia(filme);
        }
        boolean resultado = cliente.isClienteEspecialista();
        assertTrue(resultado);
    }

    @Test
    public void testAvaliarComComentario() {
        Filme f = new Filme("filme 1", "genero 1", "pt", 10, new Date());
        Filme f2 = new Filme("filme 2", "genero 2", "en", 100, new Date());
        Filme f3 = new Filme("filme 3", "genero 2", "en", 100, new Date());
        Filme f4 = new Filme("filme 4", "genero 2", "en", 100, new Date());
        Filme f5 = new Filme("filme 5", "genero 2", "en", 100, new Date());

        for (Filme filme : List.of(f, f2, f3, f4, f5)) {
            cliente.registrarAudiencia(filme);
        }

        cliente.avaliarComComentario("filme 1", 5, "Ótimo filme!");

        Media media = cliente.listaJaVistas.stream()
                .filter(s -> s.getMedia().getNome().equals("filme 1"))
                .findFirst()
                .orElse(null).getMedia();
        assertEquals(1, media.getAvaliacoes().size());
        assertEquals("Ótimo filme!", media.getAvaliacoes().get(0).comentario);
    }

    @Test
    void tentarAvaliarMediaNaoVista() {
        cliente.avaliar(serie1.getNome(), 5);
        assertFalse(serie1.getAvaliacoes().contains(5));
    }

    @Test
    public void testMidiasValidasDeAvaliacao() {
        Cliente cliente = new Cliente("Lob", "loblob");

        Filme f1 = new Filme("filme 1", "genero 1", "pt", 10, new Date());
        Filme f2 = new Filme("filme 2", "genero 2", "en", 100, new Date());

        cliente.registrarAudiencia(f1);
        cliente.registrarAudiencia(f2);

        int midiasValidas = cliente.midiasValidasDeAvaliacao();

        assertEquals(2, midiasValidas);
    }
}
