
public class Filme {
	private int duracao;
	private String id, nome, dataLancamento;
	
	public Filme(String[] dadosLidos) {
		this.id = dadosLidos[0];
		this.nome = dadosLidos[1];
		this.dataLancamento = dadosLidos[2];
		this.duracao = Integer.parseInt(dadosLidos[3]);
	}
	
	public String getNome() {
		return this.nome;
	}
}
