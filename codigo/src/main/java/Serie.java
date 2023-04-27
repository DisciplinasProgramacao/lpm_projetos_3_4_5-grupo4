public class Serie extends Media {
    private Integer quantidadeEpisodios;

    public Serie(String nome, String genero, String idioma, Integer quantidadeEpisodios) {
        super(nome, genero, idioma);
        if (quantidadeEpisodios < 2) {
            throw new Error("Precisa ter no minimo 2 episodios");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public Integer getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }
}
