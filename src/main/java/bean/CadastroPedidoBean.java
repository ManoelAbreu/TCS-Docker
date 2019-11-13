/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.BordaDao;
import dao.MesaDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import dao.SaboresDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Borda;
import model.Categoria;
import model.ItemPedido;
import model.Mesa;
import model.Ocupacao;
import model.Pedido;
import model.Produto;
import model.Sabores;
import model.StatusEntrega;
import model.StatusPedido;
import model.StatusPreparo;
import util.FacesUtil;

/**
 *
 * @author lucia
 */
@ManagedBean
@ViewScoped
public class CadastroPedidoBean {

    private SaboresDao saboresDao;
    private MesaDao mesaDao;
    private ProdutoDao produtoDao;
    private BordaDao bordaDao;
    private PedidoDao pedidoDao;
    private Pedido pedido;
    private Mesa mesa;
    private Produto produto;
    private Categoria categoria;
    private Sabores sabor;
    private Sabores saborEscolhido;
    private ItemPedido itemPedido;
    private ItemPedido itemPedidoRemover;
    private List<Mesa> mesas;
    private List<Categoria> categorias;
    private List<Produto> produtos;
    private List<Borda> bordas;
    private List<Sabores> saboresList;
    private List<Sabores> saboresListEscolhidos;
    private List<ItemPedido> listaItensPedido;

    @PostConstruct
    public void init() {
        if (pedido == null) {
            pedido = new Pedido();
            Date date = new Date();
            pedido.setDataPedido(date);
        }
        saboresDao = new SaboresDao();
        mesaDao = new MesaDao();
        bordaDao = new BordaDao();
        produtoDao = new ProdutoDao();
        pedidoDao = new PedidoDao();
        mesa = new Mesa();
        itemPedido = new ItemPedido();
        mesas = mesaDao.listarParaPedido();
        produtos = produtoDao.listarParaPedidos();
        bordas = bordaDao.listar();
        listaItensPedido = new ArrayList<>();
    }

    public ItemPedido getItemPedidoRemover() {
        return itemPedidoRemover;
    }

    public void setItemPedidoRemover(ItemPedido itemPedidoRemover) {
        this.itemPedidoRemover = itemPedidoRemover;
    }

    public void carregaSabores() {
        if (itemPedido.getProduto() != null) {
            saboresList = saboresDao.listarParaPedido(itemPedido.getProduto().getCategoria().getId());
        } else {
            saboresList.clear();
        }
        saboresListEscolhidos = new ArrayList<>();
    }

    public Sabores getSaborEscolhido() {
        return saborEscolhido;
    }

    public void setSaborEscolhido(Sabores saborEscolhido) {
        this.saborEscolhido = saborEscolhido;
    }

    public List<Sabores> getSaboresList() {
        return saboresList;
    }

    public void setSaboresList(List<Sabores> saboresList) {
        this.saboresList = saboresList;
    }

    public List<Sabores> getSaboresListEscolhidos() {
        return saboresListEscolhidos;
    }

    public void setSaboresListEscolhidos(List<Sabores> saboresListEscolhidos) {
        this.saboresListEscolhidos = saboresListEscolhidos;
    }

    public List<Borda> getBordas() {
        return bordas;
    }

    public void setBordas(List<Borda> bordas) {
        this.bordas = bordas;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public double getValorTotalItem() {
        if (itemPedido.getQuantidade() != null && itemPedido.getProduto() != null && itemPedido.getBorda() != null) {
            return itemPedido.getProduto().getValor() * itemPedido.getQuantidade() + itemPedido.getBorda().getPreco();
        }
        if (itemPedido.getQuantidade() != null && itemPedido.getProduto() != null) {
            return itemPedido.getProduto().getValor() * itemPedido.getQuantidade();
        } else {
            return 0.0;
        }
    }

    public void adicionaSaborEscolhido() {
        if (saboresListEscolhidos.size() >= itemPedido.getProduto().getQuantidadeSabor()) {
            FacesUtil.addErrorMenssage("Esse produto só pode ter até " + itemPedido.getProduto().getQuantidadeSabor() + " sabor(es)!");
            return;
        }
        saboresListEscolhidos.add(saborEscolhido);
    }

    public void removeSaborEscolhido() {
        saboresListEscolhidos.remove(saborEscolhido);
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Sabores getSabor() {
        return sabor;
    }

    public void setSabor(Sabores sabor) {
        this.sabor = sabor;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<ItemPedido> getListaItensPedido() {
        return listaItensPedido;
    }

    public void setListaItensPedido(List<ItemPedido> listaItensPedido) {
        this.listaItensPedido = listaItensPedido;
    }

    public void filtraProdutos() {
        if (categoria != null) {
            List<Produto> produtosFiltrados = new ArrayList<>();
            for (Produto produto1 : produtos) {
                if (produto1.getCategoria() == categoria) {
                    produtosFiltrados.add(produto1);
                }
            }
            produtos = produtosFiltrados;
        }
    }

    public void salvarAction() throws IOException {
        if (listaItensPedido.isEmpty()) {
            FacesUtil.addWarnMenssage("Insira ao menos um item no pedido!");
        } else {
            pedido.getMesa().setOcupacao(Ocupacao.OCUPADA);
            mesaDao.salvar(pedido.getMesa());
            pedido.setNomeCliente(pedido.getMesa().getNomeCliente());
            pedido.setStatusPreparo(StatusPreparo.FILADEPREPARO);
            pedido.setStatusPedido(StatusPedido.ABERTO);
            Pedido pedidoRetorno = pedidoDao.salvar(pedido);
            if (pedidoRetorno != null) {
                pedido = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa-pedido.xhtml");
            }
        }
    }

    public boolean validaSaboresBorda() {
        if (itemPedido.getProduto() != null) {
            if (itemPedido.getProduto().getQuantidadeSabor() != null && itemPedido.getProduto().getQuantidadeSabor() > 0 || itemPedido.getProduto().isBorda()) {
                return true;
            }
        }
        return false;
    }

    public void adicionaNaListaItens() {
        if (itemPedido.getProduto().getQuantidadeSabor() != null && itemPedido.getProduto().getQuantidadeSabor() > 0 && saboresListEscolhidos.isEmpty()) {
            FacesUtil.addWarnMenssage("Esse produto possuí sabor(es) selecione ao menos um!!");
            return;
        }
        itemPedido.setStatusEntrega(StatusEntrega.PENDENTE);
        itemPedido.setSabores(saboresListEscolhidos);
        itemPedido.setValorUnitario(itemPedido.getProduto().getValor());
        listaItensPedido.add(itemPedido);

        pedido.setItemPedido(listaItensPedido);
        itemPedido = new ItemPedido();

        valorPedido();
    }

    public void removeDaListaItens() {
        listaItensPedido.remove(itemPedidoRemover);
        valorPedido();
    }

    public void valorPedido() {
        double total = 0.0;
        for (ItemPedido listaDeItens : listaItensPedido) {
            total += listaDeItens.getProduto().getValor() * listaDeItens.getQuantidade();
            if (listaDeItens.getBorda() != null) {
                total += listaDeItens.getBorda().getPreco();
            }
        }
        pedido.setTotalPedido(total);
    }

}
