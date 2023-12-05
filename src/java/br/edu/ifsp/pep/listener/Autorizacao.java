package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.UsuarioController;
import br.edu.ifsp.pep.model.Usuario;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;

public class Autorizacao implements PhaseListener {

    @Inject
    private UsuarioController usuarioController;

    @Override
    public void afterPhase(PhaseEvent event) {
        
        FacesContext ctx = event.getFacesContext();

        String pagina = ctx.getViewRoot().getViewId();

        Usuario usuarioAutenticada = usuarioController.getUsuarioAutenticado();

        if ((usuarioAutenticada == null || !usuarioAutenticada.getNivel().equals("gerente")) && pagina.startsWith("/gerente/")) {

            NavigationHandler nh = ctx.getApplication().getNavigationHandler();

            nh.handleNavigation(ctx, null, "erro");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;

    }

}
