/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import ofa.jee7.lab05.entidades.Proyecto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Esta implementación del servicio de gestión de la entidad  proyecto
 * guarda los datos en una lista de memoria pero no en una base de datos.
 * 
 * @author mdominguez
 */
public class ProyectoServiceMemoryImpl implements ProyectoService{
    
    private List<Proyecto> listaProyectos;
    private static final AtomicInteger _GENERADOR_ID = new AtomicInteger(0); 

    public ProyectoServiceMemoryImpl(){
        this.listaProyectos = new ArrayList<>();
    }


    @Override
    public void crear(Proyecto pry) {
        if(!(pry.getId()!=null &&pry.getId()>0)) pry.setId(_GENERADOR_ID.getAndIncrement());
        this.listaProyectos.add(pry);
    }

    @Override
    public void actualizar(Proyecto pry) {
        this.borrar(pry.getId());
        this.crear(pry);
    }

    @Override
    public void borrar(Integer id) {
        Proyecto pry = new Proyecto();
        pry.setId(id);
        this.listaProyectos.remove(pry);
    }

    @Override
    public Proyecto buscar(Integer id) {
        Proyecto pryBuscar = new Proyecto();
        pryBuscar.setId(id);
        return this.listaProyectos.get(this.listaProyectos.indexOf(pryBuscar));
    }

    @Override
    public List<Proyecto> listar() {
        return this.listaProyectos;
    }
}
