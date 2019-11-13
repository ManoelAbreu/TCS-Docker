package model;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@XmlRootElement
@Entity
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido statusPedido;
    
    @Column(nullable = false)
    private Double totalPedido;

    @Column(nullable = false)
    private Date dataPedido;
    
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itemPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPreparo statusPreparo;



    public Pedido() {
    }

    public Pedido(Long id, String nomeCliente, Mesa mesa, List<ItemPedido> itemPedido) {
        super();
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.mesa = mesa;
        this.itemPedido = itemPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getTotalPedido() {
        if(totalPedido == null){
            return 0.0;
        }
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }
    
    public StatusPreparo getStatusPreparo() {
        return statusPreparo;
    }

    public void setStatusPreparo(StatusPreparo statusPreparo) {
        this.statusPreparo = statusPreparo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<ItemPedido> getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(List<ItemPedido> itemPedido) {
        this.itemPedido = itemPedido;
    }
    
    public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
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
        Pedido other = (Pedido) obj;
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
