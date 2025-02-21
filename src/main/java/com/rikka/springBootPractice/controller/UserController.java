package com.rikka.springBootPractice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikka.springBootPractice.model.dto.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/convert")
    public ResponseEntity<UserDTO> convert() throws JsonProcessingException {
        UserDTO userDTO = new UserDTO(1, null, "sdaf@dsf.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(userDTO);
        System.out.println(jsonString);

        String user = "{\"id\":2," +
                "\"name\":\"a\"," +
                "\"age\":18}";

        String user1 = "{\"id\":2," +
                "\"name\":\"a\"," +
                "\"e_mail\": \"sadf@dsf.com\"}";

        UserDTO userDTO1 = objectMapper.readValue(user1, UserDTO.class);
        System.out.println(userDTO1.getId());
        System.out.println(userDTO1.getName());

        return ResponseEntity.status(HttpStatus.OK).body(userDTO1);
    }
}
