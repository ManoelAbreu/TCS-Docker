package bean;

import dao.MesaDao;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Mesa;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PesquisaMesaBean {

    private MesaDao mesaDao;

    private List<Mesa> mesas;

    private Mesa mesaSelecionada;

    private Mesa mesaExlusao;

    public Mesa getMesaExlusao() {
        return mesaExlusao;
    }

    public void setMesaExlusao(Mesa mesaExlusao) {
        this.mesaExlusao = mesaExlusao;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> Mesas) {
        this.mesas = mesas;
    }
    
    public void listaDesativados(){
        mesas = mesaDao.listarDesativados();
    }

    public void inicializar() {
        if (mesaSelecionada == null) {
            mesaSelecionada = new Mesa();
        }
        mesaDao = new MesaDao();
        mesas = mesaDao.listar();
    }

    public void excluir() {
        mesaExlusao.setSituacao(false);
        mesaDao.salvar(mesaExlusao);
        buscarMesas();

        FacesUtil.addInfoMenssage("Mesa excluída com sucesso!");
    }
    
    public void pesquisarNumero(){
       mesas = mesaDao.listarPorNumero(mesaSelecionada.getNumero());
    }

    public void buscarMesas() {
        mesas = mesaDao.listar();
    }

    public Mesa getMesaSelecionada() {
        return mesaSelecionada;
    }

    public void setMesaSelecionada(Mesa MesaSelecionada) {
        this.mesaSelecionada = mesaSelecionada;
    }

    public void pesquisarPorNome() {

        if (mesaSelecionada.getDescricao() == null || mesaSelecionada.getDescricao().equals("")) {
            mesas = mesaDao.listar();
        } else {
            mesas = mesaDao.listarPorNome(mesaSelecionada.getDescricao());
        }

    }

}
