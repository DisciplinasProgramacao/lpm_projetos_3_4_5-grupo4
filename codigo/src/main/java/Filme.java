import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    public static void salvarTodosFilmes(List<Filme> allFilmes) throws IOException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        filmeDao.save(allFilmes, "filmes.dat");
    }

    public static List<Filme> carregarTodosFilmes() throws IOException, ClassNotFoundException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        return filmeDao.load("filmes.dat");
    }
}
