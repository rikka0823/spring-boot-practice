package com.rikka.springBootPractice.config;

import com.rikka.springBootPractice.model.dto.canon.CanonDTO;
import com.rikka.springBootPractice.model.dto.hp.HpDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

//    @Value("${rikka.coun:999}")
    private int count;

    @Bean
    public CanonDTO c() {
//        System.out.println(count);
        return new CanonDTO();
    }

    @Bean("hp")
    public HpDTO hpComponent() {
        return new HpDTO();
    }
}