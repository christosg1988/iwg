/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.model.UserWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author xrist
 */
public interface UserService {
    User findUserByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();
    UserWallet initWalletForUser(String username);
    UserWallet getWalletForUser(String username);
    int getBalanceForUser(String username);
    Boolean addBalance(String username, int addedBalance);
    Boolean useBalanceIfAvailable(String username, int usedBalance);
    Boolean withdrawBalance(String username, int withdrawnBalance);
    User save(User user);
    User update(User user);
    User authenticate(String username, String password);
}
