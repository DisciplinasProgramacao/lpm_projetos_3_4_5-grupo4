import java.util.Arrays;
import java.util.Optional;

public abstract class Media {
    private static String[] GENEROS = { "Ação", "Comédia", "ROMANCE" };
    private String nome;
    private String genero;
    private String idioma;
    private Integer audiencia = 0;

    public Media(String nome, String genero, String idioma) {
        Optional<String> existeGenero = Arrays.stream(GENEROS).filter(g -> g.equals(genero)).findFirst();
        if (existeGenero.isPresent()) {
            this.genero = genero;
        } else {
            this.genero = "";
        }
        this.nome = nome;
        this.idioma = idioma;
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
}
