package sajadv.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sajadv.domain.features.persons.Person;
import sajadv.domain.features.users.User;
import sajadv.persistence.features.persons.PersonRepository;
import sajadv.persistence.features.users.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DbSeeder implements CommandLineRunner {

    private UserRepository userRepository;
    private PersonRepository personRepository;

    public DbSeeder(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin", "admin");
        admin.setInsertDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        LocalDate localDate = LocalDate.of(1990, 6, 28);
        Date birthDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Person person = new Person("Cladio","54235638017", "cladio@gmail.com", "", birthDate, Boolean.TRUE);
        person.setInsertDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        this.userRepository.save(admin);
        this.personRepository.save(person);
    }
}
