import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Media implements Serializable {
    private static String[] GENEROS = { "Ação", "Comédia", "ROMANCE" };
    private String nome;
    private String genero;
    private String idioma;
    private Integer audiencia;
    private List<Integer> avaliacoes;

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
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getAudiencia() {
        return audiencia;
    }

    public void registrarAudiencia() {
        this.audiencia++;
    }

    @Override
    public String toString() {
        return "Media{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", idioma='" + idioma + '\'' +
                ", audiencia=" + audiencia +
                '}';
    }

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }

    public void addAvaliacao(Integer avaliacoes) {
        this.avaliacoes.add(avaliacoes);
    }

    public int gerarId() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }
}
