import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Objects.isNull;

public class Main {

    private static Scanner sc;

    public static void main(String[] args) throws IOException, ClassNotFoundException, IOException {
        PlataformaStreaming ps = new PlataformaStreaming("Metflix");

        sc = new Scanner(System.in);
        int op;

        while (true) {
            System.out.println("\nEscolha uma operação:");
            printDivider();
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Filtrar");
            System.out.println("4 - Assistir");
            System.out.println("5 - Adicionar a sua Lista para Ver");
            System.out.println("6 - Avaliar");
            System.out.println("7 - Avaliar e Comentar");
            System.out.println("0 - Sair");
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
                    handleListar();
                    break;
                case 3:
                    handleFiltrar();
                    break;
                case 4:
                    handleAssistir();
                    break;
                case 5:
                    handleWatchlist();
                    break;
                case 6:
                    handleAvaliar();
                    break;
                case 7:
                    handleAvaliarComentar();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente\n");
            }

        }

        sc.close();
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

    private static void handleListar() {}

    private static void handleFiltrar() {}

    private static void handleAssistir() {}

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
