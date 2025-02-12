package com.rikka.springBootPractice.model.dto.canon;

import com.rikka.springBootPractice.service.print.PrintService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CanonDTO implements PrintService, InitializingBean {

    @Value("${rikka.printer}")
    private String printer;

    private int count;

    @Override
    public void afterPropertiesSet() throws Exception {
        count = 5;
    }

    @Override
    public String print(String print) {
        count--;
        System.out.println(printer + count);
        return print + "haha" + printer;
    }
}
