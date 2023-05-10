import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class Cliente implements Serializable {
    private String nomeUsuario;
    private String senha;
    List<Media> listaParaVer;
    List<Media> listaJaVistas;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public List<Media> getListaParaVer() {
        return listaParaVer;
    }

    public List<Media> getListaJaVistas() {
        return listaJaVistas;
    }

    public Cliente(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.listaJaVistas = new ArrayList<>();
        this.listaParaVer = new ArrayList<>();
    }

    public void adicionarNaLista(Media media) {
        listaParaVer.add(media);
    }

    public void retirarDaLista(String nomeMedia) {
        listaParaVer.removeIf(l -> l.getNome().equals(nomeMedia));
    }

    public List<Media> filtrarPorGenero(String genero) {
        return listaParaVer.stream().filter(l -> l.getGenero().equals(genero)).collect(Collectors.toList());
    }

    public List<Media> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream().filter(l -> l.getIdioma().equals(idioma)).collect(Collectors.toList());
    }

//    public List<Media> filtrarPorQtdeEpisodios(int qtde) {
//        return listaParaVer.stream().filter(l -> l.getQuantidadeEpisodios() == qtde).collect(Collectors.toList());
//    }

    public void registrarAudiencia(Media media) {
        Optional<Media> existe = listaJaVistas.stream().filter(s -> s.equals(media)).findFirst();
        if (existe.isEmpty()) {
            media.registrarAudiencia();
            this.listaJaVistas.add(media);
        }
    }

    public static void salvarTodosClientes(List<Cliente> allClientes) throws IOException {
        GenericDao<Cliente> clienteDao = new GenericDao<>();
        clienteDao.save(allClientes, "clientes.dat");
    }

    public static List<Cliente> carregarTodosClientes() throws IOException, ClassNotFoundException {
        GenericDao<Cliente> clienteDao = new GenericDao<>();
        return clienteDao.load("clientes.dat");
    }

    public void avaliar(String nomeMedia, int nota) {
        Media media = listaJaVistas.stream().filter(s -> s.getNome().equals(nomeMedia)).findFirst().orElse(null);
        if (nonNull(media)) {
            media.addAvaliacao(nota);
        }
    }

}
