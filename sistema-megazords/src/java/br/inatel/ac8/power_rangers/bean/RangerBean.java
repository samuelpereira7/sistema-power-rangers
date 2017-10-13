/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.power_rangers.bean;

import br.inatel.ac8.power_rangers.dao.RangerDAO;
import br.inatel.ac8.power_rangers.entidade.Ranger;
import br.inatel.ac8.power_rangers.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class RangerBean {
    private Ranger ranger = new Ranger();
    private List<Ranger> rangers = new ArrayList<>();
    private RangerDAO rangerDAO = new RangerDAO();

    public void adicionar(){
        try {
            rangers.add(ranger);
            rangerDAO.salvar(ranger);
            ranger = new Ranger();
            adicionarMensagem("Salvo!", "Ranger salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void listar(){
        try {
            rangers = rangerDAO.buscar();
            
            if(rangers == null || rangers.size() == 0)
            {
                adicionarMensagem("Nenhum dado encontrado!", "A busca nao retornou nenhum ranger.", FacesMessage.SEVERITY_WARN);                
            }
            
            //adicionarMensagem("Listado!", "Rangers listados com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Ranger c){
        ranger = c;
    }
    
    public void remover(Ranger c){
        try {
            rangerDAO.remover(c);
            adicionarMensagem("Removido!", "Ranger removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }
    
    public Ranger getRanger() {
        return ranger;
    }

    public void setRanger(Ranger ranger) {
        this.ranger = ranger;
    }

    public List<Ranger> getRangers() {
        return rangers;
    }

    public void setRangers(List<Ranger> rangers) {
        this.rangers = rangers;
    }
}