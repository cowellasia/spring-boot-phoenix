package coole.co.application;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {"classpath:phoenix.properties"})
@SpringBootApplication
public class Application {
    @Autowired
    private Environment env;

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
        dataSource.setUrl(env.getProperty("phoenix.url"));
        return dataSource;

    }
}
