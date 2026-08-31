/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.andypolanco.system.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.andypolanco.system.config.ConexionDB;
import org.andypolanco.system.model.User;

/**
 *
 * @author informatica
 */
public class AuthenticationRepository implements AuthenticationInterface {

    private CallableStatement callSP;
    //Conexion DB
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    public AuthenticationRepository() {

    }

    @Override
    public User login(String email, String password) {
        User user = null;
        try {
            callSP = conexionDB.getConnection()
                    .prepareCall("{call sp_login(?,?)}");
            callSP.setString(1, email);
            callSP.setString(2, password);

            boolean hasResultSet = callSP.execute();
            if (hasResultSet) {
                try (ResultSet resultSet = callSP.getResultSet()) {
                    if (resultSet.next()) {
                        user = new User(
                                resultSet.getString("id_user"),
                                resultSet.getString("email"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getString("password"),
                                resultSet.getString("user")
                        );
                    }
                }
            }
            callSP.close();

        } catch (Exception e) {
            System.out.println("Error al iniciar sesion");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}
