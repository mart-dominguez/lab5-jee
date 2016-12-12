/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.util;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author martdominguez
 */
@ApplicationScoped
public class ProductorRecursos {
    
    @Produces @ProyectosDB
    @Resource(lookup ="jdbc/proyectos")
    DataSource proyectosDatabase;
    
    @Produces @ProyectosEM
    @PersistenceContext(unitName = "GESTION_PROYECTOS_PU")
    EntityManager em;
}
