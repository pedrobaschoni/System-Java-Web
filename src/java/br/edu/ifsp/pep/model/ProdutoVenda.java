package br.edu.ifsp.pep.model;

import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "produto_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdutoVenda.findAll", query = "SELECT p FROM ProdutoVenda p")})
@IdClass(ProdutoVendaPK.class)
public class ProdutoVenda implements Serializable{
    
    @Id
    @JoinColumn(name="produto_id")
    @ManyToOne(optional = false)
    private Produto produto_id;
    
    @Id
    @JoinColumn(name="venda_id")
    @ManyToOne(optional = false)
    private Venda venda_id;

    @Basic(optional = false)
    @Column(name = "subtotal", nullable = false)
    private double subtotal;
    
    @Basic(optional = false)
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    
    
    public ProdutoVenda() {
    }

    public ProdutoVenda(Produto produto_id, double subtotal, int quantidade) {
        this.produto_id = produto_id;
        this.subtotal = subtotal;
        this.quantidade = quantidade;
    }
    
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.produto_id);
        hash = 89 * hash + Objects.hashCode(this.venda_id);
        return hash;
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
        final ProdutoVenda other = (ProdutoVenda) obj;
        if (!Objects.equals(this.produto_id, other.produto_id)) {
            return false;
        }
        return Objects.equals(this.venda_id, other.venda_id);
    }

    public Produto getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Produto produto_id) {
        this.produto_id = produto_id;
    }

    public Venda getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(Venda venda_id) {
        this.venda_id = venda_id;
    }
}
