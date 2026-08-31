/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.andypolanco.system.service;


import org.andypolanco.system.model.User;
import org.andypolanco.system.repository.AuthenticationInterface;
import org.andypolanco.system.repository.AuthenticationRepository;


/**
 *
 * @author informatica
 */
public class AuthenticationService {

    private AuthenticationInterface authRepo = new AuthenticationRepository();
    private UserService userService = new UserService();

    public AuthenticationService() {

    }


    public AuthenticationStatus login(String userOrEmail, String password) {
        boolean userExists = userService.existsUser(userOrEmail);

        if (userExists == false) {
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        User user = authRepo.login(userOrEmail, password);

        if (user == null) {
            //la cuenta existe, pero la contrasena no coincidio
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        return AuthenticationStatus.LOGIN_SUCCESS;
    }
}