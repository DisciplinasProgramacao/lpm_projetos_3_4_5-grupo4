import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;
    private Serie serie1;
    private Serie serie2;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Nilocan2022", "sabhsjabjhbsajh");
        serie1 = new Serie("Calcinha Preta Documentário", "Ação", "catalão", 11);
        serie2 = new Serie("Quebrando camas", "Comédia", "russo", 13);
    }

    @Test
    void testAdicionarNaLista() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        List<Media> listaParaVer = cliente.getListaParaVer();

        assertEquals(2, listaParaVer.size());
        assertTrue(listaParaVer.contains(serie1));
        assertTrue(listaParaVer.contains(serie2));
    }

    @Test
    void testRetirarDaLista() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        cliente.retirarDaLista("Calcinha Preta Documentário");

        List<Media> listaParaVer = cliente.getListaParaVer();

        assertEquals(1, listaParaVer.size());
        assertFalse(listaParaVer.contains(serie1));
        assertTrue(listaParaVer.contains(serie2));
    }

    @Test
    void testFiltrarPorGenero() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);

        List<Media> seriesFiltradas = cliente.filtrarPorGenero("Ação");

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

//    @Test
//    void testFiltrarPorQtdeEpisodios() {
//        cliente.adicionarNaLista(serie1);
//        cliente.adicionarNaLista(serie2);
//
//        List<Serie> seriesFiltradas = cliente.filtrarPorQtdeEpisodios(11);
//
//        assertEquals(1, seriesFiltradas.size());
//        assertTrue(seriesFiltradas.contains(serie1));
//    }

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
    void avaliarMedia() {
        cliente.listaJaVistas.add(serie1);
        cliente.avaliar(serie1.getNome(), 5);
        assertTrue(serie1.getAvaliacoes().contains(5));
    }

    @Test
    void tentarAvaliarMediaNaoVista() {
        cliente.avaliar(serie1.getNome(), 5);
        assertFalse(serie1.getAvaliacoes().contains(5));
    }
}
