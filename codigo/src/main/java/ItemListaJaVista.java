import java.util.Date;

public class ItemListaJaVista {
    
    private Media listaJaVistas;
    private Date dataVizualizacao;

    public ItemListaJaVista(Media media, Date dataa){
        this.listaJaVistas = media;
        this.dataVizualizacao = dataa;
    }
    public ItemListaJaVista(Media media){
        Date hoje = new Date();
        this.listaJaVistas = media;
        this.dataVizualizacao = hoje;
    }

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
