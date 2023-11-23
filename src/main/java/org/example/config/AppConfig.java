package org.example.config;

import org.example.InitContacts;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-default.properties")
//@Profile("default")
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public InitContacts initContacts(){
        return new InitContacts();
    }
}
