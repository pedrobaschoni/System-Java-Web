package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.ProdutoDAO;
import br.edu.ifsp.pep.model.Produto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProdutoController implements Serializable{
    
    @EJB
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    List<Produto> produtos = new ArrayList<>();    
    
    @PostConstruct
    private void carregar(){
        produtos = produtoDAO.buscarTodos();
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
