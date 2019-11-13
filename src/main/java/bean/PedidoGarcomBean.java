/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ItemPedidoDao;
import dao.MesaDao;
import dao.PedidoDao;
import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.ItemPedido;
import model.Mesa;
import model.Pedido;
import model.StatusEntrega;
import model.StatusPreparo;
import model.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lucia
 */
@ManagedBean
@ViewScoped
public class PedidoGarcomBean {

    private Pedido pedido;
    private Usuario usuario;
    private MesaDao daoMesa;
    private PedidoDao daoPedido;
    private UsuarioDao daoUsuario;
    private List<Pedido> pedidos;
    private List<Pedido> pedidosMesaSelecionada;
    private List<Mesa> mesasEmUso;
    private List<Usuario> garcons;
    private Mesa mesaSelecionada;
    private ItemPedidoDao daoItemPedido;
    private ItemPedido itemPedidoEntregar;

    @PostConstruct
    public void init() {
        daoItemPedido = new ItemPedidoDao();
        daoMesa = new MesaDao();
        daoPedido = new PedidoDao();
        daoUsuario = new UsuarioDao();
        mesasEmUso = daoMesa.listarParaGarcom();
        garcons = daoUsuario.listarGarcons();
        mesaSelecionada = new Mesa();
    }

    public List<Pedido> getPedidosMesaSelecionada() {
        return pedidosMesaSelecionada;
    }

    public void setPedidosMesaSelecionada(List<Pedido> pedidosMesaSelecionada) {
        this.pedidosMesaSelecionada = pedidosMesaSelecionada;
    }

    public Mesa getMesaSelecionada() {
        return mesaSelecionada;
    }

    public void setMesaSelecionada(Mesa mesaSelecionada) {
        this.mesaSelecionada = mesaSelecionada;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Mesa> getMesasEmUso() {
        return mesasEmUso;
    }

    public void setMesasEmUso(List<Mesa> mesasEmUso) {
        this.mesasEmUso = mesasEmUso;
    }

    public List<Usuario> getGarcons() {
        return garcons;
    }

    public void setGarcons(List<Usuario> garcons) {
        this.garcons = garcons;
    }

    public ItemPedido getItemPedidoEntregar() {
        return itemPedidoEntregar;
    }

    public void setItemPedidoEntregar(ItemPedido itemPedidoEntregar) {
        this.itemPedidoEntregar = itemPedidoEntregar;
    }

    public void salvarGarcom() {
        if (mesaSelecionada.getGarcom() == null) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Selecione uma garçom!!"));
        } else {
            daoMesa.salvar(mesaSelecionada);
        }
    }

    public boolean verificaNovosPedidos(Mesa mesaValidacao) {
        if (mesaValidacao!=null &&!daoPedido.listarPorMesa(mesaValidacao.getId()).isEmpty()) {
            for (Pedido pedidoSepracaoStatus : daoPedido.listarPorMesa(mesaValidacao.getId())) {
                for (ItemPedido itemPedido : pedidoSepracaoStatus.getItemPedido()) {
                    if (itemPedido.getStatusEntrega() == StatusEntrega.PENDENTE && pedidoSepracaoStatus.getStatusPreparo() == StatusPreparo.FINALIZADO) {
                        return true;
                    } else if (itemPedido.getStatusEntrega() == StatusEntrega.PENDENTE && !itemPedido.getProduto().isItemCozinha()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void buscaPedidosMesa() {
        List<Pedido> pedidosRemocao = new ArrayList<>();
        pedidosMesaSelecionada = daoPedido.listarPorMesa(mesaSelecionada.getId());

        for (Pedido pedidoSepracaoStatus : pedidosMesaSelecionada) {
            boolean fica = false;
            for (ItemPedido itemPedido : pedidoSepracaoStatus.getItemPedido()) {
                if (itemPedido.getStatusEntrega() == StatusEntrega.PENDENTE && pedidoSepracaoStatus.getStatusPreparo() == StatusPreparo.FINALIZADO) {
                    fica = true;
                } else if (itemPedido.getStatusEntrega() == StatusEntrega.PENDENTE && !itemPedido.getProduto().isItemCozinha()) {
                    fica = true;
                }
            }
            if (fica == false) {
                pedidosRemocao.add(pedidoSepracaoStatus);
            }
        }

        for (Pedido pedidoRemover : pedidosRemocao) {
            pedidosMesaSelecionada.remove(pedidoRemover);
        }
    }

    public boolean validaRedenrizacao(Pedido pedido, ItemPedido itenPedido) {
        if (pedido != null && pedido.getStatusPreparo() != StatusPreparo.FINALIZADO) {
            if (itenPedido != null && itenPedido.getProduto().isItemCozinha()) {
                return false;
            }
        } else if (itenPedido != null && itenPedido.getStatusEntrega() == StatusEntrega.ENTREGUE) {
            return false;
        }
        return true;
    }

    public boolean verificarEntrega(ItemPedido itenPedido) {
        if (itenPedido.getStatusEntrega() == StatusEntrega.ENTREGUE) {
            return true;
        }
        return false;
    }

    public void entregaItem() {
        if (mesaSelecionada.getGarcom() == null) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Defina um garçom antes de entregar os itens!!"));
        } else {
            itemPedidoEntregar.setStatusEntrega(StatusEntrega.ENTREGUE);
            daoItemPedido.salvar(itemPedidoEntregar);
        }
    }

    public String concatSabores(ItemPedido item) {
        String retorno = "";
        if (item != null && item.getSabores() != null) {
            for (int i = 0; i < item.getSabores().size(); i++) {
                if ((item.getSabores().size() - 1) == i) {
                    retorno += item.getSabores().get(i).getNome();
                } else {
                    retorno += item.getSabores().get(i).getNome() + " / ";
                }
            }
        }
        return retorno;
    }
}
