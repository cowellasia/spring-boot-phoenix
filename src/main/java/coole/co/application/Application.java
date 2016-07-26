package coole.co.application;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.rangers.jdbctemplate.model.dao", "com.rangers.jdbctemplate.model", "com.rangers.jdbctemplate.model.service"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSource dataSource() {

/*    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db =
        builder.setType(EmbeddedDatabaseType.H2).setSeparator(";;")
        .addScript("database/create-db.sql")
        .addScript("database/insert-data.sql")
        .build();
    return db; */

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver");
        dataSource.setUrl("jdbc:phoenix:ip-172-31-31-71.eu-west-1.compute.internal");
        return dataSource;

    }
}
