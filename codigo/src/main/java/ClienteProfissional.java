public class ClienteProfissional implements PermissoesCliente {
    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeLancamento() {
        return true;
    }
}
