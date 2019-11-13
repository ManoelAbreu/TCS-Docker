package bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.BordaDao;
import model.Borda;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class CadastroBordaBean {

	private BordaDao bordaDao;
	
	private Borda borda;
	
	public void inicializar() {
		
		if(borda == null) {
			borda = new Borda();
		}
		
		bordaDao = new BordaDao();
	}
	
	public void salvar() {
	
		borda = bordaDao.salvar(borda);
		
		if(borda == null) {
			FacesUtil.addErrorMenssage("Erro ao salvar");
			
		}else {
			FacesUtil.addInfoMenssage("Salvo com sucesso");
            try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-borda.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		borda = new Borda();
	}

	public Borda getBorda() {
		return borda;
	}

	public void setBorda(Borda borda) {
		this.borda = borda;
	}
	
}
