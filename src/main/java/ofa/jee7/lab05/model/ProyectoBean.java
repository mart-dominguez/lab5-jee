/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Proyecto;
import ofa.jee7.lab05.servicio.ProyectoService;

/**
 *
 * @author mdominguez
 */
@Named(value = "proyectoController")
@RequestScoped
public class ProyectoBean {
    private Proyecto proyecto;
    
    @Inject
    private ProyectoService pryService;
    
    
}
