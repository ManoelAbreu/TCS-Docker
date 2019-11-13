package bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDao;
import model.Perfil;
import model.Usuario;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class CadastroUsuarioBean {

	private Usuario usuario;
	
	private Perfil perfis[] = Perfil.values() ;
	
	private UsuarioDao usuarioDao;
	
	public void inicializar() {
		
		
		if(usuario == null) {
			usuario = new Usuario();
		}
				
		usuarioDao = new UsuarioDao();
	}
	
	public void salvar() {
		
		usuario = usuarioDao.salvar(usuario);
		
		if(usuario != null) {
			FacesUtil.addInfoMenssage("Cadastrado com sucesso");	
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-usuario.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			usuario = new Usuario();
		}else {
			FacesUtil.addErrorMenssage("Erro ao salvar");
		}
		
	}	
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil[] getPerfis() {
		return perfis;
	}
			
}
