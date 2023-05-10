import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SalvarFilmeTest {
    @Test
    void TesteSalvamento() throws IOException {
        List<Filme> listaFilmes = GeradorDeFilme.gerarFilmes("Catalogo.txt");
        ArrayList<String> filmesEmString = new ArrayList<String>();
        for (Filme filme : listaFilmes) {
            filmesEmString.add(filme.StringSalvar());
        }
        SalvarFilme.salvarTxt("CatalogoNovo.txt", filmesEmString);

    }
}
