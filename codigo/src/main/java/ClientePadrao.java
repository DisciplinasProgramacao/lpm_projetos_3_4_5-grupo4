public class ClientePadrao implements PermissoesCliente {
    @Override
    public boolean podeComentar() {
        return false;
    }

    @Override
    public boolean podeLancamento() {
        return false;
    }
}
