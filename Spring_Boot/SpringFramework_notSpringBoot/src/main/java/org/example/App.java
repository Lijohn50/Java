package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        Developer devs = context.getBean(Developer.class);
        devs.create();

        System.out.println(devs.getAge());
    }
}
