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
import javax.security.auth.callback.ConfirmationCallback;
import org.andypolanco.system.service.UserService;
import org.andypolanco.system.service.UserStatus;
import org.andypolanco.system.utils.AlertInformation;
import org.andypolanco.system.utils.Validations;
import org.andypolanco.system.utils.ViewFactory;

/**
 *
 * @author informatica
 */
public class RegisterUserController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private TextField pwdConfirmPassword;
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onCancel(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

    @FXML
    public void onCreateUser(MouseEvent event) {
        boolean isValidEmail = validate.validateEmail(
                txtEmail.getText().trim());
        if (isValidEmail == false) {
            alertInfo.viewAlert("ERROR", "ERROR EMAIL", "ERROR DE CAMPO", "HAS INGRESADO UN EMAIL INCORRECTO");
        }

        String user, name, lastName, email, password, confirmPassword;
        user = txtUser.getText().trim();
        name = txtName.getText().trim();
        lastName = txtLastName.getText().trim();
        password = pwdPassword.getText().trim();
        email = txtEmail.getText().trim();
        confirmPassword = pwdConfirmPassword.getText().trim();

        if (validate.emptyText(user) == true
                || validate.emptyText(name) == true
                || validate.emptyText(lastName) == true
                || validate.emptyText(email) == true
                || validate.emptyText(password) == true
                || validate.emptyText(confirmPassword) == true) {

            alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS", "ERROR DE CAMPO", "DEJO CAMPOS VACIOS EN EL FORMULARIO");
            return;
        }

        String msgField = "";
        if (validate.validateLengthText(user, 25) == false) {
            msgField = "El campo usuario es mayor a 25 caracteres";
        }
        if (validate.validateLengthText(name, 50) == false) {
            msgField = "El campo de Nombres es mayor a 50 caracteres";
        }
        if (validate.validateLengthText(lastName, 50) == false) {
            msgField = "El campo de Apellidos es mayor a 50 caracteres";
        }
        if (validate.validateLengthText(email, 50) == false) {
            msgField = "El campo de Email es mayor a 50 caracteres";
        }
        if (validate.validateLengthText(password, 50) == false) {
            msgField = "El campo de Password es mayor a 50 caracteres";
        }
        if (msgField.isEmpty() == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPO", "ERROR", msgField);
            return;
        }

        if (validate.equalsText(password, confirmPassword) == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE CONTRASEÑA", "ERROR", "SUS CONTRASEÑAS NO COINCIDEN");
            return;
        }
        UserStatus status
                = userService.createUser(user, name, lastName, email, password);
        switch (status) {
            case UserStatus.ERROR_USER_CREATED ->
                System.out.println("Error al crear en ctrl");
            case UserStatus.USER_CREATED ->
                System.out.println("SI se creo el usuario");
            case UserStatus.FIELDS_EMPTY ->
                System.out.println("Los campos no estan vacios");
            case UserStatus.VALUE_LENGTH_INVALID ->
                System.out.println("Validar longitud de texto");
            default-> System.out.println("Error desconocido");

        }

    }

}
