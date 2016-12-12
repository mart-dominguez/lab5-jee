/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.model;

import ofa.jee7.lab05.entidades.Cliente;
import ofa.jee7.lab05.servicio.ClienteService;
import ofa.jee7.lab05.servicio.ClienteServiceMemoryImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import ofa.jee7.lab05.servicio.ClienteServiceJDBC;
import ofa.jee7.lab05.servicio.ServiceJPA;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author mdominguez
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteBean implements Serializable{
    
    @Inject 
    @ServiceJPA
    //@ClienteServiceJDBC
    private ClienteService cliService;
    private Cliente clienteSeleccionado;
    
    @PostConstruct
    public void init(){
       // AHORA LO INYECTAMOS!!!
       // cliService= new ClienteServiceMemoryImpl();
    }
    
    public void guardar(){
        if(clienteSeleccionado.getId()!=null && clienteSeleccionado.getId()>0){
            cliService.actualizar(clienteSeleccionado);
        }else{
            cliService.crear(clienteSeleccionado);
        }
        clienteSeleccionado = null;
    }
    
    public void nuevo(){
        clienteSeleccionado= new Cliente();
    }
    
        
    public void borrar(){
        cliService.borrar(clienteSeleccionado.getId());
        clienteSeleccionado = null;
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Cliente seleccionado", ((Cliente) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Cliente deseleccionado", ((Cliente) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
    
    public List<Cliente> getListaClientes(){
        return cliService.listar();
    }
     
    
}
