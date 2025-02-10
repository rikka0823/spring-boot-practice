package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.model.dto.account.AccountDTO;
import com.rikka.springBootPractice.service.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody AccountDTO req) {
        return accountService.transfer(
                req.getFromAccountId(),
                req.getToAccountId(),
                req.getMoney()
        );
    }
}
