package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "produto")
@XmlRootElement
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String descricao;
	
	@Column(nullable = false)
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Column
	private Integer quantidadeSabor;
	
	@Column
	private boolean borda;
	
	@Column(nullable = false)
	private boolean itemCozinha;
	
	// status 
	@Column(nullable = false)
	private boolean ativo;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}


	public Produto(Long id, String nome, String descricao, Double valor, Categoria categoria, Integer quantidadeSabor,
			boolean borda, boolean itemCozinha, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.quantidadeSabor = quantidadeSabor;
		this.borda = borda;
		this.itemCozinha = itemCozinha;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getQuantidadeSabor() {
		return quantidadeSabor;
	}

	public void setQuantidadeSabor(Integer quantidadeSabor) {
		this.quantidadeSabor = quantidadeSabor;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public boolean isBorda() {
		return borda;
	}

	public void setBorda(boolean borda) {
		this.borda = borda;
	}

	public boolean isItemCozinha() {
		return itemCozinha;
	}

	public void setItemCozinha(boolean itemCozinha) {
		this.itemCozinha = itemCozinha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
