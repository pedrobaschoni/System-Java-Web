package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Produto;
import br.edu.ifsp.pep.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UsarioDAO {

    @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void inserir(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario findByCodigo(Integer id) {
        return em.find(Usuario.class, id);
    }

    public List<Usuario> buscarTodos() {
        return em.createQuery("SELECT u FROM usuario u",
                Usuario.class)
                .getResultList();
    }

    public Usuario buscar(String login, String senha) {

        Usuario usuario = null;
        try {
            usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.login =:login and u.senha =:senha",
                    Usuario.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();

            return usuario;
            
        } catch (Exception e) {
            return null;
        }
    }
}
