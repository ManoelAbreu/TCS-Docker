package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@XmlRootElement
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double valorUnitario;

    @Column(nullable = false)
    private Integer quantidade = 1;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sabor_id")
    private List<Sabores> sabores;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "borda_id")
    private Borda borda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntrega statusEntrega;

    public ItemPedido() {
    }

    public ItemPedido(Long id, Double valorUnitario, Integer quantidade, Produto produto, List<Sabores> sabores) {
        super();
        this.id = id;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.produto = produto;
        this.sabores = sabores;
    }

    public Borda getBorda() {
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public List<Sabores> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabores> sabores) {
        this.sabores = sabores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
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
        ItemPedido other = (ItemPedido) obj;
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
