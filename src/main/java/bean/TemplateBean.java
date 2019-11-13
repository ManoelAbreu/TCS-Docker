/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Perfil;
import model.Usuario;

/**
 *
 * @author lucia
 */
@ManagedBean
@ViewScoped
public class TemplateBean {

    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (Usuario) session.getAttribute("usuarioLogado");
    }

    public boolean verificaPerfilGerente() {
        if (usuario != null && usuario.getPerfil() != Perfil.GERENTE) {
            return false;
        }
        return true;
    }

    public boolean verificaPerfilCaixa() {
        if (usuario != null && usuario.getPerfil() != Perfil.CAIXA) {
            return false;
        }
        return true;
    }

    public boolean verificaPerfilGarcom() {
        if (usuario != null && usuario.getPerfil() != Perfil.GARCOM) {
            return false;
        }
        return true;
    }

    public boolean verificaPerfilCozinha() {
        if (usuario != null && usuario.getPerfil() != Perfil.COZINHA) {
            return false;
        }
        return true;
    }

    public String retornaImagem() {
        if (usuario.getPerfil() == Perfil.GARCOM) {
            return "waiter.png";
        } else if (usuario.getPerfil() == Perfil.COZINHA) {
            return "baker.png";
        } else if(usuario.getPerfil() == Perfil.CAIXA) {
            return "cashier.png";
        }else{
            return "girl.png";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
