package com.yc.spel.factory;

import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
public class ApplicationContextFactory {  
    private static ApplicationContext ctx;  
    private ApplicationContextFactory() {  
    }  
    public static ApplicationContext createInstance() {  
        if (ctx == null) {  
            ctx = new ClassPathXmlApplicationContext(  
                    new String[] { "spring.xml" });  
        }  
        return ctx;  
    }  
} 