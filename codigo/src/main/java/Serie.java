import java.util.Arrays;
import java.util.Optional;

public class Serie {
    private static String[] GENEROS = {"Ação", "Comédia", "ROMANCE"};
    private String nome;
    private String genero;
    private String idioma;
    private Integer quantidadeEpisodios;
    private Integer audiencia = 0;

    public Serie(String nome, String genero, String idioma, Integer quantidadeEpisodios) {
        if (quantidadeEpisodios < 2) {
            throw new Error("Precisa ter no minimo 2 episodios");
        }
        Optional<String> existeGenero = Arrays.stream(GENEROS).filter(g -> g.equals(genero)).findFirst();
        if (existeGenero.isPresent()) {
            this.genero = genero;
        } else {
            this.genero = "";
        }
        this.nome = nome;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;

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

    public Integer getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public Integer getAudiencia() {
        return audiencia;
    }

    public void registrarAudiencia() {
        this.audiencia++;
    }
}
