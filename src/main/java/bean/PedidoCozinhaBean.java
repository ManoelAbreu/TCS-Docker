/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PedidoDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.ItemPedido;
import model.Pedido;
import model.Sabores;
import model.StatusEntrega;
import model.StatusPreparo;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lucia
 */
@ManagedBean(name = "PedidoCozinnhaBean")
@ViewScoped
public class PedidoCozinhaBean {

    private List<Pedido> pedidos;
    private List<Pedido> pedidosPreparo;
    private List<Pedido> pedidosFinalizados;
    private Pedido pedido;
    private PedidoDao dao = new PedidoDao();
    private boolean atualizar;

    @PostConstruct
    public void init() {
        pedido = new Pedido();
        pedidos = dao.listarCozinha();
        pedidosPreparo = new ArrayList<>();
        pedidosFinalizados = new ArrayList<>();
        List<Pedido> pedidosFor = new ArrayList<>();
        List<Pedido> pedidosFiltrados = new ArrayList<>();

        for (Pedido pedido1 : pedidos) {
            for (ItemPedido itemPedido : pedido1.getItemPedido()) {
                if (itemPedido.getProduto().isItemCozinha() == true) {
                    pedidosFiltrados.add(pedido1);
                    break;
                }
            }
        }
        pedidos = pedidosFiltrados;

        for (Pedido pedidoList : pedidos) {
            if (pedidoList.getStatusPreparo() == StatusPreparo.EMPREPARACAO) {
                pedidosPreparo.add(pedidoList);
                pedidosFor.add(pedidoList);
            } else if (pedidoList.getStatusPreparo() == StatusPreparo.FINALIZADO) {
                pedidosFinalizados.add(pedidoList);
                pedidosFor.add(pedidoList);
                for (ItemPedido itensPedido : pedidoList.getItemPedido()) {
                    if (itensPedido.getStatusEntrega() == StatusEntrega.ENTREGUE && itensPedido.getProduto().isItemCozinha()) {
                        pedidosFinalizados.remove(pedidoList);
                    }
                }
            }
        }

        for (Pedido pedido1 : pedidosFor) {
            pedidos.remove(pedido1);
        }
    }

    public boolean isAtualizar() {
        return atualizar;
    }

    public void setAtualizar(boolean atualizar) {
        this.atualizar = atualizar;
    }
    

    public void proximoParaFin() {
        pedido.setStatusPreparo(StatusPreparo.FINALIZADO);
        dao.salvar(pedido);
        init();
    }

    public void proximoParaPrep() {
        pedido.setStatusPreparo(StatusPreparo.EMPREPARACAO);
        dao.salvar(pedido);
        init();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidosPreparo() {
        return pedidosPreparo;
    }

    public void setPedidosPreparo(List<Pedido> pedidosPreparo) {
        this.pedidosPreparo = pedidosPreparo;
    }

    public List<Pedido> getPedidosFinalizados() {
        return pedidosFinalizados;
    }

    public void setPedidosFinalizados(List<Pedido> pedidosFinalizados) {
        this.pedidosFinalizados = pedidosFinalizados;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public boolean validaObservacoes(Pedido pedidoL) {
        if (pedidoL != null) {
            for (ItemPedido itemPedido : pedidoL.getItemPedido()) {
                if (itemPedido.getObservacao() != null && itemPedido.getProduto().isItemCozinha() == true && !itemPedido.getObservacao().isEmpty()) {
                    return false;
                } else if (itemPedido.getBorda() != null && itemPedido.getProduto().isItemCozinha() == true) {
                    return false;
                } else if (itemPedido.getSabores() != null && !itemPedido.getSabores().isEmpty() && itemPedido.getProduto().isItemCozinha() == true) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public String concatSabores(ItemPedido item) {
        String retorno = "";

        for (int i = 0; i < item.getSabores().size(); i++) {
            if ((item.getSabores().size() - 1) == i) {
                retorno += item.getSabores().get(i).getNome();
            } else {
                retorno += item.getSabores().get(i).getNome() + " / ";
            }
        }

        return retorno;
    }

}
