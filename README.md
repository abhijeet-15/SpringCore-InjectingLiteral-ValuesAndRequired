### @Value

- Enable annotations by adding below **beans.xml** 
```xml
    <context:annotation-config />
```
- Create a _.properties_ file and add the values for the literals
```properties
JDBC_DRIVER=com.mysql.cj.jdbc.Driver
JDBC_URL=jdbc:mysql://localhost:3306/films
JDBC_USERNAME=root
JDBC_PASSWORD=password
```

- In the **beans.xml** add the below to use the values while resolving the dependency:
```xml
<context:property-placeholder location="classpath:JDBC-Info.properties"/>
```
- Add the **@Value** annotation and provide the key from the above .properties files

```java
    @Value("${JDBC_DRIVER}")
    private String driver;

    @Value("${JDBC_URL}")
    private String url;

    @Value("${JDBC_USERNAME}")
    private String username;

    @Value("${JDBC_PASSWORD}")
    private String password;
```

- Add the **mysql** gradle dependency in the build.gradle file:
```groovy
    implementation 'mysql:mysql-connector-java:8.0.28'
```

Below is the **JDBCConnection.java** file:
```java
package com.abhijeetsingh.utility;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection {

    @Value("${JDBC_DRIVER}")
    private String driver;

    @Value("${JDBC_URL}")
    private String url;

    @Value("${JDBC_USERNAME}")
    private String username;

    @Value("${JDBC_PASSWORD}")
    private String password;

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
```