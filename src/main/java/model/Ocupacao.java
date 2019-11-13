package model;

public enum Ocupacao {
	

	DISPONIVEL("Disponivel"),
	OCUPADA("Ocupada"),
	PREFECHAMENTO("pre-fechamento");
	
	
	private String descricao;
	
	Ocupacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	

}
