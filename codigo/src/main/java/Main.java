import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IOException {
        List<Cliente> allClients = new ArrayList<>();
        Cliente c1 = new Cliente("fernando", "123");
        Cliente c2 = new Cliente("joao", "1234");
        Serie s = new Serie("serie 1", "genero 1", "pt", 10);
        Filme f = new Filme("filme 1", "genero 1", "pt", 10, new Date(), 123);
        Filme f2 = new Filme("filme 2", "genero 2", "en", 100, new Date(), 12345);
        c1.listaParaVer.add(s);
        c1.listaParaVer.add(f2);
        c1.listaJaVistas.add(f);
        allClients.add(c1);
        allClients.add(c2);

        Cliente.salvarTodosClientes(allClients);
        Filme.salvarTodosFilmes(List.of(f,f2));
        Serie.salvarTodasSeries(List.of(s));


        List<Cliente> clientesLoaded = Cliente.carregarTodosClientes();
        List<Filme> filmesLoaded = Filme.carregarTodosFilmes();
        List<Serie> seriesLoaded = Serie.carregarTodasSeries();
        System.out.println(clientesLoaded);
        System.out.println(filmesLoaded);
        System.out.println(seriesLoaded);

        PlataformaStreaming ps = new PlataformaStreaming("Netflix");
        filmesLoaded.forEach(ps::adicionarMidia);
        seriesLoaded.forEach(ps::adicionarMidia);

        Filme modelFilme = new Filme("filme", "Romance", "en", 100, new Date(), 124);
        System.out.println("filtrar -> idioma en: " + ps.filtrar(new CompareIdioma(), modelFilme));
        System.out.println("filtrar -> genero romance: " + ps.filtrar(new CompareGenero(), modelFilme));
        // FIXME erro de tipo
//        System.out.println("filtrar -> qtd eps 10: " + ps.filtrar(new CompareQtdEpisodios(), new Serie("serie", "Romance", "en", 10)));
    }
}
