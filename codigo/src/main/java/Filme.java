import java.util.Date;

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

    public String toString() {
        return super.toString() + ". Duracao: " + this.duracao + ". Data de lançamento"
                + this.dataLancamento.toString();
    }
}
