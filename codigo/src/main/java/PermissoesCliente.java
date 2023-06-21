public interface PermissoesCliente {
    boolean podeComentar();
    boolean podeLancamento();
    PermissoesCliente tornarPadrao();
    PermissoesCliente tornarEspecialista();
    PermissoesCliente tornarProfissional();
}
