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
    Especialista tipoCliente = null;

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

    /**
     * Adiciona uma nova mídia à lista de mídias para ver do cliente.
     *
     * @param media a mídia a ser adicionada
     */
    public void adicionarNaLista(Media media) {
        listaParaVer.add(media);
    }

    /**
     * Remove uma mídia da lista de mídias para ver do cliente, com base no nome da mídia.
     *
     * @param nomeMedia o nome da mídia a ser removida
     */
    public void retirarDaLista(String nomeMedia) {
        listaParaVer.removeIf(l -> l.getNome().equals(nomeMedia));
    }

    /**
     * Filtra as mídias da lista de mídias para ver do cliente por gênero.
     *
     * @param genero o gênero pelo qual as mídias serão filtradas
     * @return uma lista de mídias filtradas por gênero
     */
    public List<Media> filtrarPorGenero(String genero) {
        return listaParaVer.stream().filter(l -> l.getGenero().equals(genero)).collect(Collectors.toList());
    }

    /**
     * Filtra as mídias da lista de mídias para ver do cliente por idioma.
     *
     * @param idioma o idioma pelo qual as mídias serão filtradas
     * @return uma lista de mídias filtradas por idioma
     */
    public List<Media> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream().filter(l -> l.getIdioma().equals(idioma)).collect(Collectors.toList());
    }

    /**
     * Registra a visualização de uma mídia pelo cliente.
     * Se a mídia já foi registrada como vista pelo cliente, não faz nada.
     *
     * @param media a mídia a ser registrada como vista
     */
    public void registrarAudiencia(Media media) {
        Optional<Media> existe = listaJaVistas.stream().filter(s -> s.equals(media)).findFirst();
        if (existe.isEmpty()) {
            media.registrarAudiencia();
            this.listaJaVistas.add(media);
        }
    }

    /**
     * Salva uma lista de clientes em um arquivo com o nome "clientes.dat".
     *
     * @param allClientes a lista de clientes a ser salva
     * @throws IOException se ocorrer um erro de I/O durante a escrita do arquivo
     */
    public static void salvarTodosClientes(List<Cliente> allClientes) throws IOException {
        GenericDao<Cliente> clienteDao = new GenericDao<>();
        clienteDao.save(allClientes, "clientes.dat");
    }

    /**
     * Carrega a lista de clientes salva no arquivo "clientes.dat".
     *
     * @return uma lista de clientes carregada do arquivo
     * @throws IOException se ocorrer um erro de I/O durante a leitura do arquivo
     * @throws ClassNotFoundException se a classe do objeto lido do arquivo não for encontrada
     */
    public static List<Cliente> carregarTodosClientes() throws IOException, ClassNotFoundException {
        GenericDao<Cliente> clienteDao = new GenericDao<>();
        return clienteDao.load("clientes.dat");
    }

    /**
     * Adiciona uma avaliação (nota) de uma mídia vista pelo cliente.
     *
     * @param nomeMedia o nome da mídia avaliada
     * @param nota a nota atribuída à mídia pelo cliente
     */
    public void avaliar(String nomeMedia, int nota) {
        Media media = listaJaVistas.stream().filter(s -> s.getNome().equals(nomeMedia)).findFirst().orElse(null);
        if (nonNull(media)) {
            Avaliacao avaliacao = new Avaliacao(this, nota);
            media.addAvaliacao(avaliacao);
        }
    }

    public boolean isClienteEspecialista() {
        if (listaJaVistas.size() >=5) {
            this.tipoCliente = new Especialista();
            return true;
        } else {
            this.tipoCliente = null;
            return false;
        }

    }

    public void avaliarComComentario(String nomeMedia, int nota, String comentario) {
        if (isClienteEspecialista()) {
            this.tipoCliente.avaliarComComentario(nomeMedia, nota, comentario, this);
        } else {
            System.err.println("Voce deve ser cliente especialista para escrever comentario");
        }
    }

}
