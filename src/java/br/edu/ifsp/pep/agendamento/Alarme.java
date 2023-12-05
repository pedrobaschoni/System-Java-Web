package br.edu.ifsp.pep.agendamento;

import br.edu.ifsp.pep.dao.VendaDAO;
import br.edu.ifsp.pep.model.Venda;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class Alarme {
    
    @EJB
    private VendaDAO vendaDAO;
    
    private List<Venda> vendas = new ArrayList<>();
    
    private void carregar(){
        vendas = vendaDAO.buscarTodos();
    }
    
    @PostConstruct
    public void init(){
        
    }
    
    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void avisa(){
        vendas = null;
        carregar();
        double total = 0;
        
        for (Venda i : vendas) {
            total += Double.parseDouble( i.getTotal());
        }   
        System.out.println("o total das vendas foram de: " + total);
    }
}
