/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Tarea;
import ofa.jee7.lab05.servicio.TareaService;
import ofa.jee7.lab05.util.MyCustomLogger;

/**
 *
 * @author mdominguez
 */
@Named(value = "altaTareaController")
@RequestScoped
public class AltaTareaBean {
    private Tarea tarea;
    
    @Inject @MyCustomLogger
    private transient Logger myLog;

    @Inject
    private TareaService tarSrv;
    
    @Inject ProyectoBean pryBean;
    
    @PostConstruct
    public void init(){
        myLog.log(Level.INFO,"Inicio alta tarea");
        this.tarea = new Tarea();
    }
    
    public void guardarTarea(){
        myLog.log(Level.INFO,"guarda tarea"+this.tarea);
        myLog.log(Level.INFO,"guarda tarea en proyecto"+this.pryBean.getProyecto());        
        this.tarSrv.asignarProyecto(pryBean.getProyecto().getId(), tarea);
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public TareaService getTarSrv() {
        return tarSrv;
    }

    public void setTarSrv(TareaService tarSrv) {
        this.tarSrv = tarSrv;
    }

    public ProyectoBean getPryBean() {
        return pryBean;
    }

    public void setPryBean(ProyectoBean pryBean) {
        this.pryBean = pryBean;
    }
    
    
    
    
}