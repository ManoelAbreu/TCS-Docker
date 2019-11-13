/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.Mesa;
import model.Ocupacao;

/**
 *
 * @author u
 */
public class MesaDTO {
 
    private long id;
    private long numero;
    private boolean situacao;
    private String QRCode;
    private Ocupacao ocupacao;
    private String descricao;
    private String nomeCliente;

    public MesaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    
    public Mesa getMesa(){
        Mesa mesa = new Mesa();
        
        mesa.setId(getId());
        mesa.setDescricao(getDescricao());
        mesa.setNomeCliente(getNomeCliente());
        mesa.setNumero(getNumero());
        mesa.setOcupacao(getOcupacao());
        mesa.setQrCode(getQRCode());
        mesa.setSituacao(isSituacao());
        
        return mesa;
    }
    
    
}
