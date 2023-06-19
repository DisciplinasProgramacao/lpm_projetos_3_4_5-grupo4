public class ClienteProfissional implements IComentarista {
    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeLancamento() {
        return true;
    }
}
