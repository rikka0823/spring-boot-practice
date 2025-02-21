package com.rikka.springBootPractice.service.api;

import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyApiHelper {

    private RestTemplate restTemplate = new RestTemplate();

    public NewStudentDTO getForObject() {
        NewStudentDTO newStudentDTO = restTemplate.getForObject(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                NewStudentDTO.class
        );

        ResponseEntity<NewStudentDTO> newStudentDTOResponseEntity = restTemplate.getForEntity(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                NewStudentDTO.class
        );

        System.out.println(newStudentDTO.getId());
        System.out.println(newStudentDTO.getName());

        return newStudentDTO;
    }

    public NewStudentDTO getForObjectWithParam() {
        Map<String, Object> params = new HashMap<>();
        params.put("graduate", true);

        NewStudentDTO newStudentDTO = restTemplate.getForObject(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                NewStudentDTO.class,
                params
        );

        return newStudentDTO;
    }

    public NewStudentDTO getForEntity() {
        ResponseEntity<NewStudentDTO> newStudentDTOResponseEntity = restTemplate.getForEntity(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                NewStudentDTO.class
        );

        HttpStatusCode statusCode = newStudentDTOResponseEntity.getStatusCode();

        NewStudentDTO newStudentDTO = newStudentDTOResponseEntity.getBody();
        System.out.println(statusCode);

        return new NewStudentDTO();
    }

    public NewStudentDTO postForObject() {
        NewStudentDTO newStudentDTO = new NewStudentDTO();
        newStudentDTO.setId(1);
        newStudentDTO.setName("amy");

        NewStudentDTO newStudentDTO1 = new RestTemplate().postForObject(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                newStudentDTO,
                NewStudentDTO.class
        );

        return new NewStudentDTO();
    }

    public NewStudentDTO postForEntity() {
        NewStudentDTO newStudentDTO = new NewStudentDTO();
        newStudentDTO.setName("s");
        newStudentDTO.setId(1);

        ResponseEntity<NewStudentDTO> newStudentDTOResponseEntity = new RestTemplate().postForEntity(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                newStudentDTO,
                NewStudentDTO.class
        );

        return new NewStudentDTO();
    }

    public NewStudentDTO exchange() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("headerName", "2");

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "amy");

        // get
        ResponseEntity<NewStudentDTO> responseEntity = new RestTemplate().exchange(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                HttpMethod.GET,
                httpEntity,
                NewStudentDTO.class,
                params
        );

        // post
        NewStudentDTO newStudentDTO = new NewStudentDTO();
        newStudentDTO.setId(1);
        newStudentDTO.setName("amy");

        HttpHeaders httpHeaders1 = new HttpHeaders();
        httpHeaders1.set("headerName", "3");
        httpHeaders1.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NewStudentDTO> httpEntity1 = new HttpEntity<>(newStudentDTO, httpHeaders1);

        ResponseEntity<NewStudentDTO> newStudentDTOResponseEntity = new RestTemplate().exchange(
                "https://mocki.io/v1/c91ec1ea-ac21-4c80-9e18-da9751c84721",
                HttpMethod.POST,
                httpEntity1,
                NewStudentDTO.class,
                newStudentDTO
        );

        return new NewStudentDTO();
    }
}
