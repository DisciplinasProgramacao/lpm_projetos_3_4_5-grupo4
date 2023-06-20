public class ClienteEspecialista implements PermissoesCliente {
    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeLancamento() {
        return false;
    }

    @Override
    public PermissoesCliente tornarPadrao() {
        return new ClientePadrao();
    }

    @Override
    public PermissoesCliente tornarEspecialista() {
        return this;
    }

    @Override
    public PermissoesCliente tornarProfissional() {
        return new ClienteProfissional();
    }
}
