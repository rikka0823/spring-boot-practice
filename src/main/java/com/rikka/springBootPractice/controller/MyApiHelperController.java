package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.service.api.MyApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyApiHelperController {

    @Autowired
    private MyApiHelper myApiHelper;

    @GetMapping("/getName")
    public ResponseEntity<String> getForObject() {
        return ResponseEntity.status(HttpStatus.OK).body(myApiHelper.getForObject().getName());
    }
}
