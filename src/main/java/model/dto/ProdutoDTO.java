/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.Categoria;
import model.Produto;

/**
 *
 * @author u
 */
public class ProdutoDTO {

 
    
    private String nome;
    private String descricao;
    private Double valor;
    private boolean ativo;
    private CategoriaDTO categoria;
    private Integer quantidadeSabor;
    private boolean itemCozinha;
    private boolean borda;

    public ProdutoDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidadeSabor() {
        return quantidadeSabor;
    }

    public void setQuantidadeSabor(Integer quantidadeSabor) {
        this.quantidadeSabor = quantidadeSabor;
    }

    public boolean isItemCozinha() {
        return itemCozinha;
    }

    public void setItemCozinha(boolean itemCozinha) {
        this.itemCozinha = itemCozinha;
    }

    public boolean isBorda() {
        return borda;
    }

    public void setBorda(boolean borda) {
        this.borda = borda;
    }
    
    
    
    public Produto getProduto() {
        Produto produto = new Produto();
   
        produto.setNome(getNome());
        produto.setDescricao(getDescricao());
        produto.setValor(getValor());
        produto.setCategoria(categoria.getCategoria());
        produto.setQuantidadeSabor(getQuantidadeSabor());
        produto.setBorda(isBorda());
        produto.setItemCozinha(isItemCozinha());   
        produto.setAtivo(isAtivo());
        
        return produto;
    }
    
}
