package com.abhijeetsingh.utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection {

    //@Value("${JDBC_DRIVER}")
    private String driver;

    //@Value("${JDBC_URL}")
    private String url;

    //@Value("${JDBC_USERNAME}")
    private String username;

    //@Value("${JDBC_PASSWORD}")
    private String password;


    @Value("${JDBC_DRIVER}")
    @NonNull
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Value("${JDBC_URL}")
    @NonNull
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${JDBC_USERNAME}")
    @NonNull
    public void setUsername(String username) {
        this.username = username;
    }

    @Value("${JDBC_PASSWORD}")
    @NonNull
    public void setPassword(String password) {
        this.password = password;
    }

    public void display() {
        System.out.println("DRIVER : " + this.driver);
        System.out.println("URL : " + this.url);
        System.out.println("USER : " + this.username);
        System.out.println("PASSWORD : " + this.password);
    }

    public void getJDBCConnection() throws Exception {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from movies");
        while(rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getInt(2));
        }
        conn.close();
    }

}
