package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeradorDeFilmeTest {

    @Test
    void testeCarregamento() {
        ArrayList<Filme> listaFilmes = GeradorDeFilme.gerarFilmes("../Catalogo.txt");
        for (Filme filme : listaFilmes) {
            System.out.println(filme.toString());
        }
        assertEquals("", "");
    }
}
