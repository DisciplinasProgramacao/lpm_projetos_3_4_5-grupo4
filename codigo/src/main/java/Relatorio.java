import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class Relatorio {

    /**
     * Retorna uma String com o nome do cliente que mais assistiu mídias na plataforma e o total de mídias assistidas por ele.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @return a String com o resultado
     */
    public static String clienteComMaisMidias(PlataformaStreaming plataformaStreaming) {
        Cliente cliente = plataformaStreaming.getClientes().stream()
                .max(Comparator.comparingInt(c -> c.getListaJaVistas().size()))
                .orElse(null);
        if (nonNull(cliente)) {
            return "O Cliente: " + cliente.getNomeUsuario() + " foi o que que mais assistiu, totalizando: " + cliente.getListaJaVistas().size();
        }
        return "";
    }

    /**
     * Retorna uma String com o nome do cliente que mais avaliou mídias na plataforma e o total de avaliações feitas por ele.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @return a String com o resultado
     */
    public static String clienteQueMaisAvaliou(PlataformaStreaming plataformaStreaming) {
        Map<Cliente, Long> mapCliente = plataformaStreaming.getMidias().stream()
                .flatMap(c -> c.avaliacoes.stream())
                .collect(Collectors.groupingBy(Avaliacao::getCliente, Collectors.counting()));
        var clienteLongEntry = mapCliente.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).orElse(null);
        Cliente maisAvaliou =  clienteLongEntry.getKey();
        return "O Cliente: " + maisAvaliou.getNomeUsuario() + " foi o que mais avaliou, totalizando: " + clienteLongEntry.getValue();
    }

    /**
     * Retorna a porcentagem de clientes na plataforma que fizeram mais de 15 avaliações.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @return a porcentagem formatada como String
     */

    public static String porcentagemClientesMaisDe15Avaliacoes(PlataformaStreaming plataformaStreaming) {
        Map<Cliente, Long> mapCliente = plataformaStreaming.getMidias().stream()
                .flatMap(c -> c.avaliacoes.stream())
                .collect(Collectors.groupingBy(Avaliacao::getCliente, Collectors.counting()));
        Map<Cliente, Long> clientesComMaisDe15Avaliacoes = mapCliente.entrySet().stream()
                .filter(entry -> entry.getValue() > 15)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        DecimalFormat formato = new DecimalFormat("#.00");
        Double r =  ((double)clientesComMaisDe15Avaliacoes.size()/ plataformaStreaming.getClientes().size() * 100.0);
        System.out.println(formato.format(r));
        return formato.format(r);
    }

    /**
     * Retorna uma lista das 10 mídias mais avaliadas, em ordem decrescente de quantidade de avaliações e com no minimo 100 avaliacoes.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @return a lista das mídias mais avaliadas
     */
    public static List<Media> dezMidiasMaisAvaliadasComMininoDe100Avaliacoes(PlataformaStreaming plataformaStreaming) {
        List<Media> dezMidiasComMaisAvaliacoes = plataformaStreaming.getMidias().stream()
                .filter(media -> media.getAvaliacoes().size() >= 100)
                .sorted(Comparator.comparingInt(m -> m.getAvaliacoes().size()))
                .limit(10).collect(Collectors.toList());

        List<Media> ordemDecrescente = new ArrayList<>();
        for (int i = dezMidiasComMaisAvaliacoes.size() - 1; i >= 0; i--) {
            Media elemento = dezMidiasComMaisAvaliacoes.get(i);
            ordemDecrescente.add(elemento);
        }
        return ordemDecrescente;
    }

    /**
     * Retorna uma lista das 10 mídias mais avaliadas de um determinado gênero, em ordem decrescente de quantidade de avaliações.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @param genero o gênero das mídias
     * @return a lista das mídias mais avaliadas do gênero especificado
     */
    public static List<Media> dezMidiasMaisAvaliadasComMininoDe100AvaliacoesPorGenero(PlataformaStreaming plataformaStreaming, String  genero) {
        List<Media> dezMidiasComMaisAvaliacoes = plataformaStreaming.getMidias().stream()
                .filter(media -> media.getGenero().equals(genero) && media.getAvaliacoes().size() >= 100)
                .sorted(Comparator.comparingInt(m -> m.getAvaliacoes().size()))
                .limit(10)
                .collect(Collectors.toList());
        List<Media> ordemDecrescente = new ArrayList<>();
        for (int i = dezMidiasComMaisAvaliacoes.size() - 1; i >= 0; i--) {
            Media elemento = dezMidiasComMaisAvaliacoes.get(i);
            ordemDecrescente.add(elemento);
        }
        return ordemDecrescente;
    }


    /**
     * Retorna uma lista das 10 mídias com mais visualizações, em ordem crescente de audiência.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @return a lista das mídias mais visualizadas
     */
    public static List<Media> dezMidiasComMaisVisualizacoes(PlataformaStreaming plataformaStreaming) {
        List<Media> dezMidiasComMaisVisualizacoes = plataformaStreaming.getMidias().stream()
                .sorted(Comparator.comparingInt(Media::getAudiencia))
                .limit(10).collect(Collectors.toList());

        List<Media> ordemDecrescente = new ArrayList<>();
        for (int i = dezMidiasComMaisVisualizacoes.size() - 1; i >= 0; i--) {
            Media elemento = dezMidiasComMaisVisualizacoes.get(i);
            ordemDecrescente.add(elemento);
        }
        return dezMidiasComMaisVisualizacoes;
    }

    /**
     * Retorna uma lista das 10 mídias com mais visualizações de um determinado gênero, em ordem crescente de audiência.
     *
     * @param plataformaStreaming a plataforma de streaming
     * @param genero o gênero das mídias
     * @return a lista das mídias mais visualizadas do gênero especificado
     */

    public static List<Media> dezMidiasMaisVisualizacoesPorGenero(PlataformaStreaming plataformaStreaming, String genero) {
        List<Media> dezMidiasComMaisVisualizacoes = plataformaStreaming.getMidias().stream()
                .filter(m -> m.getGenero().equals(genero))
                .sorted(Comparator.comparingInt(Media::getAudiencia))
                .limit(10).collect(Collectors.toList());

        List<Media> ordemDecrescente = new ArrayList<>();
        for (int i = dezMidiasComMaisVisualizacoes.size() - 1; i >= 0; i--) {
            Media elemento = dezMidiasComMaisVisualizacoes.get(i);
            ordemDecrescente.add(elemento);
        }
        return dezMidiasComMaisVisualizacoes;
    }
}
