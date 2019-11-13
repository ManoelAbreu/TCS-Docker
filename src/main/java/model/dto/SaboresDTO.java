/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.Categoria;
import model.Sabores;
/**
 *
 * @author u
 */
public class SaboresDTO {
    
    private long id;
    private String nome;
    private String descricao;
    private CategoriaDTO categoria;

    public SaboresDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
    
    public Sabores getSabor() {
        Sabores sabor = new Sabores();

        
        sabor.setNome(getNome());
        sabor.setDescricao(getDescricao());
        sabor.setCategoria(categoria.getCategoria());
      
        
        return sabor;
    }
    
}
