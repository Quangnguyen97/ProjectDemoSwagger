package com.example.demoswagger.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(long userId);

    Account findByUserIdAndAccountNumber(long userId, long accountNumber);

    void deleteByUserId(long userId);
}
