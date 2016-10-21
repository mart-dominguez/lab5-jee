/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martin.lab05.servicio;

import com.martin.lab05.entidades.Proyecto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Esta implementación del servicio de gestión de la entidad cliente
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
    public void crear(Proyecto cli) {
        if(!(cli.getId()!=null &&cli.getId()>0)) cli.setId(_GENERADOR_ID.getAndIncrement());
        this.listaProyectos.add(cli);
    }

    @Override
    public void actualizar(Proyecto cli) {
        this.borrar(cli.getId());
        this.crear(cli);
    }

    @Override
    public void borrar(Integer id) {
        Proyecto cli = new Proyecto();
        cli.setId(id);
        this.listaProyectos.remove(cli);
    }

    @Override
    public Proyecto buscar(Integer id) {
        Proyecto cliBuscar = new Proyecto();
        cliBuscar.setId(id);
        return this.listaProyectos.get(this.listaProyectos.indexOf(cliBuscar));
    }

    @Override
    public List<Proyecto> listar() {
        return this.listaProyectos;
    }
}
