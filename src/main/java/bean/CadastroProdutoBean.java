package bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CategoriaDao;
import dao.ProdutoDao;
import dao.SaboresDao;
import model.Categoria;
import model.Produto;
import model.Sabores;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class CadastroProdutoBean {

	private Produto produto;
	
	private ProdutoDao produtoDao;
	
	private List<Categoria> categorias;
	
	private CategoriaDao categoriaDao;
	
	private List<Sabores> sabores;
	
	private SaboresDao saboresDao;
	
	public void inicializar() {
		
		if(produto == null) {
			
			produto = new Produto();
			produto.setAtivo(true);
		}
		
		produtoDao = new ProdutoDao();
		
		produtoDao.listar();
		
		categoriaDao = new CategoriaDao();
		
		categorias = categoriaDao.listar();
		
	}
	
	public void listaSabores() {
		saboresDao = new SaboresDao();
		sabores = saboresDao.listar();
	}
	
	
	public void salvar() {
		
		produto = produtoDao.salvar(produto);
	
		if(produto != null) {
			FacesUtil.addInfoMenssage("Salvo com sucesso");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-produto.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			produto = new Produto();
		}else {
			FacesUtil.addErrorMenssage("Erro ao salvar");
		}
	
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Sabores> getSabores() {
		return sabores;
	}

	public void setSabores(List<Sabores> sabores) {
		this.sabores = sabores;
	}

}
