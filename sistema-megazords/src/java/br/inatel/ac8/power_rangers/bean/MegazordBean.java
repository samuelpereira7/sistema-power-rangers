
package br.inatel.ac8.power_rangers.bean;

import br.inatel.ac8.power_rangers.dao.MegazordDAO;
import br.inatel.ac8.power_rangers.entidade.Megazord;
import br.inatel.ac8.power_rangers.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MegazordBean {
    private Megazord megazord = new Megazord();
    private List<Megazord> megazords = new ArrayList<>();
    private MegazordDAO megazordDAO = new MegazordDAO();

    public void adicionar(){
        try {
            megazords.add(megazord);
            megazordDAO.salvar(megazord);
            megazord = new Megazord();
            adicionarMensagem("Salvo!", "Megazord salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            //Logger.getLogger(MegazordBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void listar(){
        try {
            megazords = megazordDAO.buscar();
            
            if(megazords == null || megazords.size() == 0)
            {
                adicionarMensagem("Nenhum dado encontrado!", "A busca nao retornou nenhum megazord.", FacesMessage.SEVERITY_WARN);                
            }
            
            //adicionarMensagem("Listado!", "Megazords listados com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Megazord c){
        megazord = c;
    }
    
    public void remover(Megazord c){
        try {
            megazordDAO.remover(c);
            adicionarMensagem("Removido!", "Megazord removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
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
