/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.andypolanco.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.andypolanco.system.service.AuthenticationService;
import org.andypolanco.system.service.AuthenticationStatus;
import org.andypolanco.system.utils.AlertInformation;
import org.andypolanco.system.utils.Validations;
import org.andypolanco.system.utils.ViewFactory;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUserOrEmail;
    @FXML
    private PasswordField pwdPassword;

    private AuthenticationService authService = new AuthenticationService();
    private AlertInformation alertInfo = new AlertInformation();
    private Validations validate = new Validations();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onRegister(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewRegister();
    }

    @FXML
    public void onLogin(MouseEvent event) {
        String userOrEmail = txtUserOrEmail.getText().trim();
        String password = pwdPassword.getText().trim();

        if (validate.emptyText(userOrEmail) == true || validate.emptyText(password) == true) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS", "ERROR DE CAMPO",
                    "DEBES INGRESAR TU USUARIO/CORREO Y TU CONTRASEÑA");
            return;
        }

        AuthenticationStatus status = authService.login(userOrEmail, password);

        switch (status) {
            case NOT_EXIST_USER ->
                alertInfo.viewAlert("ERROR", "CUENTA NO ENCONTRADA", "USUARIO NO EXISTE",
                        "No existe una cuenta con ese usuario o correo, o la contraseña es incorrecta.\nDebes registrarte.");
            case LOGIN_SUCCESS -> {
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewDashboard();
            }
        }
    }

}
