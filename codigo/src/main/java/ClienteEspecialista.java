public class ClienteEspecialista implements PermissoesCliente {
    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeLancamento() {
        return false;
    }
}
