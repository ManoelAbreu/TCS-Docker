package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.SaboresDao;
import model.Sabores;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PesquisaSaboresBean {

	private Sabores sabores;
	
	private String nome;
	
	private SaboresDao saboresDao;
	
	private List<Sabores> listaSabores;
	
	public void inicializar() {
		
		saboresDao = new SaboresDao();
		listar();
	}
	
	public void excluir() {
		
		saboresDao.deletar(sabores);
		listar();
		FacesUtil.addInfoMenssage("Excluído com sucesso");
		
	}
	
	public void pesquisar() {
		listaSabores = saboresDao.listarPorNome(nome);
	}

	private void listar() {
		listaSabores = saboresDao.listar();
	}

	public Sabores getSabores() {
		return sabores;
	}

	public void setSabores(Sabores sabores) {
		this.sabores = sabores;
	}

	public List<Sabores> getListaSabores() {
		return listaSabores;
	}

	public void setListaSabores(List<Sabores> listaSabores) {
		this.listaSabores = listaSabores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
