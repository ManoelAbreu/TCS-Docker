package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.MesaDao;
import dao.PedidoDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import model.ItemPedido;
import model.Mesa;
import model.Ocupacao;
import model.Pedido;
import model.StatusPedido;

@ViewScoped
@ManagedBean
public class CaixaBean {

    private Pedido pedido;
    private List<Pedido> pedidos;
    private PedidoDao pedidoDao;
    private MesaDao mesaDao;
    private List<Mesa> mesasOcupadas;
    private Mesa mesaSelecionada;
    private Mesa mesaFinalizar;
    private List<ItemPedido> todosItensMesa;

    @PostConstruct
    public void init() {
        mesaDao = new MesaDao();
        pedidoDao = new PedidoDao();
        mesasOcupadas = mesaDao.listarParaGarcom();
        pedido = new Pedido();
        mesaSelecionada = new Mesa();
    }

    public void finalizar() {
        System.out.println("merdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        mesaFinalizar.setOcupacao(Ocupacao.DISPONIVEL);
        mesaFinalizar.setNomeCliente(null);
        mesaFinalizar.setGarcom(null);
        mesaDao.salvar(mesaFinalizar);
        for (Pedido pedidoFinalizacao : pedidoDao.listarPorMesa(mesaFinalizar.getId())) {
            pedidoFinalizacao.setStatusPedido(StatusPedido.FECHADO);
            pedidoDao.salvar(pedidoFinalizacao);
        }
        mesasOcupadas = mesaDao.listarParaGarcom();
        mesaFinalizar = null;
    }

    public Mesa getMesaFinalizar() {
        return mesaFinalizar;
    }

    public void setMesaFinalizar(Mesa mesaFinalizar) {
        this.mesaFinalizar = mesaFinalizar;
    }

    public List<ItemPedido> getTodosItensMesa() {
        return todosItensMesa;
    }

    public void setTodosItensMesa(List<ItemPedido> todosItensMesa) {
        this.todosItensMesa = todosItensMesa;
    }

    public List<Mesa> getMesasOcupadas() {
        return mesasOcupadas;
    }

    public void setMesasOcupadas(List<Mesa> mesasOcupadas) {
        this.mesasOcupadas = mesasOcupadas;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Mesa getMesaSelecionada() {
        return mesaSelecionada;
    }

    public void setMesaSelecionada(Mesa mesaSelecionada) {
        this.mesaSelecionada = mesaSelecionada;
    }

    public void buscaPedidosMesa() {
        if (mesaSelecionada != null) {
            pedidos = pedidoDao.listarPorMesa(mesaSelecionada.getId());
        }
        todosItensMesa = new ArrayList();
        for (Pedido pedidoMesa : pedidos) {
            todosItensMesa.addAll(pedidoMesa.getItemPedido());
        }
    }

    public double totalMesa(Mesa mesa) {
        List<Pedido> pedidosTotal;
        double total = 0;
        pedidosTotal = pedidoDao.listarPorMesa(mesa.getId());
        for (Pedido pedido1 : pedidosTotal) {
            total += pedido1.getTotalPedido();
        }
        return total;
    }

    public double totalItem(ItemPedido item) {
        return item.getQuantidade() * item.getValorUnitario();
    }

}
