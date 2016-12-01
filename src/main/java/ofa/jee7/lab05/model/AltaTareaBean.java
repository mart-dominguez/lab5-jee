/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Tarea;
import ofa.jee7.lab05.servicio.ProyectoService;
import ofa.jee7.lab05.servicio.TareaService;

/**
 *
 * @author mdominguez
 */
@Named(value = "altaTareaController")
@RequestScoped
public class AltaTareaBean {
    private Tarea tarea;
    
    @Inject
    private TareaService tarSrv;
    
    @Inject ProyectoBean pryBean;
    
    @PostConstruct
    public void init(){
        this.tarea = new Tarea();
    }
    
    public void guardarTarea(){
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