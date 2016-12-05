/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.util;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

/**
 *
 * @author martdominguez
 */
@Named
@ApplicationScoped
public class MyLogger {
    
    @Produces @MyCustomLogger
    public Logger productorLogger(InjectionPoint p) {
        return Logger.getLogger(p.getClass().getCanonicalName());
    }
}
