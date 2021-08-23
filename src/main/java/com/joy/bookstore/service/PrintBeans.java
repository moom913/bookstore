package com.joy.bookstore.service;

import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PrintBeans {

    ApplicationContext applicationContext;

    public PrintBeans(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String printBeans() {
        StringBuilder stringBuilder = new StringBuilder("");
        Arrays.stream(applicationContext.getBeanDefinitionNames())
            .forEach(b -> {
                stringBuilder.append(b);
                stringBuilder.append("\n");
            });

        return stringBuilder.toString();
    }
}
