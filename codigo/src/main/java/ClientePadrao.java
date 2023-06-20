public class ClientePadrao implements PermissoesCliente {
    @Override
    public boolean podeComentar() {
        return false;
    }

    @Override
    public boolean podeLancamento() {
        return false;
    }

    @Override
    public PermissoesCliente tornarPadrao() {
        return this;
    }

    @Override
    public PermissoesCliente tornarEspecialista() {
        return new ClienteEspecialista();
    }

    @Override
    public PermissoesCliente tornarProfissional() {
        return new ClienteProfissional();
    }
}
