/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Role;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.model.UserWallet;
import gr.codingschool.iwg.repository.RoleRepository;
import gr.codingschool.iwg.repository.UserRepository;
import gr.codingschool.iwg.repository.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author xrist
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWalletRepository walletRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public List<User> findAll(){ return userRepository.findAll(); }

    @Override
    public UserWallet initWalletForUser(String username){
        UserWallet wallet = new UserWallet();
        wallet.setBalance(0);
        wallet = walletRepository.save(wallet);
        User user = userRepository.findByUsername(username);
        user.setWallet(wallet);
        userRepository.save(user);
        return wallet;
    }

    @Override
    public UserWallet getWalletForUser(String username) {
        User user = userRepository.findByUsername(username);
        return user.getWallet();
    }

    @Override
    public int getBalanceForUser(String username) {
        User user = userRepository.findByUsername(username);
        return user.getWallet().getBalance();
    }

    @Override
    public Boolean addBalance(String username, int addedBalance) {
        User user = userRepository.findByUsername(username);
        UserWallet wallet = user.getWallet();
        wallet.setBalance(wallet.getBalance() + addedBalance);
        walletRepository.save(wallet);
        return true;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean useBalanceIfAvailable(String username, int usedBalance) {
        User user = userRepository.findByUsername(username);
        UserWallet wallet = user.getWallet();
        if(wallet.getBalance() < usedBalance)
            return false;
        wallet.setBalance(wallet.getBalance() - usedBalance);
        walletRepository.save(wallet);
        return true;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean withdrawBalance(String username, int withdrawnBalance) {
        User user = userRepository.findByUsername(username);
    UserWallet wallet = user.getWallet();
        if(wallet.getBalance() < withdrawnBalance)
            return false;
        wallet.setBalance(wallet.getBalance() - withdrawnBalance);
        walletRepository.save(wallet);
        return true;
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password){
        User user =  userRepository.findByUsername(username);

        if(user == null){
            return null;
        }
        else {
            if(bCryptPasswordEncoder.matches(password, user.getPassword()))
                return user;
            else
                return null;
        }
    }
}
