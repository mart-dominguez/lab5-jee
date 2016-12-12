/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ofa.jee7.lab05.entidades.Cliente;
import ofa.jee7.lab05.util.ProyectosEM;

/**
 *
 * @author mdominguez
 */
@ServiceJPA
@Transactional
public class ClienteServiceJPA implements ClienteService{

    @Inject @ProyectosEM
    private EntityManager em;
    
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
        this.em.persist(cli);
    }

    @Override
    public void actualizar(Cliente cli) {
        Cliente aux = this.em.merge(cli);
        this.em.flush();
    }

    @Override
    public void borrar(Integer id) {
        this.em.remove(this.em.find(Cliente.class, id));
    }

    @Override
    public Cliente buscar(Integer id) {
        return this.em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> listar() {
        return this.em.createQuery("SELECT c FROM Cliente c").getResultList();
    }
    
}
