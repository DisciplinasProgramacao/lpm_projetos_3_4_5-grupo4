import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Serie extends Media implements Serializable {
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

    public static void salvarTodasSeries(List<Serie> allSeries) throws IOException {
        GenericDao<Serie> serieDao = new GenericDao<>();
        serieDao.save(allSeries, "series.dat");
    }

    public static List<Serie> carregarTodasSeries() throws IOException, ClassNotFoundException {
        GenericDao<Serie> serieDao = new GenericDao<>();
        return serieDao.load("series.dat");
    }
}
