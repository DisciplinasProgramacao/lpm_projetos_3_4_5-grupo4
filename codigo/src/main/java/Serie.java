import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Serie extends Media implements Serializable {
    private Integer quantidadeEpisodios;

    /**
     * Construtor da classe Serie.
     *
     * @param nome                o nome da série
     * @param genero              o gênero da série
     * @param idioma              o idioma da série
     * @param dataLancamento      a data de lançamento da série
     * @param quantidadeEpisodios a quantidade de episódios da série
     * @throws Error se a quantidade de episódios for menor que 2
     */
    public Serie(String nome, String genero, String idioma, Date dataLancamento, Integer quantidadeEpisodios, boolean lancamento) {
        super(nome, genero, idioma, dataLancamento, lancamento);
        if (quantidadeEpisodios < 2) {
            throw new Error("Precisa ter no mínimo 2 episódios");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * Construtor da classe Serie.
     *
     * @param id                  o id da série
     * @param nome                o nome da série
     * @param genero              o gênero da série
     * @param idioma              o idioma da série
     * @param dataLancamento      a data de lançamento da série
     * @param quantidadeEpisodios a quantidade de episódios da série
     * @throws Error se a quantidade de episódios for menor que 2
     */
    public Serie(Integer id, String nome, String genero, String idioma, Date dataLancamento, Integer quantidadeEpisodios) {
        super(id, nome, genero, idioma, dataLancamento);
        if (quantidadeEpisodios < 2) {
            throw new Error("Precisa ter no mínimo 2 episódios");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public Serie(String[] dadosLidos) {
        super(Integer.parseInt(dadosLidos[0].replaceAll("\\p{C}", "")), dadosLidos[1], "", "", new Date(dadosLidos[2]));
    }

    /**
     * Obtém a quantidade de episódios da série.
     *
     * @return a quantidade de episódios da série
     */
    public Integer getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    /**
     * Salva todas as séries em um arquivo.
     *
     * @param allSeries a lista de todas as séries a serem salvas
     * @throws IOException caso ocorra um erro durante o processo de salvamento
     */
    public static void salvarTodasSeries(List<Serie> allSeries) throws IOException {
        GenericDao<Serie> serieDao = new GenericDao<>();
        serieDao.save(allSeries, "data/series.dat");
    }

    /**
     * Carrega todas as séries de um arquivo.
     *
     * @return a lista de todas as séries carregadas do arquivo
     * @throws IOException            caso ocorra um erro durante o processo de
     *                                carregamento
     * @throws ClassNotFoundException caso a classe da série não seja encontrada
     *                                durante o processo de carregamento
     */
    public static List<Serie> carregarTodasSeries() throws IOException, ClassNotFoundException {
        GenericDao<Serie> serieDao = new GenericDao<>();
        return serieDao.load("data/series.dat");
    }

    /**
     * Gera os dados da série.
     *
     * @return uma string com os dados da série no formato
     *         "idSerie;nome;quantidadeEpisodios"
     */
    public String geraDadosSerie() {

        String nome = this.getNome();
        int qtdeEpisodios = this.getQuantidadeEpisodios();
        int idSerie = gerarId();
        String dadosSerie = String.format("%d;%s;%d", idSerie, nome, qtdeEpisodios);

        return dadosSerie;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "quantidadeEpisodios=" + quantidadeEpisodios +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + this.getGenero() + '\'' +
                ", idioma='" + idioma + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", audiencia=" + audiencia +
                '}';
    }
}
