package br.edu.ifsp.pep.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;


public class ProdutoVendaPK implements Serializable {


    private int venda_id;

    private int produto_id;

    public ProdutoVendaPK() {
    }

    public ProdutoVendaPK(int vendaId, int produtoId) {
        this.venda_id = vendaId;
        this.produto_id = produtoId;
    }

    public int getVendaId() {
        return venda_id;
    }

    public void setVendaId(int vendaId) {
        this.venda_id = vendaId;
    }

    public int getProdutoId() {
        return produto_id;
    }

    public void setProdutoId(int produtoId) {
        this.produto_id = produtoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) venda_id;
        hash += (int) produto_id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoVendaPK)) {
            return false;
        }
        ProdutoVendaPK other = (ProdutoVendaPK) object;
        if (this.venda_id != other.venda_id) {
            return false;
        }
        if (this.produto_id != other.produto_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifsp.pep.model.ProdutoVendaPK[ vendaId=" + venda_id + ", produtoId=" + produto_id + " ]";
    }
    
}
