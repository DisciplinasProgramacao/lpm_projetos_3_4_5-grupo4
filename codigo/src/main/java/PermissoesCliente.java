import java.io.Serializable;

public interface PermissoesCliente extends Serializable {
    boolean podeComentar();
    boolean podeLancamento();
    PermissoesCliente tornarPadrao();
    PermissoesCliente tornarEspecialista();
    PermissoesCliente tornarProfissional();
}
