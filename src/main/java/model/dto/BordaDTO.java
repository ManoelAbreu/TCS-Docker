/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.Borda;

/**
 *
 * @author u
 */
public class BordaDTO {
    

    private String nome;
    private Double valor;

    public BordaDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
    
    public Borda getBorda(){
        Borda borda = new Borda();
        
        borda.setNome(getNome());
        borda.setPreco(getValor());
        return borda;
    }
    
}
