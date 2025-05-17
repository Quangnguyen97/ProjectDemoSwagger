package com.example.demoswagger;

import com.example.demoswagger.account.Account;
import com.example.demoswagger.account.AccountServiceImpl;
import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.user.User;
import com.example.demoswagger.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DataLineRunner implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    public DataLineRunner(UserServiceImpl userServiceImpl, AccountServiceImpl accountServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.accountServiceImpl = accountServiceImpl;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            userServiceImpl.saveUser(new User(1, "NGUYEN VAN A", "hash1", "token1"));
            userServiceImpl.saveUser(new User(2, "NGUYEN VAN B", "hash2", "token2"));
            userServiceImpl.saveUser(new User(3, "NGUYEN VAN C", "hash3", "token3"));

            accountServiceImpl.saveAccount(1, new Account(1, 5, 50));
            accountServiceImpl.saveAccount(1, new Account(1, 10, 100));
            accountServiceImpl.saveAccount(1, new Account(1, 20, 200));
            accountServiceImpl.saveAccount(2, new Account(2, 30, 300));
            accountServiceImpl.saveAccount(2, new Account(2, 40, 400));
            accountServiceImpl.saveAccount(3, new Account(3, 50, 500));
            accountServiceImpl.saveAccount(3, new Account(3, 60, 600));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
