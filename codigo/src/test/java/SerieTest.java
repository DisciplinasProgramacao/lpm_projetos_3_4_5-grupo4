import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SerieTest {

    private Serie serie;
    @Test
    void testVerificaAudiencia()  {
        serie = new Serie("a volta dos que não foram", "comédia", "pt-br", 3);
        serie.registrarAudiencia();
        assertEquals(1, serie.getAudiencia());
    }

    @Test
    void testInstanciaSerie(){
        assertThrows(Error.class, () -> new Serie("a volta dos que não foram", "comédia", "pt-br", 1));
    }
}