package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.BordaDao;
import model.Borda;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PesquisaBordaBean {

	private Borda borda;
	
	private String nome;
	
	private BordaDao bordaDao;
	
	private List<Borda> bordas;
	
	public void inicializar() {

		borda = new Borda();
		
		bordaDao = new BordaDao();
		
		bordas = bordaDao.listar();
		
	}
	
	public void excluir() {
		
		bordaDao.deletar(borda);
		bordas = bordaDao.listar();
		FacesUtil.addInfoMenssage("Excluído com sucesso");
		borda = null;
		
	}
	
	public void pesquisar() {
		
		bordas = bordaDao.listarPorNome(nome);
	}

	public Borda getBorda() {
		return borda;
	}

	public void setBorda(Borda borda) {
		this.borda = borda;
	}

	public List<Borda> getBordas() {
		return bordas;
	}

	public void setBordas(List<Borda> bordas) {
		this.bordas = bordas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
