/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import ofa.jee7.lab05.entidades.Cliente;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface ClienteService {
    public void crear(String nombre,String cuit,String email);
    public void crear(Cliente cli);
    public void actualizar(Cliente cli);
    public void borrar(Integer id);
    public Cliente buscar(Integer id);
    public List<Cliente> listar();
    
}
