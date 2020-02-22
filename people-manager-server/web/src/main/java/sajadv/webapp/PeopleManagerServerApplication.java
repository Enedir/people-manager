package sajadv.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"sajadv.persistence"})
@EnableTransactionManagement
@EntityScan(basePackages = {"sajadv.domain"})
@ComponentScan( basePackages = {
        "sajadv.domain",
        "sajadv.persistence",
        "sajadv.application",
        "sajadv.webapp"})
public class PeopleManagerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeopleManagerServerApplication.class, args);
    }
}
