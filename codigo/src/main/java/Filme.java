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

    public Integer getDuracaoSegundos() {
        return duracao * 60;
    }
}

    
}