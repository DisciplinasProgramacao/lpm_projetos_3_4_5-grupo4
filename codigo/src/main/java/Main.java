import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Objects.isNull;

public class Main {

    private static Scanner sc;

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        sc = new Scanner(System.in);
        PlataformaStreaming ps = new PlataformaStreaming("Metflix");

        System.out.println("\nBem-vindo(a) à Metflix");
        printDivider();

        Cliente cliente = handleLogin(ps);
        System.out.println();

        int op;

        while (true) {
            printDivider();
            System.out.println("Olá, " + cliente.getNomeUsuario());
            printDivider();
            System.out.println("Escolha uma operação:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Filtrar");
            System.out.println("3 - Assistir");
            System.out.println("4 - Adicionar a sua Lista para Ver");
            System.out.println("5 - Avaliar");
            System.out.println("6 - Avaliar e Comentar");
            System.out.println("7 - Logoff");
            System.out.println("0 - Sair da Metflix");
            printDivider();

            System.out.print("Operação: ");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 0) break;
            switch (op) {
                case 1:
                    Media media = handleCadastrar();
                    if (!isNull(media)) {
                        ps.adicionarMidia(media);
                        System.out.println("Mídia cadastrada com sucesso");
                    }
                    break;
                case 2:
                    List<Media> list = handleFiltrar(ps);
                    System.out.println("Mídias filtradas:");
                    list.forEach(System.out::println);
                    break;
                case 3:
                    handleAssistir(ps, cliente);
                    break;
                case 4:
                    handleWatchlist();
                    break;
                case 5:
                    handleAvaliar();
                    break;
                case 6:
                    handleAvaliarComentar();
                    break;
                case 7:
                    ps.logoff();
                    main(args);
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente\n");
            }

        }

        sc.close();
    }

    private static Cliente handleLogin(PlataformaStreaming ps) {
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Login");
        System.out.println("2 - Novo usuário");
        System.out.print("Opção: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                System.out.println("Login");
                System.out.print("Usuário: ");
                String username = sc.nextLine();
                System.out.print("Senha: ");
                String password = sc.nextLine();

                Cliente cliente = ps.login(username, password);
                if (isNull(cliente)) {
                    System.out.println("Usuário ou senha inválidos. Tente novamente");
                    return handleLogin(ps);
                }

                return cliente;
            case 2:
                while (true) {
                    System.out.println("Novo usuário");
                    System.out.print("Usuário: ");
                    String newUsername = sc.nextLine();
                    System.out.print("Senha: ");
                    String newPassword = sc.nextLine();

                    Cliente newCliente = new Cliente(newUsername, newPassword);
                    if (ps.adicionarCliente(newCliente)) return newCliente;
                    System.out.println("Esse usuário já existe\n");
                }
            default:
                System.out.println("Opção inválida\n");
                return handleLogin(ps);
        }
    }

    private static Media handleCadastrar() {
        int op = handleFilmeOuSerie("Cadastrar ");
        if (op == 1) {
            System.out.println("Informe os dados do filme:");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Gênero: ");
            String genero = sc.nextLine();
            System.out.print("Idioma: ");
            String idioma = sc.nextLine();
            System.out.print("Duração (min): ");
            int duracao = sc.nextInt();
            sc.nextLine();
            Date date;
            while (true) {
                System.out.print("Data Lançamento (dd/mm/aaaa): ");
                String dateStr = sc.nextLine();
                System.out.println("str: " + dateStr);
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
                    break;
                } catch (ParseException e) {
                    System.out.println("Formato de data inválido. Tente novamente");
                }
            }

            return new Filme(nome, genero, idioma, duracao, date);
        } else if (op == 2) {
            System.out.println("Informe os dados da série:");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Gênero: ");
            String genero = sc.nextLine();
            System.out.print("Idioma: ");
            String idioma = sc.nextLine();
            System.out.print("Quantos episódios? ");
            Serie serie;
            while (true) {
                try {
                    int episiodios = sc.nextInt();
                    sc.nextLine();
                    serie = new Serie(nome, genero, idioma, episiodios);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            return serie;
        }

        return null;
    }

    private static List<Media> handleFiltrar(PlataformaStreaming ps) {
        System.out.println("Como deseja filtrar as mídias?");
        System.out.println("1 - Filtrar por gênero");
        System.out.println("2 - Filtrar por idioma");
        System.out.println("3 - Filtrar por número de episódios (apenas séries)");
        System.out.print("\nOpção: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                System.out.print("Qual gênero deseja filtrar? ");
                String genero = sc.nextLine();
                return ps.filtrarPorGenero(new CompareGenero(), new Serie("model", genero, "en", 5));
            case 2:
                System.out.print("Qual idioma deseja filtrar? ");
                String idioma = sc.nextLine();
                return ps.filtrarPorIdioma(new CompareIdioma(), new Serie("model", "Romance", idioma, 5));
            case 3:
                System.out.print("Filtrar séries com quantos episódios? ");
                int eps = sc.nextInt();
                sc.nextLine();
                return ps.filtrarPorQtdEpisodios(new CompareQtdEpisodios(), new Serie("model", "Romance", "en", eps));
            default:
                System.out.println("Opção inválida. Tente novamente");
                return handleFiltrar(ps);
        }
    }

    private static void handleAssistir(PlataformaStreaming ps, Cliente cliente) {
        System.out.print("Escreva o nome da mídia que deseja assistir: ");
        String nome = sc.nextLine();
        Media media = ps.buscarMidia(nome);

        if (!isNull(media)) ps.registrarAudiencia(nome);
        else System.out.println("Mídia não encontrada. Tente novamente\n");
    }

    private static void handleWatchlist() {}

    private static void handleAvaliar() {}

    private static void handleAvaliarComentar() {}

    private static int handleFilmeOuSerie(String prefix) {
        System.out.println(prefix + "Filme ou Série?");
        System.out.println("1 - Filme");
        System.out.println("2 - Série");
        System.out.print("Opção: ");
        int op = sc.nextInt();
        sc.nextLine();
        if (op == 1 || op == 2) return op;

        return handleFilmeOuSerie(prefix);
    }

    private static void printDivider() {
        System.out.println("------------------------------");
    }
}
