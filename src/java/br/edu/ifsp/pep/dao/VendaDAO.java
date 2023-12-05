package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Venda;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class VendaDAO {
    @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void inserir(Venda venda) {
        em.persist(venda);
    }

    public Venda findByCodigo(Integer id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> buscarTodos() {
        return em.createQuery("SELECT v FROM Venda v",
                Venda.class)
                .getResultList();
    }
}
