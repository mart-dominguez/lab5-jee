/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import java.io.Serializable;
import ofa.jee7.lab05.entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;

/**
 * Esta implementación del servicio de gestión de la entidad cliente
 * guarda los datos en una lista de memoria pero no en una base de datos.
 * 
 * @author mdominguez
 */
@ApplicationScoped
public class ClienteServiceMemoryImpl implements ClienteService, Serializable{
    
    private List<Cliente> listaClientes;
    private static final AtomicInteger _GENERADOR_ID = new AtomicInteger(0); 

    public ClienteServiceMemoryImpl(){
        this.listaClientes = new ArrayList<>();
    }

    @Override
    public void crear(String nombre, String cuit, String email) {
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setCuit(cuit);
        cli.setCorreoElectronico(email);
        this.crear(cli);
    }

    @Override
    public void crear(Cliente cli) {
        if(!(cli.getId()!=null &&cli.getId()>0)) cli.setId(_GENERADOR_ID.getAndIncrement());
        this.listaClientes.add(cli);
    }

    @Override
    public void actualizar(Cliente cli) {
        this.borrar(cli.getId());
        this.crear(cli);
    }

    @Override
    public void borrar(Integer id) {
        Cliente cli = new Cliente();
        cli.setId(id);
        this.listaClientes.remove(cli);
    }

    @Override
    public Cliente buscar(Integer id) {
        Cliente cliBuscar = new Cliente();
        cliBuscar.setId(id);
        return this.listaClientes.get(this.listaClientes.indexOf(cliBuscar));
    }

    @Override
    public List<Cliente> listar() {
        return this.listaClientes;
    }
}
