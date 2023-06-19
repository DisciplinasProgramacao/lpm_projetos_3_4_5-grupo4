public class ClienteEspecialista implements IComentarista {
    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeLancamento() {
        return false;
    }
}
