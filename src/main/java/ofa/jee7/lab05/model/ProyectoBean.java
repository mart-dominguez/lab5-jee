/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Cliente;
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
    
    private Integer idClienteSeleccionado;

    
    @Inject
    private ProyectoService pryService;

    
    public void init(){
        
    }
    public String nuevoProyecto(){
        this.proyecto = new Proyecto();
        return null;
    }

    public String guardarProyecto(){
        if(this.proyecto.getId()==null || this.proyecto.getId()==0){
            this.pryService.crear(proyecto);
        }else{
            this.pryService.actualizar(proyecto);
        }
        return null;
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
