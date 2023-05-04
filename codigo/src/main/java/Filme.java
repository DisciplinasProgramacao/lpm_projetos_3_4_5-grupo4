import java.util.Date;
import java.util.Scanner;

public class Filme extends Media {

    private Integer duracao;
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
	}
    public Integer getDuracao() {
        return duracao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public Integer getDuracaoSegundos() {
        return duracao * 60;
    }

    /**
     * 
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

    
}