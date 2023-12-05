package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.ProdutoDAO;
import br.edu.ifsp.pep.dao.ProdutoVendaDAO;
import br.edu.ifsp.pep.dao.VendaDAO;
import br.edu.ifsp.pep.model.Cliente;
import br.edu.ifsp.pep.model.Produto;
import br.edu.ifsp.pep.model.ProdutoVenda;
import br.edu.ifsp.pep.model.Venda;
import br.edu.ifsp.pep.util.Util;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class PagamentoController implements Serializable {

    @EJB
    private ProdutoVendaDAO produtoVendaDAO;

    @EJB
    private VendaDAO vendaDAO;
    
    @EJB
    private ProdutoDAO produtoDAO;

    @Inject
    private VendaController venda;

    @Inject
    UsuarioController usuario;

    private List<ProdutoVenda> produtosVenda = new ArrayList<>();

    public PagamentoController() {
    }

    boolean entrou = false;

    @PostConstruct
    private void colocar() {
        setProdutosVenda(venda.getProdutoVendas());
    }

    public String finalizarVenda() {

        for (ProdutoVenda i : produtosVenda) {
            if (i.getQuantidade() > Integer.parseInt(venda.getProdutoSelecionado().getQuantidadeEstoque())) {
                entrou = true;
            }
        }
        if (!entrou) {

            double total = 0;

            for (ProdutoVenda i : produtosVenda) {
                total += i.getSubtotal();
            }

            Cliente cliente = new Cliente(1, "Felipe");

            Venda venda = new Venda(Double.toString(total), new Date(), cliente, usuario.getUsuarioAutenticado(), produtosVenda);

            vendaDAO.inserir(venda);
            
            for (ProdutoVenda i : produtosVenda) {
                Produto produto = i.getProduto_id();
                produto.setQuantidadeEstoque(String.valueOf(Integer.parseInt(produto.getQuantidadeEstoque()) - i.getQuantidade()));
                produtoDAO.atualizar(produto);
            }
            
            this.venda.getProdutoVendas().removeAll(this.venda.getProdutoVendas());

            return "/venda";

        } else {
            Util.addMessageWarning("um produto foi selecionado uma qunatidade maior od que há em estouqe");
            return null;
        }
    }

    public String continuarComprando() {
        return "/venda";
    }

    public String cancelarVenda() {
        venda.getProdutoVendas().removeAll(venda.getProdutoVendas());
        return "/venda";
    }

    public List<ProdutoVenda> getProdutosVenda() {
        return produtosVenda;
    }

    public void setProdutosVenda(List<ProdutoVenda> produtosVenda) {
        this.produtosVenda = produtosVenda;
    }

}
