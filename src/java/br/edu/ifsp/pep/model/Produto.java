/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id"),
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produto.findByPreco", query = "SELECT p FROM Produto p WHERE p.preco = :preco"),
    @NamedQuery(name = "Produto.findByQuantidadeEstoque", query = "SELECT p FROM Produto p WHERE p.quantidadeEstoque = :quantidadeEstoque")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    @Basic(optional = false)
    @Column(name = "preco", nullable = false)
    private double preco;
    @Basic(optional = false)
    @Column(name = "quantidade_estoque", nullable = false, length = 45)
    private String quantidadeEstoque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto_id")
    private List<ProdutoVenda> produtoVendaList;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, String nome, double preco, String quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(String quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @XmlTransient
    public List<ProdutoVenda> getProdutoVendaList() {
        return produtoVendaList;
    }

    public void setProdutoVendaList(List<ProdutoVenda> produtoVendaList) {
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifsp.pep.model.Produto[ id=" + id + " ]";
    }
    
}
