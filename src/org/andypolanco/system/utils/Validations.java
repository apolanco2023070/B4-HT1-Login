/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.andypolanco.system.utils;

/**
 *
 * @author informatica
 */
public class Validations {

    public Validations() {

    }

    public Boolean equalsText(String textOriginal, String textCompare) {
        return textOriginal.equals(textCompare);
    }

    public Boolean emptyText(String text) {
        boolean isEmpty = false;
        if (text.isEmpty() || text.isBlank()) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public Boolean validateLengthText(String text, int lengthMax) {
        return text.length() <= lengthMax;
    }

    public Boolean validateEmail(String email) {
          int dotCount = 0;
    int arrobeCount = 0;
    
    // VALIDA LA EXISTENCIA DE PUNTOS CONSECUTIVOS Y CUENTA LOS PUNTOS
    for (int index = 0; index < email.length(); index++) {
        if (email.charAt(index) == '.') {
            dotCount++;
            // Validación real de puntos consecutivos (ej: "test..@mail.com")
            if (index > 0 && email.charAt(index - 1) == '.') {
                return false;
            }
        }
    }
    // La validación del conteo se evalúa AL FINAL del bucle, no dentro
    if (dotCount == 0) {
        return false;
    }

    // VALIDA LA EXISTENCIA DE SOLO UN ÚNICO ARROBA
    for (int index = 0; index < email.length(); index++) {
        if (email.charAt(index) == '@') {
            arrobeCount++;
        }
    }
    // La validación del conteo se evalúa AL FINAL del bucle
    if (arrobeCount > 1 || arrobeCount == 0) {
        return false;
    }

    return true;
}
}
