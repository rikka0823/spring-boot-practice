package com.rikka.springBootPractice.component;

import com.rikka.springBootPractice.service.ifs.PrintService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HpComponent implements PrintService {

//    @Value("${count}")
    private int count;

//    @PostConstruct
//    public void init() {
//        count = 5;
//    }

    @Override
    public String print(String print) {
        count--;
        System.out.println(print + " say hi" + ", count: " + count);
        System.out.println("test");
        return "rikka say hello";
    }


}
