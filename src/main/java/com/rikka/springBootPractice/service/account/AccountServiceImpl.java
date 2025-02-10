package com.rikka.springBootPractice.service.account;

import com.rikka.springBootPractice.dao.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    @Override
    public ResponseEntity<String> transfer(Integer fromAccountId, Integer toAccountId, Integer money) {
        if (!accountDao.existsById(fromAccountId) || !accountDao.existsById(toAccountId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
        }

        accountDao.decreaseMoney(fromAccountId, money);

//        Integer i = 1 / 0;

        accountDao.addMoney(toAccountId, money);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }
}
