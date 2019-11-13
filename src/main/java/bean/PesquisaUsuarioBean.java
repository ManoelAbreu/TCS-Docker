package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDao;
import model.Usuario;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PesquisaUsuarioBean {

	private Usuario usuario;

	private String nome;
	
	private UsuarioDao usuarioDao;
	
	private List<Usuario> usuarios;
	
	public void inicializar() {
		
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		
		usuarios = usuarioDao.listarAtivo();
	}
	
	public void pesquisar() {
		
		usuarios = usuarioDao.listarPorNome(nome);
		
	}
	
	public void excluir() {
		
		usuario.setAtivo(false);
		usuarioDao.salvar(usuario);
		if (usuario != null) {
			FacesUtil.addInfoMenssage("Excluído com sucesso");
		}else {
			FacesUtil.addErrorMenssage("Erro ao excluir");
		}
		
		usuarios = usuarioDao.listarAtivo();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
