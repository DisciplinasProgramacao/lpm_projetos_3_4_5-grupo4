import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

public class GeradorDeFilme {
	private static String arquivo;

	public GeradorDeFilme(String enderecoArquivo) {
		GeradorDeFilme.arquivo = enderecoArquivo;
	}

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

	public static List<Filme> gerarFilmes(String enderecoArquivo) throws IOException {
		List<Filme> listaFilmes = new ArrayList<Filme>();
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
}
