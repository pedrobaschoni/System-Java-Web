/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findById", query = "SELECT v FROM Venda v WHERE v.id = :id"),
    @NamedQuery(name = "Venda.findByTotal", query = "SELECT v FROM Venda v WHERE v.total = :total"),
    @NamedQuery(name = "Venda.findByDataVenda", query = "SELECT v FROM Venda v WHERE v.dataVenda = :dataVenda")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "total", nullable = false, length = 45)
    private String total;
    
    @Basic(optional = false)
    @Column(name = "data_venda", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cliente clienteId;
    
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda_id")
    private List<ProdutoVenda> produtoVendaList;

    public Venda() {
    }

    public Venda(Integer id) {
        this.id = id;
    }

    public Venda(String total, Date dataVenda, Cliente clienteId, Usuario usuarioId, List<ProdutoVenda> produtoVendaList) {
        this.total = total;
        this.dataVenda = dataVenda;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.produtoVendaList = produtoVendaList;
        for (ProdutoVenda produtoVenda : produtoVendaList){
            produtoVenda.setVenda_id(this);
        }
    }
    
    public Venda(Integer id, String total, Date dataVenda) {
        this.id = id;
        this.total = total;
        this.dataVenda = dataVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @XmlTransient
    public List<ProdutoVenda> getProdutoVendaList() {
        return produtoVendaList;
    }

    public void setProdutoVendaList(List<ProdutoVenda> produtoVendaList) {
        for (ProdutoVenda produtoVenda : produtoVendaList){
            produtoVenda.setVenda_id(this);
        }
        this.produtoVendaList = produtoVendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifsp.pep.model.Venda[ id=" + id + " ]";
    }
    
}
