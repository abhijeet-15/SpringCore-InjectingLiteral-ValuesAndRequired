package com.abhijeetsingh;

import com.abhijeetsingh.utility.JDBCConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello there!");
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        JDBCConnection conn = context.getBean("jdbcConnectionObject", JDBCConnection.class);
        conn.display();
        conn.getJDBCConnection();
    }
}