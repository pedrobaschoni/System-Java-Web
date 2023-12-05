package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoDAO {
    @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void inserir(Produto produto) {
        em.persist(produto);
    }
    
    public void atualizar(Produto produto) {
        em.merge(produto);
    }

    public Produto findByCodigo(Integer id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        return em.createQuery("SELECT p FROM Produto p",
                Produto.class)
                .getResultList();
    }
}
