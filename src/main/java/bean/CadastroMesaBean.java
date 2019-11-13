/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import model.Mesa;
import dao.MesaDao;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Ocupacao;
import util.FacesUtil;

/**
 *
 * @author lucia
 */
@ManagedBean
@ViewScoped
public class CadastroMesaBean {

    private Mesa mesa;
    private MesaDao dao;

    public void init() {
        dao = new MesaDao();
        if (mesa == null){      
            mesa = new Mesa();
            mesa.setNumero((Long)dao.numeroMesa());
            mesa.setQrCode(geraQRCode());       
            mesa.setOcupacao(Ocupacao.DISPONIVEL);
        }
    }

    public void salvar() throws IOException {
        if (dao.pesquisarPorNumero(mesa.getNumero()) != null && mesa.getId() == null){
            FacesUtil.addErrorMenssage("Já existe um mesa com esse número");
            return;
        }

        mesa = dao.salvar(mesa);
        if (mesa != null) {
            FacesUtil.addInfoMenssage("Mesa salva com sucesso");
            FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-mesa.xhtml");
            mesa = new Mesa();
        } else {
            FacesUtil.addErrorMenssage("Erro ao salvar");
        }
        mesa = null;
    }
    
    public String geraQRCode(){
        int qtdChars =0;
        String sb = "";
       while(qtdChars <= 16){
		int charInt = (int) (Math.random() * 71); 
		if(((charInt >= 48) && (charInt <= 57)) || ((charInt >= 65) && (charInt <= 70))){ 
			char c = (char) charInt;	
			sb += c;
			qtdChars++;
		}		
	}
       return sb.toString();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public MesaDao getDao() {
        return dao;
    }

    public void setDao(MesaDao dao) {
        this.dao = dao;
    }

}
