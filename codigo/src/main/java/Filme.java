import java.text.SimpleDateFormat;
import java.util.Date;

public class Filme extends Media {

    private Integer duracao, codigo;
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
        this.codigo = Integer.parseInt(dadosLidos[0]);
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

    @Override
    public String toString() {
        return super.toString() +
                "\nFilme{" +
                "duracao=" + duracao +
                ", dataLancamento=" + dataLancamento +
                '}';
    }

    public String StringSalvar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return this.codigo + ";" + this.getNome() + ";" + sdf.format(this.dataLancamento) + ";" + this.duracao;
    }
}
