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
        if (validate.validateLengthText(user, 25)
                || validate.validateLengthText(name, 50)
                || validate.validateLengthText(lastName, 50)
                || validate.validateLengthText(email, 50)
                || validate.validateLengthText(email, 50)
                || validate.validateLengthText(password, 50)) {

            return;
        }
    

    }
   
    
}
