import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioTest {
    PlataformaStreaming plataforma = new PlataformaStreaming("teste", true);

    @BeforeEach
    void setUp() {
        for (int i = 0; i <= 120; i++) {
            String nomeCliente = "Cliente " + i;
            Cliente cliente = new Cliente(nomeCliente, "123");
            plataforma.adicionarCliente(cliente);
            Filme filme = new Filme(i, "Filme Teste " + i, "Romance", "Português", 120, new Date());
            plataforma.adicionarMidia(filme);
        }
    }

   @Test
    void testeClienteQueMaisAssistiu() {
       int auxCliente2 = 0;
       int auxCliente3 = 0;
       for (Media m : plataforma.getMidias()) {
            plataforma.getClientes().get(0).listaJaVistas.add(new ItemListaJaVista(m, new Date()));
            if (auxCliente2 < 30) {
                plataforma.getClientes().get(1).listaJaVistas.add((new ItemListaJaVista(m, new Date())));
            }
            if (auxCliente3 < 50) {
                plataforma.getClientes().get(2).listaJaVistas.add((new ItemListaJaVista(m, new Date())));
            }
            auxCliente2++;
            auxCliente3++;
        }
       assertEquals(Relatorio.clienteComMaisMidias(plataforma), "O Cliente: Cliente 0 foi o que que mais assistiu, totalizando: 121");
    }

    @Test
    void testeClienteQueMaisAvaliou() {
        int auxCliente2 = 0;
        plataforma.getMidias().forEach(media -> {
            plataforma.getClientes().get(0).listaJaVistas.add((new ItemListaJaVista(media)));
            plataforma.getClientes().get(0).avaliar(media.nome, 4);
        });
        for (Media media: plataforma.getMidias()) {
            if (auxCliente2 < 40) {
                Cliente c1 = plataforma.getClientes().get(1);
                Cliente c2 = plataforma.getClientes().get(2);
                c1.listaJaVistas.add((new ItemListaJaVista(media)));
                c2.listaJaVistas.add((new ItemListaJaVista(media)));
                c1.avaliar(media.nome, 4);
                c2.avaliar(media.nome, 4);
            } else {
                break;
            }
            auxCliente2++;
        }
        assertEquals(Relatorio.clienteQueMaisAvaliou(plataforma), "O Cliente: Cliente 0 foi o que mais avaliou, totalizando: 121");
    }


    @Test
    void testePorcentagemClientesComMais15Avaliacoes() {
        int auxCliente2 = 0;
        plataforma.getMidias().forEach(media -> {
            plataforma.getClientes().get(0).listaJaVistas.add((new ItemListaJaVista(media)));
            plataforma.getClientes().get(0).avaliar(media.nome, 4);
        });
        for (Media media: plataforma.getMidias()) {
            if (auxCliente2 < 40) {
                Cliente c1 = plataforma.getClientes().get(1);
                Cliente c2 = plataforma.getClientes().get(2);
                c1.listaJaVistas.add((new ItemListaJaVista(media)));
                c2.listaJaVistas.add((new ItemListaJaVista(media)));
                c1.avaliar(media.nome, 4);
                c2.avaliar(media.nome, 4);
            } else {
                break;
            }
            auxCliente2++;
        }
        assertEquals(Relatorio.porcentagemClientesMaisDe15Avaliacoes(plataforma), "2,48");
    }


    @Test
    void teste10MidiasMaisAvaliadasComMinimo100Avaliacoes() {
        List<Media> maisAvaliadas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            maisAvaliadas.add(plataforma.getMidias().get(i));
                for (Cliente c: plataforma.getClientes()){
                    c.listaJaVistas.add((new ItemListaJaVista(plataforma.getMidias().get(i))));
                    c.avaliar(plataforma.getMidias().get(i).nome, 4);
                }
        }
        plataforma.getMidias().get(0).addAvaliacao(new Avaliacao(new Cliente("teste", "123"), 5));
        for (int i = 11; i< plataforma.getMidias().size(); i++) {
            for (int j = 0; j < 50; j++) {
                plataforma.getClientes().get(j).listaJaVistas.add((new ItemListaJaVista(plataforma.getMidias().get(i))));
                plataforma.getClientes().get(j).avaliar(plataforma.getMidias().get(i).nome, 4);
            }
        }
        assertEquals(Relatorio.dezMidiasMaisAvaliadasComMininoDe100Avaliacoes(plataforma), maisAvaliadas);
    }

    @Test
    void teste10MidiasMaisAvaliadasComMinimo100AvaliacoesPorGenero() {
        List<Media> maisAvaliadas = new ArrayList<>();

        for (int i = 0; i <= 120; i++) {
            String nomeCliente = "Cliente " + i*10000;
            Cliente cliente = new Cliente(nomeCliente, "123");
            plataforma.adicionarCliente(cliente);
            Filme filme = new Filme(i, "Filme Teste " + i, "Suspense", "Português", 120, new Date());
            plataforma.adicionarMidia(filme);
        }
        List<Media> mediaSuspensePlataforma = plataforma.getMidias().stream().filter(m -> m.getGenero().equals("Suspense")).collect(Collectors.toList());
        for (int i = 0; i < 2; i++) {
            maisAvaliadas.add(mediaSuspensePlataforma.get(i));
            for (Cliente c: plataforma.getClientes()){
                c.listaJaVistas.add((new ItemListaJaVista(mediaSuspensePlataforma.get(i))));
                c.avaliar(mediaSuspensePlataforma.get(i).nome, 4);
            }
        }
        mediaSuspensePlataforma.get(0).addAvaliacao(new Avaliacao(new Cliente("teste", "123"), 5));
        assertEquals(Relatorio.dezMidiasMaisAvaliadasComMininoDe100AvaliacoesPorGenero(plataforma, "Suspense"), maisAvaliadas);
    }

    @Test
    void teste10MidiasMaisVisualizadas() {
        List<Media> mediasMaisVistas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Media m = plataforma.getMidias().get(i);
            mediasMaisVistas.add(m);
            for (int j = 0; j < plataforma.getClientes().size(); j++) {
                Cliente c = plataforma.getClientes().get(j);
                c.listaJaVistas.add((new ItemListaJaVista(m)));
            }
        }
        assertEquals(Relatorio.dezMidiasComMaisVisualizacoes(plataforma), mediasMaisVistas);
    }

    @Test
    void teste10MidiasMaisVisualizadasPorGenero() {
        for (int i = 0; i <= 120; i++) {
            String nomeCliente = i + " Cliente";
            Cliente cliente = new Cliente(nomeCliente, "123");
            plataforma.adicionarCliente(cliente);
            Filme filme = new Filme(i, "Filme Teste " + i, "Policial", "Português", 120, new Date());
            plataforma.adicionarMidia(filme);
            cliente.listaJaVistas.add((new ItemListaJaVista(filme)));
        }

        List<Media> mediasMaisVistas = new ArrayList<>();
        List<Media> suspensesPlataforma = plataforma.getMidias().stream().filter(m -> m.getGenero().equals("Policial")).collect(Collectors.toList());
        for (int i = 0; i < 10; i++) {
            Media m = suspensesPlataforma.get(i);
            mediasMaisVistas.add(m);
            for (int j = 0; j < plataforma.getClientes().size(); j++) {
                Cliente c = plataforma.getClientes().get(j);
                c.listaJaVistas.add((new ItemListaJaVista(m)));
            }
        }
        assertEquals(Relatorio.dezMidiasMaisVisualizacoesPorGenero(plataforma, "Policial"), mediasMaisVistas);
    }

}
