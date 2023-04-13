import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cliente {
    private String nomeUsuario;
    private String senha;
    List<Serie> listaParaVer;
    List<Serie> listaJaVistas;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public List<Serie> getListaParaVer() {
        return listaParaVer;
    }

    public List<Serie> getListaJaVistas() {
        return listaJaVistas;
    }

    public Cliente(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie) {
        listaParaVer.removeIf(l -> l.getNome().equals(nomeSerie));
    }

    public List<Serie> filtrarPorGenero(String genero) {
        return listaParaVer.stream().filter(l -> l.getGenero().equals(genero)).collect(Collectors.toList());
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream().filter(l -> l.getIdioma().equals(idioma)).collect(Collectors.toList());
    }

    public List<Serie> filtrarPorQtdeEpisodios(int qtde) {
        return listaParaVer.stream().filter(l -> l.getQuantidadeEpisodios() == qtde).collect(Collectors.toList());
    }

    public void registrarAudiencia(Serie serie) {
        Optional<Serie> existe = listaJaVistas.stream().filter(s -> s.equals(serie)).findFirst();
        if (existe.isEmpty()) {
            serie.registrarAudiencia();
            this.listaJaVistas.add(serie);
        }
    }

}
