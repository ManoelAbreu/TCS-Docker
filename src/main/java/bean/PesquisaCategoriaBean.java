package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.CategoriaDao;
import dao.ProdutoDao;
import dao.SaboresDao;
import model.Categoria;
import model.Produto;
import model.Sabores;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PesquisaCategoriaBean {

	private Categoria categoria;
	
	private String nome;
	
	private CategoriaDao categoriaDao;
	
	private List<Categoria> categorias;
	
	
	public void inicializar(){
		
		categoria = new Categoria();
		
		categoriaDao = new CategoriaDao();
		
		categorias = categoriaDao.listar();
		
	}

	
	public void excluir() {
		
		
		if(!verificaProdutosVinculados()) {
			FacesUtil.addWarnMenssage("Não foi possivel excluir \n Existem produtos vinculados a essa categoria");
		}else if (!verificarSaboresVinculados()) {
			FacesUtil.addWarnMenssage("Não foi possivel excluir \n Existem sabores vinculados a essa categoria");
		}else {
			categoriaDao.deletar(categoria);
			categorias = categoriaDao.listar();
			FacesUtil.addInfoMenssage("Excluído com sucesso");
			
		}
	}
	
	
	public void pesquisar() {
		
		categorias = categoriaDao.listarPorNome(nome);
	}

	
	private boolean verificarSaboresVinculados() {
		SaboresDao dao = new SaboresDao();
		List<Sabores>  listaSabores = dao.listar();
		
		for (Sabores sabores : listaSabores) {
			if(sabores.getCategoria().getId() == this.categoria.getId()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean verificaProdutosVinculados() {
		
		ProdutoDao dao = new ProdutoDao();
		List<Produto> produtos = dao.listar();
		
		for (Produto produto : produtos) {
			if(produto.getCategoria().getId() == this.categoria.getId()) {
				return false;
			}
		}
		return true;
		
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
		
}
