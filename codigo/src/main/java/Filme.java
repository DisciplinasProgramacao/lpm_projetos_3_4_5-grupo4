import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Filme extends Media implements Serializable {

    private Integer duracao;

    public Filme(Integer id, String nome, String genero, String idioma, Integer duracao, Date dataLancamento) {
        super(id, nome, genero, idioma, dataLancamento);
        this.duracao = duracao;
    }

    public Filme(String nome, String genero, String idioma, Integer duracao, Date dataLancamento) {
        super(nome, genero, idioma, dataLancamento);
        this.duracao = duracao;
    }

    public Filme(String[] dadosLidos) {
        super(Integer.parseInt(dadosLidos[0]), dadosLidos[1], "", "", new Date(dadosLidos[2]));
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

    public String StringSalvar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return this.id + ";" + this.getNome() + ";" + sdf.format(this.dataLancamento) + ";" + this.duracao;
    }

    public static void salvarTodosFilmes(List<Filme> allFilmes) throws IOException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        filmeDao.save(allFilmes, "data/filmes.dat");
    }

    public static List<Filme> carregarTodosFilmes() throws IOException, ClassNotFoundException {
        GenericDao<Filme> filmeDao = new GenericDao<>();
        return filmeDao.load("data/filmes.dat");
    }

    @Override
    public String toString() {
        return "Filme{" +
                "duracao=" + duracao +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", idioma='" + idioma + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", audiencia=" + audiencia +
                '}';
    }
}
