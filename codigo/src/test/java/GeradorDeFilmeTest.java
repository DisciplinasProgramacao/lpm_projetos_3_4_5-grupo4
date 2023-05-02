import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeradorDeFilmeTest {

    @Test
    void testeCarregamento() throws IOException {

        List<Filme> listaFilmes = GeradorDeFilme.gerarFilmes("Catalogo.txt");

        for (Filme filme : listaFilmes) {
            System.out.println(filme.toString());

        }
        assertEquals("", listaFilmes.get(0).toString());
        // assertEquals(10, listaFilmes.size());
    }
}