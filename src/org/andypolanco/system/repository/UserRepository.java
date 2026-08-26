/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.andypolanco.system.repository;

import org.andypolanco.system.model.User;
import java.sql.CallableStatement;
import java.sql.SQLException;
import org.andypolanco.system.config.ConexionDB;

/**
 *
 * @author informatica
 */
public class UserRepository implements UserInterface {

    //CallableStatement
    private CallableStatement callSP;
    //Conexion DB
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
    
    public UserRepository (){
        
    }
    
    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection()
                     .prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1,user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            
            callSP.execute();
            
            callSP.close();//liberar los recursos utilizados
            
        } catch (Exception e) {
            System.out.println("Error al crear el usuario");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
