package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ProdutoDao;
import model.Produto;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PesquisaProdutoBean {

	private Produto produto;
	
	private String nome;
	
	private ProdutoDao produtoDao;
	
	private List<Produto> produtos;
	

	public void inicializar() {
		
		produtoDao = new ProdutoDao();
		
		produtos = produtoDao.listar();
			
	}
	
	public void excluir() {
		produtoDao.deletar(produto);
		produtos = produtoDao.listar();
		FacesUtil.addInfoMenssage("Excluido com sucesso");
		
	}
	
	public void pesquisar() {
		
		produtos = produtoDao.listarPorNome(nome);
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
