package com.chi.chwitter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.chi.chwitter.repository")
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfig {
}
