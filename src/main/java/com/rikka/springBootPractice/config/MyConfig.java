package com.rikka.springBootPractice.config;

import com.rikka.springBootPractice.component.CanonComponent;
import com.rikka.springBootPractice.component.HpComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

//    @Value("${rikka.coun:999}")
    private int count;

    @Bean
    public CanonComponent c() {
//        System.out.println(count);
        return new CanonComponent();
    }

    @Bean("hp")
    public HpComponent hpComponent() {
        return new HpComponent();
    }
}