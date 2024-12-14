package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.aspect.MyAspect;
import com.rikka.springBootPractice.service.ifs.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Qualifier("hpComponent")
    @Autowired
    private PrintService printService;

    @Autowired
    private MyAspect myAspect;

    @RequestMapping("/test")
    public String sayHello() {
        printService.print("hi");
        return  printService.print("rikka") + ", say hi";
    }
}
