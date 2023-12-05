
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.UsarioDAO;
import br.edu.ifsp.pep.model.Usuario;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UsuarioController implements Serializable{
    
    @EJB
    private UsarioDAO usuarioDAO;
    
    private Usuario usuario = new Usuario();
    
    private Usuario usuarioAutenticado;

    public String login(){
        if(usuario.getLogin().isBlank() && usuario.getSenha().isBlank()){
            Util.addMessageWarning("prenencha todos os campos");
        }else{
            usuarioAutenticado = usuarioDAO.buscar(usuario.getLogin(), usuario.getSenha());
            if(usuarioAutenticado == null){
                Util.addMessageWarning("senha ou login invalidos");

            }else{
                return "/inicio";
            }
        }
        usuario = new Usuario();
        return null;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }    
}
