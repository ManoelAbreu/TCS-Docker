package bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CategoriaDao;
import dao.SaboresDao;
import model.Categoria;
import model.Sabores;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class CadastroSaboresBean {

	private Sabores sabores;
	
	private SaboresDao saboresDao;
	
	private List<Categoria> categorias;
	
	private CategoriaDao categoriaDao;
	
	public void inicializar() {
		
		if(sabores == null) {
			sabores = new Sabores();
		}
		
		saboresDao = new SaboresDao();
			
		categoriaDao = new CategoriaDao();
		
		categorias = categoriaDao.listar();
		
	}
	
	public void salvar() {
		
		sabores = saboresDao.salvar(sabores);
		
		if(sabores != null) {
			FacesUtil.addInfoMenssage("Salvo com sucesso");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-sabores.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sabores = new Sabores();
		}else {
			FacesUtil.addErrorMenssage("Erro ao salvar");
		}
	}

	public Sabores getSabores() {
		return sabores;
	}

	public void setSabores(Sabores sabores) {
		this.sabores = sabores;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
