import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerieTest {

    private Serie serie;
    @Test
    void testGerarItemsDoJson()  {
        serie = new Serie("a volta dos que não foram", "comédia", "pt-br", 3);
        serie.registrarAudiencia();
        assertEquals(1, serie.getAudiencia());
    }
}