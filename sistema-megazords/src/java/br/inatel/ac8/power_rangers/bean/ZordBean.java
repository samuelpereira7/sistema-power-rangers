/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.power_rangers.bean;

import br.inatel.ac8.power_rangers.dao.ZordDAO;
import br.inatel.ac8.power_rangers.entidade.Zord;
import br.inatel.ac8.power_rangers.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author samuel
 */

@ManagedBean
@SessionScoped
public class ZordBean {
    private Zord zord = new Zord();
    private List<Zord> zords = new ArrayList<>();
    private ZordDAO zordDAO = new ZordDAO();
    
    public void adicionar(){
        try {
            zords.add(zord);
            zordDAO.salvar(zord);
            zord = new Zord();
            adicionarMensagem("Salvo!", "Zord salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     
     public void listar(){
        try {
            zords = zordDAO.buscar();
            
            if(zords == null || zords.size() == 0)
            {
                adicionarMensagem("Nenhum dado encontrado!", "A busca nao retornou nenhum zord.", FacesMessage.SEVERITY_WARN);                
            }
            
            //adicionarMensagem("Listado!", "Rangers listados com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     
     public void editar(Zord c){
        zord = c;
    }
    
    public void remover(Zord c){
        try {
            zordDAO.remover(c);
            adicionarMensagem("Removido!", "Zord removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }
    
    public Zord getZord() {
        return zord;
    }

    public void setZord(Zord zord) {
        this.zord = zord;
    }

    public List<Zord> getZords() {
        return zords;
    }

    public void setZords(List<Zord> zords) {
        this.zords = zords;
    }

}
