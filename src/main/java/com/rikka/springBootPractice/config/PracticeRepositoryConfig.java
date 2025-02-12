package com.rikka.springBootPractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.rikka.springBootPractice.repository",
        entityManagerFactoryRef = "entityManagerFactoryPractice",
        transactionManagerRef = "transactionManagerPractice"
)
public class PracticeRepositoryConfig {
}
