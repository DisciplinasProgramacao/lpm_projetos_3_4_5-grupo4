import java.io.Serializable;
import java.util.*;
import static java.util.Objects.isNull;

public abstract class Media implements Serializable {
    private Generos GENERO;
    protected Integer id;
    protected String nome;
    protected String idioma;
    protected Date dataLancamento;
    protected Integer audiencia;
    protected List<Avaliacao> avaliacoes;

    private void init(String nome, String genero, String idioma, Date dataLancamento) {
        Optional<Generos> existeGenero = Arrays.stream(Generos.values()).filter(g -> g.name().equalsIgnoreCase(genero))
                .findFirst();
        if (existeGenero.isPresent()) {
            this.GENERO = existeGenero.get();
        } else {
            this.GENERO = Generos.randomGeneros();
        }
        this.nome = nome;
        this.idioma = idioma;
        this.dataLancamento = dataLancamento;
        audiencia = 0;
        avaliacoes = new ArrayList<>();
    }

    /**
     * Construtor da classe Media.
     *
     * @param nome   o nome da mídia
     * @param genero o gênero da mídia
     * @param idioma o idioma da mídia
     */
    public Media(Integer id, String nome, String genero, String idioma, Date dataLancamento) {
        init(nome, genero, idioma, dataLancamento);
        this.id = id;
    }

    /**
     * Construtor da classe Media.
     *
     * @param nome   o nome da mídia
     * @param genero o gênero da mídia
     * @param idioma o idioma da mídia
     */
    public Media(String nome, String genero, String idioma, Date dataLancamento) {
        init(nome, genero, idioma, dataLancamento);
        id = gerarId();
    }

    /**
     * Retorna o nome da mídia.
     *
     * @return o nome da mídia
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o gênero da mídia.
     *
     * @return o gênero da mídia
     */
    public String getGenero() {
        return GENERO.name();
    }

    /**
     * Retorna o idioma da mídia.
     *
     * @return o idioma da mídia
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Retorna a audiência da mídia.
     *
     * @return a audiência da mídia
     */
    public Integer getAudiencia() {
        return audiencia;
    }

    /**
     * Registra mais uma audiência para a mídia.
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    /**
     * Retorna a lista de avaliações da mídia.
     *
     * @return a lista de avaliações da mídia
     */
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    /**
     * Adiciona uma avaliação à mídia.
     *
     * @param avaliacao a avaliação a ser adicionada
     */
    public void addAvaliacao(Avaliacao avaliacao) {
        Avaliacao clienteJaAvaliou = avaliacoes.stream()
                .filter(a -> a.getCliente().getNomeUsuario().equals(avaliacao.getCliente().getNomeUsuario()))
                .findFirst().orElse(null);
        if (isNull(clienteJaAvaliou)) {
            this.avaliacoes.add(avaliacao);
        }
    }

    /**
     * Gera um ID aleatório para a mídia.
     *
     * @return o ID gerado
     */
    public int gerarId() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Calcula a média das avaliações.
     *
     * @return A média das notas das avaliações.
     */
    protected double mediaDeAvaliacoes() {
        double mediaDeAvaliacoes = avaliacoes.stream().mapToInt(avaliacao -> avaliacao.nota).average().orElse(0d);
        return mediaDeAvaliacoes;
    }
}
