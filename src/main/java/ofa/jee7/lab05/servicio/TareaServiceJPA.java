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
import ofa.jee7.lab05.entidades.Proyecto;
import ofa.jee7.lab05.entidades.Tarea;
import ofa.jee7.lab05.util.ProyectosEM;

/**
 *
 * @author mdominguez
 */
@ApplicationScoped
@ServiceJPA
@Transactional
public class TareaServiceJPA implements TareaService{
    @Inject @ProyectosEM
    private EntityManager em;

    @Override
    public void borrar(Integer id) {
        this.em.remove(this.em.find(Tarea.class, id));
    }

    @Override
    public void actualizar(Tarea tarea) {
        this.em.merge(tarea);
        this.em.flush();
    }

    @Override
    public Tarea buscar(Integer id) {
        return this.em.find(Tarea.class, id);
    }

    @Override
    public List<Tarea> listarTodas() {
        return this.em.createQuery("SELECT t FROM Tarea t").getResultList();
    }

    @Override
    public void asignarProyecto(Integer idProyecto, Tarea tarea) {
        Proyecto p = this.em.find(Proyecto.class, idProyecto);
        tarea.setProyecto(p);
        if(!(tarea.getId()!=null &&tarea.getId()>0)) {
            this.em.persist(tarea);
        }else{
            this.em.merge(tarea);
        }
    }
    
    
}
