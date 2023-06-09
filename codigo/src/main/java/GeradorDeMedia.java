import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Paths;

public class GeradorDeMedia {
	private static String arquivo;

	/**
	 * Construtor da classe GeradorDeFilme.
	 *
	 * @param enderecoArquivo o caminho do arquivo de texto contendo informações sobre os filmes.
	 */
	public GeradorDeMedia(String enderecoArquivo) {
		GeradorDeMedia.arquivo = enderecoArquivo;
	}

	/**
	 * Gera uma lista de filmes a partir do arquivo especificado no construtor.
	 *
	 * @return uma lista de objetos da classe Filme.
	 * @throws IOException se ocorrer algum erro na leitura do arquivo.
	 */
	public static List<Filme> gerarFilmes() throws IOException {
		List<Filme> listaFilmes = new ArrayList<Filme>();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(arquivo));

			for (String line : allLines) {
				listaFilmes.add(new Filme(line.split(";")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaFilmes;
	}

	/**
	 * Gera uma lista de filmes a partir do arquivo especificado.
	 *
	 * @param enderecoArquivo o caminho do arquivo de texto contendo informações sobre os filmes.
	 * @return uma lista de objetos da classe Filme.
	 * @throws IOException se ocorrer algum erro na leitura do arquivo.
	 */
	public static List<Filme> gerarFilmes(String enderecoArquivo) throws IOException {
		List<Filme> listaFilmes = new ArrayList<>();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(enderecoArquivo));

			for (String line : allLines) {
				listaFilmes.add(new Filme(line.split(";")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaFilmes;
	}

	/**
	 * Gera uma lista de filmes a partir do arquivo especificado.
	 *
	 * @param enderecoArquivo o caminho do arquivo de texto contendo informações sobre os filmes.
	 * @return uma lista de objetos da classe Filme.
	 * @throws IOException se ocorrer algum erro na leitura do arquivo.
	 */
	public static List<Serie> gerarSeries(String enderecoArquivo) throws IOException {
		List<Serie> series = new ArrayList<>();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(enderecoArquivo));

			for (String line : allLines) {
				series.add(new Serie(line.split(";")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return series;
	}
}
