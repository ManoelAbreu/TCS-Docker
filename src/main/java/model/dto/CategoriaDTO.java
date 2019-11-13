/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.Categoria;

/**
 *
 * @author u
 */
public class CategoriaDTO {
    

    private long id;
    private String nome;
    private boolean divisivel;

    public CategoriaDTO() {
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

    public boolean isDivisivel() {
        return divisivel;
    }

    public void setDivisivel(boolean divisivel) {
        this.divisivel = divisivel;
    }
    
    
    

    public Categoria getCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(getId());
        categoria.setNome(getNome());
        categoria.setDivisivel(isDivisivel());
        return categoria;
    }
    
}
