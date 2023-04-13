public class Serie {
    private static String[] GENEROS = {"GENERO1", "GENERO2"};
    private String nome;
    private String genero;
    private String idioma;
    private Integer quantidadeEpisodios;
    private Integer audiencia;

    public Serie(String nome, String genero, String idioma, Integer quantidadeEpisodios) {
        this.nome = nome;
        this.genero = genero;
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
