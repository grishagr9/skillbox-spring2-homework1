package org.example.config;

import org.example.InitContacts;
import org.example.WorkerContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application.properties")
//@Profile("init")
@ComponentScan("org.example")
public class InitAppConfig {
    @Bean
    public InitContacts initContacts(){
        return new InitContacts();
    }
}
