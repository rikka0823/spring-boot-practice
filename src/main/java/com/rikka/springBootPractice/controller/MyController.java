package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.aspect.MyAspect;
import com.rikka.springBootPractice.service.ifs.PrintService;
import com.rikka.springBootPractice.service.ifs.StudentService;
import com.rikka.springBootPractice.vo.StudentReq;
import com.rikka.springBootPractice.vo.StudentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rikka")
@RestController
public class MyController {

    @Autowired
    private StudentService studentService;

    @Qualifier("hpComponent")
    @Autowired
    private PrintService printService;

    @Autowired
    private MyAspect myAspect;

    @RequestMapping("/test")
    public String sayHello() {
        printService.print("hi");
        return  printService.print("rikka") + ", say ha";
    }

    @GetMapping("sayHi")
    public String sayHi() {
        return "hi";
    }

    @GetMapping("basicReq")
    public StudentRes basicRes1(@RequestParam(name = "newId") Integer id,
                               @RequestParam(defaultValue = "hello", required = false) String str) {
        System.out.println(id + str);
        return studentService.basicRes();
    }

    @PostMapping("postReq")
    public StudentRes basicRes2(@RequestBody StudentReq req) {
        System.out.println(req);
        return studentService.basicRes(req);
    }

    @GetMapping("headerReq")
    public String headerRes(@RequestHeader String contentType) {
        System.out.println(contentType);
        return "hi";
    }
}
