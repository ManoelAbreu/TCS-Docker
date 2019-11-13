package bean;

import dao.MesaDao;
import dao.PedidoDao;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.ItemPedido;
import model.Mesa;
import model.Pedido;
import model.Sabores;
import model.StatusEntrega;
import model.StatusPedido;
import model.StatusPreparo;

/**
 *
 * @author lucia
 */
@ManagedBean
@ViewScoped
public class PesquisaPedidoBean {

    private PedidoDao pedidoDao;
    private MesaDao mesaDao;
    private List<Pedido> pedidos;
    private List<Mesa> mesas;
    private Pedido pedidoSelecionado;
    private Pedido pedidoQuery;
    private ItemPedido item;
    private StatusEntrega[] statusEntregas;
    private StatusPedido[] statusPedidos;
    private StatusPreparo[] statusPreparos;

    @PostConstruct
    public void inicializar() {
        pedidoSelecionado = new Pedido();
        pedidoDao = new PedidoDao();
        mesaDao = new MesaDao();
        pedidos = pedidoDao.listar();
        mesas = mesaDao.listar();
        pedidoQuery = new Pedido();
        statusEntregas = StatusEntrega.values();
        statusPedidos = StatusPedido.values();
        statusPreparos = StatusPreparo.values();
    }

    public StatusEntrega[] getStatusEntregas() {
        return statusEntregas;
    }

    public void setStatusEntregas(StatusEntrega[] statusEntregas) {
        this.statusEntregas = statusEntregas;
    }

    public StatusPedido[] getStatusPedidos() {
        return statusPedidos;
    }

    public void setStatusPedidos(StatusPedido[] statusPedidos) {
        this.statusPedidos = statusPedidos;
    }

    public StatusPreparo[] getStatusPreparos() {
        return statusPreparos;
    }

    public void setStatusPreparos(StatusPreparo[] statusPreparos) {
        this.statusPreparos = statusPreparos;
    }

    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        item = null;
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public Pedido getPedidoQuery() {
        return pedidoQuery;
    }

    public void setPedidoQuery(Pedido pedidoQuery) {
        this.pedidoQuery = pedidoQuery;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void limpaFiltros() {
        pedidos = pedidoDao.listar();
    }

    public void pesquisar() {
        try {
            pedidos = pedidoDao.listarQuery(pedidoQuery);
        } catch (ParseException ex) {
            Logger.getLogger(PesquisaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String concatSabores(ItemPedido item) {

        String retorno = "";

        if (item != null && item.getSabores() != null) {
            for (int i = 0; i < item.getSabores().size(); i++) {
                if ((item.getSabores().size()-1) == i) {
                    retorno += item.getSabores().get(i).getNome();
                } else {
                    retorno += item.getSabores().get(i).getNome() + " / ";
                }
            }
        }

        return retorno;
    }

    public boolean validaObservacoes(ItemPedido item) {
        if (item.getObservacao() != null && !item.getObservacao().isEmpty()) {
            return false;
        }
        return true;
    }

    public int pegaNumeroItem(ItemPedido itemP) {
        if (itemP == null) {
            return 0;
        } else {
            return pedidoSelecionado.getItemPedido().indexOf(itemP) + 1;
        }
    }

}
