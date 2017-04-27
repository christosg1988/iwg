package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.UserWallet;
import gr.codingschool.iwg.repository.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by christos_georgiadis on 26/04/2017.
 */
@Service("userWalletService")
public class UserWalletServiceImpl implements UserWalletService {
    @Autowired
    UserWalletRepository userWalletRepository;

    @Override
    public UserWallet save(UserWallet wallet) {
        return userWalletRepository.save(wallet);
    }
}
