package org.example;

import org.example.config.InitAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try{
            ApplicationContext context = new AnnotationConfigApplicationContext(InitAppConfig.class);

            context.getBean(WorkerContacts.class).startWork();
        }
        catch (Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
    }
}