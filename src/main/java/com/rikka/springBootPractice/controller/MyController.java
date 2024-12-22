package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.aspect.MyAspect;
import com.rikka.springBootPractice.service.ifs.PrintService;
import com.rikka.springBootPractice.service.ifs.StudentService;
import com.rikka.springBootPractice.vo.StudentReq;
import com.rikka.springBootPractice.vo.StudentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//@Controller
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
        return printService.print("rikka") + ", say ha";
    }

    @GetMapping("sayHi")
    public String sayHi() {
        return "hi";
    }

    @GetMapping("basicReq")
    public StudentRes basicRes1(@RequestParam(name = "newId", required = false) Integer id,
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
    public String headerRes(@RequestHeader(name = "Content-Type", defaultValue = "7") Integer contentType) {
        System.out.println(contentType);
        return "hi";
    }

    @GetMapping("path/{name}/{age}")
    public String pathRes(@PathVariable String name,
                          @PathVariable Integer age,
                          @RequestParam String id) {
        System.out.println(id + ": " + name + ": " + age);
        return "hello";
    }

//    @ResponseBody
//    @RequestMapping("students")
//    @RequestMapping(value = "students", method = RequestMethod.POST)
    @PostMapping("students")
    public String create(@RequestBody StudentReq req) {
        System.out.println(req.getName());
        return "create: " +  req.getName();
    }

    @GetMapping("students/{id}")
    public String read(@PathVariable Integer id) {
        System.out.println(id);
        return "read: " + id;
    }

    @PutMapping("students/{id}")
    public String update(@PathVariable Integer id,
                         @RequestBody StudentReq req) {
        System.out.println(req);
        return "update: " + req;
    }

    @DeleteMapping("students/{id}")
    public String delete(@PathVariable int id) {
        System.out.println(id);
        return "delete: " + id;
    }



}

