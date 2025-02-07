package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.aspect.MyAspect;
import com.rikka.springBootPractice.service.print.PrintService;
import com.rikka.springBootPractice.service.student.StudentService;
import com.rikka.springBootPractice.dto.student.StudentRequestDTO;
import com.rikka.springBootPractice.dto.student.StudentResponseDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


//@Controller
@Validated
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
    public StudentResponseDTO basicRes1(@RequestParam(name = "newId", required = false) Integer id,
                                        @RequestParam(defaultValue = "hello", required = false) String str) {
        System.out.println(id + str);
        return studentService.basicRes();
    }

    @PostMapping("postReq")
    public StudentResponseDTO basicRes2(@RequestBody StudentRequestDTO req) {
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
    public String create(@RequestBody StudentRequestDTO req) {
        System.out.println(req.getName());
        return "create: " + req.getName();
    }

    @GetMapping("students/{id}")
    public String read(@PathVariable Integer id) {
        System.out.println(id);
        return "read: " + id;
    }

    @PutMapping("students/{id}")
    public String update(@PathVariable Integer id,
                         @RequestBody @Valid StudentRequestDTO req) {
        System.out.println(req);
        return "update: " + req;
    }

    @DeleteMapping("students/{id}")
    public String delete(@PathVariable @Min(50) Integer id) {
        System.out.println(id);
        return "delete: " + id;
    }

    @PostMapping("httpStatus")
    public ResponseEntity<String> httpStatus() {
//        return ResponseEntity.status(HttpStatus.CREATED).body("201, post");
//        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("202, pending");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403");
    }

    @GetMapping("practice/{exception1}")
    public StudentResponseDTO exceptionPractice1(@PathVariable Integer exception1) {
        return studentService.exceptionPractice1();
    }

    @GetMapping("practice")
    public StudentResponseDTO exceptionPractice2(@RequestParam Integer exception2) {
        return studentService.exceptionPractice2();
    }

    @GetMapping("login")
    public ResponseEntity<String> login(HttpSession session) {
        session.setAttribute("role", "admin");
        return ResponseEntity.ok("200");
    }

    @GetMapping("logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("200");
    }
}