package model;

public enum StatusPedido {
	
	ABERTO ("Aberto"),
	FECHADO ("Fechado");

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
