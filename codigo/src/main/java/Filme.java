import java.util.Date;
import java.util.Scanner;

public class Filme extends Media {

    private Integer duracao;
    private Date dataLancamento;

    public Filme(String nome, String genero, String idioma, Integer duracao, Date dataLancamento) {
        super(nome, genero, idioma);
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
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

    public void CadastraFilmeClientSide(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Seja bem-vindo ao cadastro de Filmes!");
        System.out.println("Digite o Id do filme:");
        int idFilme = sc.nextInt();
        System.out.println("Digite o Nome do filme:");
        String nomeFilme = sc.nextLine();
        System.out.println("Digite a data de lancamento do filme:");
        String dataLancamento = sc.nextLine();
        System.out.println("Digite a duracao do filme:");
        int duracaoFilme = sc.nextInt();
    
         

    }

    
}