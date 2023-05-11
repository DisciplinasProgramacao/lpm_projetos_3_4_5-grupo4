import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Filme extends Media implements Serializable {

    private Integer duracao, codigo;
    private Date dataLancamento;
    private Integer id;

    public Filme(String nome, String genero, String idioma, Integer duracao, Date dataLancamento, Integer id) {
        super(nome, genero, idioma);
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.id = id;
    }

    public Filme(String[] dadosLidos) {
        super(dadosLidos[1], "", "");
        this.dataLancamento = new Date(dadosLidos[2]);
        this.duracao = Integer.parseInt(dadosLidos[3]);
        this.codigo = Integer.parseInt(dadosLidos[0]);
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Obtém a duração do filme em segundos.
     *
     * @return A duração do filme em segundos.
     */
    public Integer getDuracaoSegundos() {
        return (duracao * 60);
    }

    /**
     * Realiza o cadastro de um filme a partir de entradas do usuário.
     *
     * @return Uma string contendo as informações do filme cadastrado.
     */
    public String cadastraFilmeClientSide(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Seja bem-vindo ao cadastro de Filmes!");
        System.out.println("Digite o Id do filme:");
        Integer idFilme = sc.nextInt();
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
        sc.close();

        String stringSalvaInformacoes = idFilme + ";" + nomeFilme + ";" + dataLancamentoFilme + ";" + duracaoFilme + ";" + generoFilme + ";" + idiomaFilme; 
        
        return stringSalvaInformacoes;
    }

    /**
     * Gera os dados do filme formatados em uma string.
     *
     * @return Uma string contendo os dados do filme no formato "id;nome;dataLancamento;duração;genero;idioma".
     */
    public String geraDadosFilme(){
        int idFilme = gerarId();
        String nomeFilme = getNome();
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String dataLancamentoFilme = sf.format(getDataLancamento());
        Integer duracaoFilme = getDuracao();
        String generoFilme = getGenero();
        String idiomaFilme = getIdioma();

        String dadosFilme = String.format("%d;%s;%s;%d;%s;%s", idFilme,  nomeFilme, dataLancamentoFilme, duracaoFilme, generoFilme, idiomaFilme);

        return dadosFilme;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nFilme{" +
                "duracao=" + duracao +
                ", dataLancamento=" + dataLancamento +
                '}';
    }

    /**
     * Retorna uma string contendo os dados do filme formatados para salvar.
     *
     * @return Uma string contendo os dados do filme no formato "codigo;nome;dataLancamento;duração".
     */
    public String StringSalvar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return this.codigo + ";" + this.getNome() + ";" + sdf.format(this.dataLancamento) + ";" + this.duracao;
    }

    /**
     * Salva todos os filmes em uma lista para um arquivo.
     *
     * @param allFilmes  A lista de filmes a serem salvos.
     * @throws IOException  Se ocorrer um erro de E/S durante o processo de salvamento.
     */
    public static void salvarTodosFilmes(List<Filme> allFilmes) throws IOException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        filmeDao.save(allFilmes, "filmes.dat");
    }

    /**
     * Carrega todos os filmes de um arquivo.
     *
     * @return Uma lista contendo todos os filmes carregados.
     * @throws IOException  Se ocorrer um erro de E/S durante o processo de carregamento.
     * @throws ClassNotFoundException  Se a classe do objeto carregado não for encontrada.
     */
    public static List<Filme> carregarTodosFilmes() throws IOException, ClassNotFoundException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        return filmeDao.load("filmes.dat");
    }
}
