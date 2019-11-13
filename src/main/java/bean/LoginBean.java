package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.UsuarioDao;
import model.Perfil;
import model.Usuario;
import util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean {

    private String login;

    private String senha;

    private UsuarioDao usuarioDao;

    private Usuario usuario;

    public LoginBean() {
        usuarioDao = new UsuarioDao();
    }

    public String efetuarLogin() {

        if(usuarioDao.verificaLogin("admin", "1234") == null){
            usuarioDao.salvar(new Usuario("admin", "admin", "1234", true, Perfil.GERENTE));
        }
        this.usuario = usuarioDao.verificaLogin(login, senha);

        if (usuario == null) {
            FacesUtil.addErrorMenssage("Usuário ou senha inválidos!");

        } else if (usuario.isAtivo()) {

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            session.setAttribute("usuarioLogado", usuario);

            if (null != usuario.getPerfil()) {
                switch (usuario.getPerfil()) {
                    case GARCOM:
                        return "/garcom/pedido-garcom?faces-redirect=true";
                    case COZINHA:
                        return "/cozinha/pedido-cozinha?faces-redirect=true";
                    case CAIXA:
                        return "/caixa/caixa-pedido?faces-redirect=true";
                    default:
                        return "/inicioGerente/inicio-gerente?faces-redirect=true";
                }
            }

        } else {
            FacesUtil.addErrorMenssage("Usuário desativado!");
        }

        return "login?faces-redirect=true";

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        session.invalidate();

        return "/login?faces-redirect=true";
    }

}
