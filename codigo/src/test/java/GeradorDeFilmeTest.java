import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GeradorDeFilmeTest {

    /**
     * Teste de carregamento do arquivo Catalogo.txt
     * Verificação realizada a partir de a ListaFilmes ser maior q 0
     * @throws IOException
     */

    @Test
    void testeCarregamento() throws IOException {

        List<Filme> listaFilmes = GeradorDeFilme.gerarFilmes("Catalogo.txt");

        assertTrue(listaFilmes.toArray().length > 0);
    }
}