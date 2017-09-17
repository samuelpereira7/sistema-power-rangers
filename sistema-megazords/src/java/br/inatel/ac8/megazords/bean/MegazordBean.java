
package br.inatel.ac8.megazords.bean;

import br.inatel.ac8.megazords.dao.MegazordDAO;
import br.inatel.ac8.megazords.entidade.Megazord;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MegazordBean {
    private Megazord megazord = new Megazord();
    private List<Megazord> megazords = new ArrayList<>();
    private MegazordDAO megazordDAO = new MegazordDAO();

    public void adicionar(){
        megazords.add(megazord);
        megazordDAO.salvar(megazord);
        megazord = new Megazord();
    }
    
    public void listar(){
        megazords = megazordDAO.buscar();
    }
    
    public void editar(Megazord c){
        megazord = c;
    }
    
    public void remover(Megazord c){
        megazordDAO.remover(c);
    }
    
    public Megazord getMegazord() {
        return megazord;
    }

    public void setMegazord(Megazord megazord) {
        this.megazord = megazord;
    }

    public List<Megazord> getMegazords() {
        return megazords;
    }

    public void setMegazords(List<Megazord> megazords) {
        this.megazords = megazords;
    }
    
}
