import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class Media implements Serializable {
    private static final String[] GENEROS = { "Ação", "Comédia", "Romance" };
    protected String nome;
    protected String genero;
    protected String idioma;
    protected Integer audiencia;
    protected List<Avaliacao> avaliacoes;

    private Integer id;

    /**
     * Construtor da classe Media.
     *
     * @param nome    o nome da mídia
     * @param genero  o gênero da mídia
     * @param idioma  o idioma da mídia
     */
    public Media(String nome, String genero, String idioma) {
        Optional<String> existeGenero = Arrays.stream(GENEROS).filter(g -> g.equals(genero)).findFirst();
        if (existeGenero.isPresent()) {
            this.genero = genero;
        } else {
            this.genero = "";
        }
        this.nome = nome;
        this.idioma = idioma;
        audiencia = 0;
        avaliacoes = new ArrayList<>();
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
        return genero;
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
     * Retorna uma representação em formato de string da mídia.
     *
     * @return a representação em formato de string da mídia
     */
    @Override
    public String toString() {
        return "Media{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", idioma='" + idioma + '\'' +
                ", audiencia=" + audiencia +
                '}';
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
        Avaliacao clienteJaAvaliou = avaliacoes.stream().filter(a -> a.getCliente().getNomeUsuario().equals(avaliacao.getCliente().getNomeUsuario())).findFirst().orElse(null);
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

    protected double mediaDeAvaliacoes(){
        double mediaDeAvaliacoes = avaliacoes.stream().mapToInt(avaliacao -> avaliacao.nota).average().orElse(0d);
        return mediaDeAvaliacoes;
    }
}
