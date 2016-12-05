/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Proyecto;
import ofa.jee7.lab05.servicio.ProyectoService;
import ofa.jee7.lab05.util.MyCustomLogger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author mdominguez
 */
@Named(value = "proyectoController")
@SessionScoped
public class ProyectoBean implements Serializable{
    private Proyecto proyecto;
    
    private Integer idClienteSeleccionado;

    @Inject @MyCustomLogger
    private transient Logger myLog;

    public Logger getMyLog() {
        return myLog;
    }

    public void setMyLog(Logger myLog) {
        this.myLog = myLog;
    }
    
    @Inject
    private ProyectoService pryService;

    @PostConstruct
    public void init(){     
        myLog.log(Level.INFO, "hola");
        
    }
    
    public String nuevoProyecto(){
        myLog.log(Level.INFO, "nuevo proyecto");
        this.proyecto = new Proyecto();
        return null;
    }

    public String guardarProyecto(){
        myLog.log(Level.INFO, "guardarProyecto");
        myLog.log(Level.INFO, this.proyecto.toString());
        if(this.proyecto.getId()==null || this.proyecto.getId()==0){
            this.pryService.crear(proyecto);
        }else{
            this.pryService.actualizar(proyecto);
        }
        this.proyecto = null;
        return null;
    }    
    
    public String borrarProyecto(){
        myLog.log(Level.INFO, "borrarProyecto");        
        if(this.proyecto != null && this.proyecto.getId()>=0){
            this.pryService.borrar(proyecto.getId());
        }
        return null;
    }    

     public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Proyecto seleccionado", ((Proyecto) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Proyecto NO seleccionado", ((Proyecto) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Integer getIdClienteSeleccionado() {
        return idClienteSeleccionado;
    }

    public void setIdClienteSeleccionado(Integer idClienteSeleccionado) {
        this.idClienteSeleccionado = idClienteSeleccionado;
    }
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public ProyectoService getPryService() {
        return pryService;
    }

    public void setPryService(ProyectoService pryService) {
        this.pryService = pryService;
    }
    
    
    
    public List<Proyecto> getListaProyectos(){
        return pryService.listar();
    }
}
