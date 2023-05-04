import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void retirarDaLista(String nome) {
        listaParaVer.removeIf(l -> l.getNome().equals(nome));
    }

    public List<Media> filtrarPorGenero(String genero) {
        return listaParaVer.stream().filter(l -> l.getGenero().equals(genero)).collect(Collectors.toList());
    }

    public List<Media> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream().filter(l -> l.getIdioma().equals(idioma)).collect(Collectors.toList());
    }
    // TODO: move to the right place (Vinicius)

//    public List<Media> filtrarPorQtdeEpisodios(int qtde) {
//        return listaParaVer.stream().filter(l -> l.getQuantidadeEpisodios() == qtde).collect(Collectors.toList());
//    }

    public void registrarAudiencia(Serie serie) {
        Optional<Media> existe = listaJaVistas.stream().filter(s -> s.equals(serie)).findFirst();
        if (existe.isEmpty()) {
            serie.registrarAudiencia();
            this.listaJaVistas.add(serie);
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

}
