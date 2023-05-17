import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IOException {
        List<Cliente> allClients = new ArrayList<>();
        Cliente c1 = new Cliente("fernando", "123");
        Cliente c2 = new Cliente("joao", "1234");
        Serie s = new Serie("serie 1", "genero 1", "pt", 10);
        Filme f = new Filme("filme 1", "genero 1", "pt", 10, new Date());
        Filme f2 = new Filme("filme 2", "genero 2", "en", 100, new Date());
        c1.listaParaVer.add(s);
        c1.listaParaVer.add(f2);
        c1.listaJaVistas.add(new ItemListaJaVista(f));
        allClients.add(c1);
        allClients.add(c2);

        Cliente.salvarTodosClientes(allClients);
        Filme.salvarTodosFilmes(List.of(f,f2));
        Serie.salvarTodasSeries(List.of(s));

        Scanner sc = new Scanner(System.in);
        System.out.println("Seja bem-vindo ao cadastro de Filmes!");
        System.out.println("Digite o Nome do filme:");
        String nomeFilme = sc.next();
        System.out.println("Digite a data de lancamento do filme:");
        String dataLancamentoFilme = sc.next();
        System.out.println("Digite a duracao do filme:");
        Integer duracaoFilme = sc.nextInt();
        System.out.println("Digite o genero do filme:");
        String generoFilme = sc.next();
        System.out.println("Digite o idioma do filme:");
        String idiomaFilme = sc.next();

        Filme fTesteRetorno = new Filme(nomeFilme, generoFilme, idiomaFilme, duracaoFilme, new Date());

        System.out.println(fTesteRetorno.geraDadosFilme());

        List<Cliente> clientesLoaded = Cliente.carregarTodosClientes();
        List<Filme> filmesLoaded = Filme.carregarTodosFilmes();
        List<Serie> seriesLoaded = Serie.carregarTodasSeries();
        System.out.println(clientesLoaded);
        System.out.println(filmesLoaded);
        System.out.println(seriesLoaded);

        PlataformaStreaming ps = new PlataformaStreaming("Netflix");
        filmesLoaded.forEach(ps::adicionarMidia);
        seriesLoaded.forEach(ps::adicionarMidia);

        Filme modelFilme = new Filme("filme", "Romance", "en", 100, new Date());
        System.out.println("filtrar -> idioma en: " + ps.filtrar(new CompareIdioma(), modelFilme));
        System.out.println("filtrar -> genero romance: " + ps.filtrar(new CompareGenero(), modelFilme));
        // FIXME erro de tipo
//        System.out.println("filtrar -> qtd eps 10: " + ps.filtrar(new CompareQtdEpisodios(), new Serie("serie", "Romance", "en", 10)));

        // Interface para usuário digitar atributos da série
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da série: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o gênero da série: ");
        String genero = scanner.nextLine();

        System.out.print("Digite o idioma da série: ");
        String idioma = scanner.nextLine();

        System.out.print("Digite a quantidade de episódios da série: ");
        Integer quantidadeEpisodios = scanner.nextInt();

        Serie novaSerie = new Serie(nome, genero, idioma, quantidadeEpisodios);

    }
}
