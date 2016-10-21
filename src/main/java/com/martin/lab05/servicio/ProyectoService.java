/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martin.lab05.servicio;

import com.martin.lab05.entidades.Proyecto;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface ProyectoService {
    public void crear(Proyecto cli);
    public void actualizar(Proyecto cli);
    public void borrar(Integer id);
    public Proyecto buscar(Integer id);
    public List<Proyecto> listar();
    
}
