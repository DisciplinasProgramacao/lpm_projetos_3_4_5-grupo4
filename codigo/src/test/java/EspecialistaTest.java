import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EspecialistaTest {
    static List<Filme> filmes;
    static Cliente cliente;


    @BeforeAll
    static void init() throws IOException{
        filmes = GeradorDeMedia.gerarFilmes("data/Filmes.csv");
        cliente = new Cliente("User", "Senha");
    }
    
    
    /**
     * Teste para ver se ele eh um cliente valido ou não. True tendo mais de 5 avaliações, false para menos
     * Não validando com data de filme expirado
     * 
     */
    @Test
    public void testeIsEspecialista() throws IOException{
        cliente.registrarAudiencia(filmes.get(0));
        cliente.registrarAudiencia(filmes.get(1));
        cliente.registrarAudiencia(filmes.get(2));
        cliente.avaliar("Heroes And Captains", 5);
        cliente.avaliar("Complexity Of A Nuclear Winter", 5);
        cliente.avaliar("Creatures And Athletes", 5);
        
        assertEquals(false, cliente.isClienteEspecialista());
        
        cliente.registrarAudiencia(filmes.get(3));
        cliente.registrarAudiencia(filmes.get(4));
        cliente.registrarAudiencia(filmes.get(5));
        cliente.avaliar("Cultured By The New World", 5);
        cliente.avaliar("Fools Of The East", 5);
        cliente.avaliar("Music Of The Lakes", 5);
        assertEquals(true, cliente.isClienteEspecialista());
        
    }
}
