package org.example;

import org.example.config.AppConfig;
import org.example.config.InitAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try{
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            context.getBean(WorkerContacts.class).startWork();
        }
        catch (Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
    }
}