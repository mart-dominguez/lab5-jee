/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import ofa.jee7.lab05.entidades.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import ofa.jee7.lab05.entidades.Proyecto;
import ofa.jee7.lab05.entidades.Tarea;

/**
 * Esta implementación del servicio de gestión de la entidad  proyecto
 * guarda los datos en una lista de memoria pero no en una base de datos.
 * 
 * @author mdominguez
 */
@ApplicationScoped
public class TareaServiceMemoryImpl implements TareaService{
    
    @Inject 
    private ProyectoService pryServ;
    
    private List<Tarea> listaTareas;
    private static final AtomicInteger _GENERADOR_ID = new AtomicInteger(0); 

    public TareaServiceMemoryImpl(){
        this.listaTareas = new ArrayList<>();
    }

    
    @Override
    public void asignarProyecto(Integer idProyecto,Tarea tarea){        
        // como la tarea es nueva le asigno un ID    
        if(!(tarea.getId()!=null &&tarea.getId()>0)) tarea.setId(_GENERADOR_ID.getAndIncrement());
            
        Proyecto p = this.pryServ.buscar(idProyecto);
        tarea.setProyecto(p);
        if(p.getTareas()==null){
            p.setTareas(new ArrayList<Tarea>());
        }
        p.getTareas().add(tarea);
        // guardo el proyecto con la tarea agregada
        pryServ.actualizar(p);
        // agrego la tarea a la lista de tareas que gestiono
        this.listaTareas.add(tarea);
    }

    @Override
    public void actualizar(Tarea tarea) {
        // obtengo la referencia y la actualizo.
        Tarea t = this.buscar(tarea.getId());
        t.setNombre(tarea.getNombre());
        t.setCompletada(tarea.getCompletada());
        t.setHorasCompletadas(tarea.getHorasCompletadas());
        t.setHorasPlanificadas(tarea.getHorasPlanificadas());        
    }

 
    @Override
    public void borrar(Integer id) {
        Tarea t = new Tarea();
        t.setId(id);
        // busca una tarea que tenga el mismo ID y la borra
        this.listaTareas.remove(t);
    }

    @Override
    public Tarea buscar(Integer id) {
        Tarea t = new Tarea();
        t.setId(id);
        return this.listaTareas.get(this.listaTareas.indexOf(t));
    }

    @Override
    public List<Tarea> listarTodas() {
        return this.listaTareas;
    }
    
}
