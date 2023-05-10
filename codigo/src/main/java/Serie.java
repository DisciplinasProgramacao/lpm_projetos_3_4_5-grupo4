import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

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

        public String geraDadosSerie() {

            String nome = this.getNome();
            int qtdeEpisodios = this.getQuantidadeEpisodios();
            int idSerie = gerarId();
            String dadosSerie = String.format("%d;%s;%d", idSerie, nome, qtdeEpisodios);

            return dadosSerie;
        }
    }
