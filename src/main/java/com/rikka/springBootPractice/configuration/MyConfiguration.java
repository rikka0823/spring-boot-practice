package com.rikka.springBootPractice.configuration;

import com.rikka.springBootPractice.component.CanonComponent;
import com.rikka.springBootPractice.component.HpComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

//    @Value("${rikka.coun:999}")
    private int count;

    @Bean
    public CanonComponent c() {
        System.out.println(count);
        return new CanonComponent();
    }

    @Bean("hp")
    public HpComponent hpComponent() {
        return new HpComponent();
    }

}
