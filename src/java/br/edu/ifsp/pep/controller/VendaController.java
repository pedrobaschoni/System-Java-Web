package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.model.Produto;
import br.edu.ifsp.pep.model.ProdutoVenda;
import br.edu.ifsp.pep.util.Util;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class VendaController implements Serializable {

    private Produto produtoSelecionado = new Produto();

    private List<ProdutoVenda> produtoVendas = new ArrayList<>();

    private int quantidadeCompra;
    
    public void adicionar() {
        if (quantidadeCompra <= 0) {
            Util.addMessageWarning("quantidade tem que ser no minimo 1");
        } else {

            double subtotal = produtoSelecionado.getPreco() * quantidadeCompra;

            boolean entrol = false;

            for (ProdutoVenda i : produtoVendas) {

                if (i.getProduto_id().equals(produtoSelecionado)) {
                    i.setQuantidade(i.getQuantidade() + quantidadeCompra);
                    i.setSubtotal(i.getSubtotal() + subtotal);
                    entrol = true;
                }
            }
            if (!entrol) {
                ProdutoVenda produtoVenda = new ProdutoVenda(produtoSelecionado, subtotal, quantidadeCompra);
                produtoVendas.add(produtoVenda);
            }

        }
    }
    
    public double calcularTotal(){
        
        double total = 0;
        
        for (ProdutoVenda produtoVenda : produtoVendas) {
            total += produtoVenda.getSubtotal();
        }
        
        return total;
    }

    public String pagamento() {
        if (produtoVendas.isEmpty()) {
            Util.addMessageWarning("para ir para o pagamento deve ser selecioda ao menos um produto");
            return null;
        } else {
            return "/pagamento";
        }
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<ProdutoVenda> getProdutoVendas() {
        return produtoVendas;
    }

    public void setProdutoVendas(List<ProdutoVenda> produtoVendas) {
        this.produtoVendas = produtoVendas;
    }

    public int getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(int quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }
}
