/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.User;

/**
 *
 * @author xrist
 */
public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    boolean authenticate(String username, String password);
}
