/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.andypolanco.system.repository;

import org.andypolanco.system.model.User;



public interface UserInterface {
    void create(User user);
    User findByUserOrEmail(String userOrEmail);
}