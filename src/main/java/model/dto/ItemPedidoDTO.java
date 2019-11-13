/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.ArrayList;
import java.util.List;
import model.ItemPedido;
import model.Sabores;
import model.StatusEntrega;

/**
 *
 * @author u
 */
public class ItemPedidoDTO {
    

    private Double valorUnitario;
    private int quantidade;
    private ProdutoDTO produto;
    private BordaDTO borda;
    private String descricao;
    private List<SaboresDTO> sabores;
    private StatusEntrega statusEntrega;

    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }
    

    public ItemPedidoDTO() {
    }
    
    

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public BordaDTO getBorda() {
        return borda;
    }

    public void setBorda(BordaDTO borda) {
        this.borda = borda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<SaboresDTO> getSabores() {
        return sabores;
    }

    public void setSabores(List<SaboresDTO> sabores) {
        this.sabores = sabores;
    }
    
    public ItemPedido getItemPedido(){
        ItemPedido itemPedido = new ItemPedido();
        
        
        itemPedido.setObservacao(getDescricao());
        itemPedido.setQuantidade(getQuantidade());
        itemPedido.setValorUnitario(getValorUnitario());
        itemPedido.setBorda(borda.getBorda());
        itemPedido.setProduto(produto.getProduto());
        itemPedido.setStatusEntrega(getStatusEntrega());
        
        List<Sabores> lista_sabores = new ArrayList<>();
        for (SaboresDTO saborDTO : sabores){
            Sabores sabor = saborDTO.getSabor();
            lista_sabores.add(sabor);
        }
        itemPedido.setSabores(lista_sabores);
        
        return itemPedido;
    }
    
}
