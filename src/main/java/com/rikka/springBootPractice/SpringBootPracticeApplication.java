package com.rikka.springBootPractice;

import com.rikka.springBootPractice.controller.BookController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

    @Bean
    public ServletRegistrationBean<BookController> bookServlet() {
        ServletRegistrationBean<BookController> srb = new ServletRegistrationBean<>(new BookController(), "/servletTest");
        return srb;
    }

}
