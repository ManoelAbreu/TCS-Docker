/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ItemPedido;
import model.Pedido;
import model.StatusEntrega;
import model.StatusPedido;
import model.StatusPreparo;

/**
 *
 * @author u
 */
public class PedidoDTO {
    

    private String cliente;
    private MesaDTO mesa;
    private StatusPreparo statusPreparo;
    private StatusPedido statusPedido;
    private Date dataPedido;
    private Double valorTotal;
    private List<ItemPedidoDTO> itensDoPedido;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    

    public PedidoDTO() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }

    public StatusPreparo getStatusPreparo() {
        return statusPreparo;
    }

    public void setStatusPreparo(StatusPreparo statusPreparo) {
        this.statusPreparo = statusPreparo;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItemPedidoDTO> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItemPedidoDTO> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }
 
    
    public Pedido getPedido() {
        Pedido pedido = new Pedido();
        
        
        pedido.setNomeCliente(getCliente());
        pedido.setDataPedido(getDataPedido());
        
        List<ItemPedido> lista_itens = new ArrayList<>();
        for(ItemPedidoDTO dto : getItensDoPedido()){
            ItemPedido item = dto.getItemPedido();
            lista_itens.add(item);
        }        
        pedido.setItemPedido(lista_itens);
        pedido.setStatusPedido(getStatusPedido());
        pedido.setStatusPreparo(getStatusPreparo());
        pedido.setTotalPedido(getValorTotal());
        
        pedido.setMesa(mesa.getMesa());    
        
        return pedido;
    }
    
}
