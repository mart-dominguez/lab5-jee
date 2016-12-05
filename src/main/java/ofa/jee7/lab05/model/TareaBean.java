/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Tarea;

/**
 *
 * @author mdominguez
 */
@Named(value = "tareaController")
@RequestScoped
public class TareaBean {
    private Tarea tarea;

    @PostConstruct
    public void init(){
        this.tarea = new Tarea();
    }
    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
    
}
