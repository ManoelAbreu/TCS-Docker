package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long numero;

    @Column(nullable = false)
    private boolean situacao = true;

    @Column(nullable = false)
    private String qrCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ocupacao ocupacao;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = true)
    private String nomeCliente;

    @ManyToOne
    @JoinColumn(name = "garcom_id", nullable = true)
    private Usuario garcom;

    public Mesa() {
    }

    public Mesa(Long id, Long numero, boolean situacao, String qrCode, Ocupacao ocupacao, String descricao) {
        this.id = id;
        this.numero = numero;
        this.situacao = situacao;
        this.qrCode = qrCode;
        this.ocupacao = ocupacao;
        this.descricao = descricao;
    }

    public Usuario getGarcom() {
        return garcom;
    }

    public void setGarcom(Usuario garcom) {
        this.garcom = garcom;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Mesa other = (Mesa) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
