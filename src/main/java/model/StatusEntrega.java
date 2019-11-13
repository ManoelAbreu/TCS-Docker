package model;

public enum StatusEntrega {

	PENDENTE ("Pendente"),
	ENTREGUE ("Entregue");

	private String descricao;

	StatusEntrega(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
