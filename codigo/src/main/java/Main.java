import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static java.util.Objects.isNull;

public class Main {

    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        PlataformaStreaming ps = new PlataformaStreaming("Xulambs Video");

        System.out.println("\nBem-vindo(a) à " + ps.getNome());
        printDivider();

        Cliente cliente = handleLogin(ps);
        System.out.println();

        int op;

        while (true) {
            System.out.println();
            printDivider();
            System.out.println("Olá, " + cliente.getNomeUsuario());
            printDivider();
            System.out.println("Escolha uma operação:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Filtrar");
            System.out.println("3 - Assistir");
            System.out.println("4 - Gerenciar sua Lista para Ver");
            System.out.println("5 - Avaliar");
            System.out.println("6 - Avaliar e Comentar");
            System.out.println("7 - Relatórios");
            System.out.println("8 - Logoff");
            System.out.println("0 - Sair da " + ps.getNome());
            printDivider();

            System.out.print("Operação: ");
            try {
                op = sc.nextInt();
            } catch (InputMismatchException e) {
                op = -1;
            }
            sc.nextLine();
            System.out.println();

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
                    handleAssistir(ps);
                    break;
                case 4:
                    handleWatchlist(cliente, ps);
                    break;
                case 5:
                    handleAvaliar(cliente);
                    break;
                case 6:
                    handleAvaliarComentar(cliente);
                    break;
                case 7:
                    handleRelatorios(ps);
                    break;
                case 8:
                    ps.logoff();
                    main(args);
                    sc.close();
                    return;
                case 0:
                    ps.logoff();
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente\n");
            }

        }
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
                if (!cliente.podeLancamento()) {
                    if (!cliente.tornarEspecialista()) cliente.tornarPadrao();
                }

                return cliente;
            case 2:
                while (true) {
                    System.out.println("Novo usuário");
                    System.out.print("Usuário: ");
                    String newUsername = sc.nextLine();
                    System.out.print("Senha: ");
                    String newPassword = sc.nextLine();
                    System.out.print("Profissional? (s/n): ");
                    boolean pro = handleYesOrNo();

                    PermissoesCliente pc;
                    if (pro) pc = new ClienteProfissional();
                    else pc = new ClientePadrao();

                    Cliente newCliente = new Cliente(newUsername, newPassword, pc);
                    if (ps.adicionarCliente(newCliente)) {
                        ps.login(newUsername, newPassword);
                        return newCliente;
                    }
                    System.out.println("Esse usuário já existe\n");
                }
            case 0:

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
            System.out.print("É lançamento? (s/n): ");
            boolean lancamento = handleYesOrNo();

            return new Filme(nome, genero, idioma, duracao, date, lancamento);
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

                    System.out.print("É lançamento? (s/n): ");
                    boolean lancamento = handleYesOrNo();

                    serie = new Serie(nome, genero, idioma, new Date(), episiodios, lancamento);
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
                return ps.filtrarPorGenero(new CompareGenero(), new Serie("model", genero, "en", new Date(), 5, false));
            case 2:
                System.out.print("Qual idioma deseja filtrar? ");
                String idioma = sc.nextLine();
                return ps.filtrarPorIdioma(new CompareIdioma(), new Serie("model", "Romance", idioma, new Date(), 5, false));
            case 3:
                System.out.print("Filtrar séries com quantos episódios? ");
                int eps = sc.nextInt();
                sc.nextLine();
                return ps.filtrarPorQtdEpisodios(new CompareQtdEpisodios(), new Serie("model", "Romance", "en", new Date(), eps, false));
            default:
                System.out.println("Opção inválida. Tente novamente");
                return handleFiltrar(ps);
        }
    }

    private static void handleAssistir(PlataformaStreaming ps) {
        System.out.print("Escreva o nome da mídia que deseja assistir: ");
        String nome = sc.nextLine();
        Media media = ps.buscarMidia(nome);

        if (!isNull(media)) {
            if (ps.registrarAudiencia(nome)) {
                System.out.println("Audiência registrada");
                if (ps.getClienteAtual().tornarEspecialista())
                    System.out.println("Parabéns! Você se tornou um Especialista. " +
                        "Agora você pode adicionar comentários às suas avaliações");
            }
            else System.out.println("Você já assistiu a essa mídia");
        }
        else {
            System.out.println("Mídia não encontrada. Tente novamente\n");
            handleAssistir(ps);
        }
    }

    private static void handleWatchlist(Cliente cliente, PlataformaStreaming ps) {
        System.out.println("Sua Lista para Ver:");
        cliente.getListaParaVer().forEach(System.out::println);
        System.out.println("\nO que você quer fazer?");
        System.out.println("1 - Adicionar a Lista");
        System.out.println("2 - Remover da Lista");
        System.out.println("0 - Voltar");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                while (true) {
                    System.out.print("Nome da mídia: ");
                    String nome = sc.nextLine();
                    Media media = ps.buscarMidia(nome);
                    if (!isNull(media)) {
                        if (cliente.adicionarNaLista(media)) {
                            System.out.println("Mídia adicionada a lista");
                            break;
                        }
                        System.out.println("Essa mídia já está na lista");
                    } else System.out.println("Mídia não encontrada. Tente novamente");
                }
                break;
            case 2:
                System.out.print("Nome da mídia: ");
                String nome = sc.nextLine();
                if (cliente.retirarDaLista(nome)) {
                    System.out.println("Mídia removida da lista");
                    break;
                }
                System.out.println("Mídia não encontrada. Tente novamente");
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente");
                handleWatchlist(cliente, ps);
        }
    }

    private static void handleAvaliar(Cliente cliente) {
        System.out.println("Avaliar mídia");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Nota: ");
        int nota = sc.nextInt();
        sc.nextLine();

        if (cliente.avaliar(nome, nota)) {
            System.out.println("Avaliação registrada");
            return;
        }

        System.out.println("Falha ao registrar. Certifique-se que a mídia existe e que você já a assistiu");
        System.out.print("Tentar novamente? (s/n): ");
        boolean tryAgain = handleYesOrNo();
        if (tryAgain) handleAvaliar(cliente);

    }

    private static void handleAvaliarComentar(Cliente cliente) {
        if (!cliente.podeComentar()) {
            System.out.println("Você não pode fazer avaliações com comentários");
            return;
        }
        System.out.println("Avaliar com comentário");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Nota: ");
        int nota = sc.nextInt();
        sc.nextLine();
        System.out.print("Comentário: ");
        String comentario = sc.nextLine();

        if (cliente.avaliarComComentario(nome, nota, comentario))
            System.out.println("Avaliação registrada");
        else {
            System.out.println("Você não assistiu a essa mídia. Tente novamente");
            handleAvaliarComentar(cliente);
        }
    }

    private static void handleRelatorios(PlataformaStreaming ps) {
        while (true) {
            System.out.println("Relatórios disponíveis:");
            System.out.println("1 - Cliente com mais mídias");
            System.out.println("2 - Cliente que mais avaliou");
            System.out.println("3 - Porcentagem de clientes com mais de 15 avaliações");
            System.out.println("4 - Top 10 mídias mais avaliadas com mínino de 100 avaliações");
            System.out.println("5 - Top 10 mídias mais avaliadas com mínino de 100 avaliações por gênero");
            System.out.println("6 - Top 10 mídias com mais visualizações");
            System.out.println("7 - Top 10 mídias mais visualizações por gênero");
            System.out.println("0 - Voltar");

            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 0) return;

            switch (op) {
                case 1:
                    System.out.println();
                    System.out.println(Relatorio.clienteComMaisMidias(ps));
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    System.out.println(Relatorio.clienteQueMaisAvaliou(ps));
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println(Relatorio.porcentagemClientesMaisDe15Avaliacoes(ps));
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println(Relatorio.dezMidiasMaisAvaliadasComMininoDe100Avaliacoes(ps));
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Por qual gênero deseja filtrar? ");
                    String g = sc.nextLine();
                    System.out.println();
                    System.out.println(Relatorio.dezMidiasMaisAvaliadasComMininoDe100AvaliacoesPorGenero(ps, g));
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    System.out.println(Relatorio.dezMidiasComMaisVisualizacoes(ps));
                    System.out.println();
                    break;
                case 7:
                    System.out.print("Por qual gênero deseja filtrar? ");
                    String g1 = sc.nextLine();
                    System.out.println();
                    System.out.println(Relatorio.dezMidiasMaisVisualizacoesPorGenero(ps, g1));
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    handleRelatorios(ps);
            }
        }
    }

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

    private static boolean handleYesOrNo() {
        String res = sc.nextLine();
        if (res.startsWith("s") || res.startsWith("y")) return true;
        if (res.startsWith("n")) return false;

        System.out.print("(s/n): ");

        return handleYesOrNo();
    }

    private static void printDivider() {
        System.out.println("------------------------------");
    }
}
