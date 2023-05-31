import java.io.Serializable;
import java.util.Date;

public class ItemListaJaVista implements Serializable {
    
    private Media listaJaVistas;
    private Date dataVizualizacao;

    /**
     * Construtor que cria um ItemListaJaVista com a mídia e a data de visualização fornecidas.
     *
     * @param media       A mídia associada ao ItemListaJaVista.
     * @param dataVizualizacao A data de visualização da mídia.
     */
    public ItemListaJaVista(Media media, Date dataa){
        this.listaJaVistas = media;
        this.dataVizualizacao = dataa;
    }

    /**
     * Construtor que cria um ItemListaJaVista com a mídia fornecida e a data de visualização como a data atual.
     *
     * @param media A mídia associada ao ItemListaJaVista.
     */
    public ItemListaJaVista(Media media){
        Date hoje = new Date();
        this.listaJaVistas = media;
        this.dataVizualizacao = hoje;
    }

    /**
     * Verifica se o item da lista é válido, ou seja, se a data de visualização está dentro dos últimos 30 dias.
     *
     * @return true se o item da lista é válido, false caso contrário.
     */
    public boolean isValid(){
        Date hoje = new Date();
        long umDiaEmMilissegundos = 24 * 60 * 60 * 1000; // Quantidade de milissegundos em um dia
        long trintaDiasEmMilissegundos = 30 * umDiaEmMilissegundos; // Quantidade de milissegundos em 30 dias
        if(dataVizualizacao.getTime() >= (hoje.getTime() - trintaDiasEmMilissegundos)){
            return true;
        }else{
            return false;
        }
    }

    public Media getMedia(){
        return this.listaJaVistas;
    }

    public Date getDataVizualizacao(){
        return this.dataVizualizacao;
    }

}
