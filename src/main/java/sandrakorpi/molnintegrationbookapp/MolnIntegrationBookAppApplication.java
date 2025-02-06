package sandrakorpi.molnintegrationbookapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MolnIntegrationBookAppApplication {


    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.datasource.url", dotenv.get("SPRING_DATASOURCE_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("SPRING_DATASOURCE_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
        System.setProperty("spring.datasource.driver-class-name", dotenv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME"));
        System.setProperty("spring.jpa.hibernate.ddl-auto", dotenv.get("SPRING_JPA_HIBERNATE_DDL_AUTO"));
        System.setProperty("spring.jpa.show-sql", dotenv.get("SPRING_JPA_SHOW_SQL"));
        System.setProperty("spring.jpa.properties.hibernate.dialect", dotenv.get("SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT"));
        System.setProperty("server.port", dotenv.get("SERVER_PORT"));
        System.setProperty("jwt.secret", dotenv.get("JWT_SECRET"));

        SpringApplication.run(MolnIntegrationBookAppApplication.class, args);
    }

}
