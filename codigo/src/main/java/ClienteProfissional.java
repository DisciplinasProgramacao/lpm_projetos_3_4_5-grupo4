public class ClienteProfissional implements PermissoesCliente {
    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeLancamento() {
        return true;
    }

    @Override
    public PermissoesCliente tornarPadrao() {
        return new ClientePadrao();
    }

    @Override
    public PermissoesCliente tornarEspecialista() {
        return new ClienteEspecialista();
    }

    @Override
    public PermissoesCliente tornarProfissional() {
        return this;
    }
}
