/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ofa.jee7.lab05.entidades.Cliente;
import ofa.jee7.lab05.entidades.Proyecto;
import ofa.jee7.lab05.util.ProyectosEM;

/**
 *
 * @author mdominguez
 */
@ServiceJPA
@ApplicationScoped
@Transactional
public class ProyectoServiceJPA implements ProyectoService{

    @Inject @ProyectosEM
    private EntityManager em;
       
    @Override
    public void crear(Proyecto pry) {
        this.em.persist(pry);
    }

    @Override
    public void actualizar(Proyecto pry) {
       Proyecto aux = this.em.merge(pry);
        this.em.flush();    
    }

    @Override
    public void borrar(Integer id) {
        this.em.remove(this.em.find(Proyecto.class, id));
    }

    @Override
    public Proyecto buscar(Integer id) {
        return this.em.find(Proyecto.class, id);
    }

    @Override
    public List<Proyecto> listar() {
        return this.em.createQuery("SELECT p FROM Proyecto p").getResultList();
    }
    
}
