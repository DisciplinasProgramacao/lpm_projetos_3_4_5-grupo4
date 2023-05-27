import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GeradorDeFilmeTest {

    /**
     * Teste de carregamento do arquivo data/Filmes.csv
     * Verificação realizada a partir de a ListaFilmes ser maior q 0
     * @throws IOException
     */

    @Test
    void testeCarregamento() throws IOException {

        List<Filme> listaFilmes = GeradorDeFilme.gerarFilmes("data/Filmes.csv");

        assertTrue(listaFilmes.toArray().length > 0);
    }
}