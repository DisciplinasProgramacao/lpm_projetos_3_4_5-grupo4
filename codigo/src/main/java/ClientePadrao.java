public class ClientePadrao implements IComentarista {
    @Override
    public boolean podeComentar() {
        return false;
    }

    @Override
    public boolean podeLancamento() {
        return false;
    }
}
