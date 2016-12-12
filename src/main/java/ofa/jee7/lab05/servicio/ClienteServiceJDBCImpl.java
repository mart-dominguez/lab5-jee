/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ofa.jee7.lab05.servicio;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ofa.jee7.lab05.entidades.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import ofa.jee7.lab05.util.ProyectosDB;

/**
 * Esta implementación del servicio de gestión de la entidad cliente
 * guarda los datos en una base de datos.
 * 
 * @author mdominguez
 */
@ApplicationScoped
@ClienteServiceJDBC
public class ClienteServiceJDBCImpl implements ClienteService, Serializable{
    
    @Inject @ProyectosDB
    DataSource ds;

    private final String _NOMBRE_TABLA = "clientes";

    @Override
    public void crear(String nombre, String cuit, String email) {
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setCuit(cuit);
        cli.setCorreoElectronico(email);
        this.crear(cli);
    }

    @Override
    public void crear(Cliente cli) {
        String sql = "INSERT INTO "+_NOMBRE_TABLA+" (NOMBRE,CUIT,CORREO_ELECTRONICO) VALUES (?,?,?)";
        Connection conn=null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pstm= conn.prepareStatement(sql);
            pstm.setString(1, cli.getNombre());
            pstm.setString(2, cli.getCorreoElectronico());
            pstm.setString(3, cli.getCuit());
            pstm.executeUpdate();           
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!= null) try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void actualizar(Cliente cli) {
        String sql = "UPDATE "+_NOMBRE_TABLA+" SET NOMBRE =? ,CUIT=?,CORREO_ELECTRONICO=? WHERE ID=?";
        Connection conn=null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pstm= conn.prepareStatement(sql);
            pstm.setString(1, cli.getNombre());
            pstm.setString(2, cli.getCorreoElectronico());
            pstm.setString(3, cli.getCuit());
            pstm.setInt(4, cli.getId());
            pstm.executeUpdate();           
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!= null) try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void borrar(Integer id) {
 String sql = "DELETE FROM "+_NOMBRE_TABLA+" WHERE ID=?";
        Connection conn=null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pstm= conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();           
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!= null) try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Cliente buscar(Integer id) {
        Cliente cli = new Cliente();
        String sql = "SELECT id,nombre,correo_electronico,cuit FROM "+_NOMBRE_TABLA+" WHERE ID=?";
        Connection conn=null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pstm= conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                cli.setId(rs.getInt("id"));
                cli.setCuit(rs.getString("cuit"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCorreoElectronico(rs.getString("correo_electronico"));
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!= null) try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cli;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listaCli=new ArrayList<>();
        String sql = "SELECT id,nombre,correo_electronico,cuit FROM "+_NOMBRE_TABLA+" ";
        Connection conn=null;
        try {
            conn = this.ds.getConnection();
            Statement st= conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setCuit(rs.getString("cuit"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCorreoElectronico(rs.getString("correo_electronico"));
                listaCli.add(cli);
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!= null) try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCli;
    }

        public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

}
