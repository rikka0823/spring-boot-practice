package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String getHello(Model model) {
        NewStudentDTO newStudentDTO = new NewStudentDTO(1, "d");
        model.addAttribute("student", newStudentDTO);
        return "index";
    }

    @GetMapping("/by")
    public String toBy() {
        return "by";
    }

    @PostMapping("/login")
    public String login(String userName,
                        String password) {
        return "login";
    }
}
