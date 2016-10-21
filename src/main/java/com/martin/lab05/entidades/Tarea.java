/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martin.lab05.entidades;

import java.util.Objects;

/**
 *
 * @author mdominguez
 */
public class Tarea {
    private Integer id;
    private String nombre;
    private Boolean completada;
    private Integer horasPlanificadas;
    private Integer horasCompletadas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }

    public Integer getHorasPlanificadas() {
        return horasPlanificadas;
    }

    public void setHorasPlanificadas(Integer horasPlanificadas) {
        this.horasPlanificadas = horasPlanificadas;
    }

    public Integer getHorasCompletadas() {
        return horasCompletadas;
    }

    public void setHorasCompletadas(Integer horasCompletadas) {
        this.horasCompletadas = horasCompletadas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarea other = (Tarea) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

    
}
