package model;

public enum StatusPreparo {

	FILADEPREPARO("Na fila de preparação"),
	EMPREPARACAO("Em preparação"),
	FINALIZADO("Finalizado");

	private String descricao;

	StatusPreparo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
