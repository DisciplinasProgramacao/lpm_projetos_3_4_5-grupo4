import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GeradorDeFilmeTest {

    @Test
    void testeCarregamento() throws IOException {

        List<Filme> listaFilmes = GeradorDeFilme.gerarFilmes("Catalogo.txt");

        assertTrue(listaFilmes.toArray().length > 0);
    }
}