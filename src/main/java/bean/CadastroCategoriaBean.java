package bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CategoriaDao;
import model.Categoria;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class CadastroCategoriaBean {

	private Categoria categoria;
	
	private CategoriaDao categoriaDao;
	
	public void inicializar() {
		
		if(categoria == null) {
			categoria = new Categoria();
		}
		
		categoriaDao = new CategoriaDao();
			
	}

	public void salvar() {
		
		categoria = categoriaDao.salvar(categoria);
		
		if(categoria != null) {
			FacesUtil.addInfoMenssage("Salvo com sucesso");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-categoria.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			categoria = new Categoria();
		}else {
			FacesUtil.addErrorMenssage("Erro ao salvar");
		}
		
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
