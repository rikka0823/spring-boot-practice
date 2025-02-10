package com.rikka.springBootPractice.service.account;

import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<String> transfer(Integer fromAccountId, Integer toAccountId, Integer money);
}
