/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import ofa.jee7.lab05.entidades.Tarea;

/**
 *
 * @author mdominguez
 */
@Named
@ApplicationScoped
public interface TareaService {

    public void borrar(Integer id);
    public void actualizar(Tarea tarea);
    public Tarea buscar(Integer id);
    public List<Tarea> listarTodas();
    public void asignarProyecto(Integer idProyecto,Tarea tarea);

    
}
