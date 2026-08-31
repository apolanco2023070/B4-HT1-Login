/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.andypolanco.system.service;

import org.andypolanco.system.model.User;
import org.andypolanco.system.repository.UserRepository;
import org.andypolanco.system.utils.AlertInformation;
import org.andypolanco.system.utils.Validations;

public class UserService {

    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserRepository userRepo = new UserRepository();

    public UserStatus createUser(String user, String name, String lastName,
            String email, String password) {
        if (validate.emptyText(user) == true
                || validate.emptyText(name) == true
                || validate.emptyText(lastName) == true
                || validate.emptyText(email) == true
                || validate.emptyText(password) == true) {

            alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS", "ERROR DE CAMPO", "DEJO CAMPOS VACIOS EN EL FORMULARIO");
            return UserStatus.FIELDS_EMPTY;
        }

        if (userRepo.findByUserOrEmail(user) != null) {
            alertInfo.viewAlert("ERROR", "USUARIO EXISTENTE", "ERROR DE CAMPO", "Ya existe una cuenta con ese nombre de usuario");
            return UserStatus.USER_ALREADY_EXISTS;
        }

        if (userRepo.findByUserOrEmail(email) != null) {
            alertInfo.viewAlert("ERROR", "CORREO EXISTENTE", "ERROR DE CAMPO", "Ya existe una cuenta con ese correo");
            return UserStatus.EMAIL_ALREADY_EXISTS;
        }

        try {
            User newUser = new User(password, email, name, lastName, user);
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
        } catch (Exception e) {
            return UserStatus.ERROR_USER_CREATED;
        }

    }

    public User findUser(String userOrEmail) {
        return userRepo.findByUserOrEmail(userOrEmail);
    }

    public boolean existsUser(String userOrEmail) {
        return findUser(userOrEmail) != null;
    }
}
