/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.util;

import java.sql.*;

/**
 *
 * @author josue
 */
public class Conexion {
    private Connection conexion = null;
    private Statement s=null;
    private ResultSet rs=null;
    private String ingresoempleados="";
    
    public Conexion() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost/EstudiantesArrupe","root",""
            );
            s = conexion.createStatement();
            System.out.println("Conexion exitosa");
        }
        catch (ClassNotFoundException e1){
            System.out.println("Error:No encuentro el driver"+ e1.getMessage());
        }
    }
    public ResultSet getRs() {
        return this.rs;
    }
    public void setRs(String sql){
        try{
            this.rs = s.executeQuery(sql);
        }
        catch(SQLException e2){
            System.out.println("Error: Fallo en SQL" + e2.getMessage());
        }
    }
    
    public void setQuery(String sql) throws SQLException{
        this.s.executeUpdate(sql);
    }
    public void cerrarConexion() throws SQLException{
        conexion.close();
    }
}
