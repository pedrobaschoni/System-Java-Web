package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.ProdutoVenda;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoVendaDAO {
    @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void inserir(ProdutoVenda produtoVenda) {
        em.persist(produtoVenda);
    }

    public ProdutoVenda findByCodigo(Integer id) {
        return em.find(ProdutoVenda.class, id);
    }

    public List<ProdutoVenda> buscarTodos() {
        return em.createQuery("SELECT pv FROM ProdutoVenda pv",
                ProdutoVenda.class)
                .getResultList();
    }
}
