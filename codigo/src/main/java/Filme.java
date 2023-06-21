import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Filme extends Media implements Serializable {

    private Integer duracao;

    /**
     * Construtor que recebe todos os atributos do filme.
     *
     * @param id             O ID do filme.
     * @param nome           O nome do filme.
     * @param genero         O gênero do filme.
     * @param idioma         O idioma do filme.
     * @param duracao        A duração do filme em minutos.
     * @param dataLancamento A data de lançamento do filme.
     */
    public Filme(Integer id, String nome, String genero, String idioma, Integer duracao, Date dataLancamento) {
        super(id, nome, genero, idioma, dataLancamento);
        this.duracao = duracao;
    }

    /**
     * Construtor que recebe todos os atributos do filme, exceto o ID.
     *
     * @param nome           O nome do filme.
     * @param genero         O gênero do filme.
     * @param idioma         O idioma do filme.
     * @param duracao        A duração do filme em minutos.
     * @param dataLancamento A data de lançamento do filme.
     */
    public Filme(String nome, String genero, String idioma, Integer duracao, Date dataLancamento, boolean lancamento) {
        super(nome, genero, idioma, dataLancamento, lancamento);
        this.duracao = duracao;
    }

    /**
     * Construtor que recebe um array de strings contendo os dados do filme.
     *
     * @param dadosLidos O array de strings contendo os dados do filme.
     */
    public Filme(String[] dadosLidos) {
        super(Integer.parseInt(dadosLidos[0]), dadosLidos[1], "", "", new Date(dadosLidos[2]));
        this.duracao = Integer.parseInt(dadosLidos[3]);
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Retorna a duração do filme em segundos.
     *
     * @return A duração do filme em segundos.
     */
    public Integer getDuracaoSegundos() {
        return duracao * 60;
    }

    public String StringSalvar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return this.id + ";" + this.getNome() + ";" + sdf.format(this.dataLancamento) + ";" + this.duracao;
    }

    /**
     * Salva todos os filmes em uma lista em um arquivo.
     *
     * @param allFilmes A lista de filmes a serem salvos.
     * @throws IOException Se ocorrer um erro de IO ao salvar o arquivo.
     */
    public static void salvarTodosFilmes(List<Filme> allFilmes) throws IOException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        filmeDao.save(allFilmes, "data/filmes.dat");
    }

    /**
     * Carrega todos os filmes de um arquivo.
     *
     * @return Uma lista contendo todos os filmes carregados do arquivo.
     * @throws IOException            Se ocorrer um erro de IO ao carregar o
     *                                arquivo.
     * @throws ClassNotFoundException Se a classe do objeto carregado não for
     *                                encontrada.
     */
    public static List<Filme> carregarTodosFilmes() throws IOException, ClassNotFoundException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        return filmeDao.load("data/filmes.dat");
    }

    @Override
    public String toString() {
        return "Filme{" +
                "duracao=" + duracao +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + this.getGenero() + '\'' +
                ", idioma='" + idioma + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", audiencia=" + audiencia +
                '}';
    }
}
