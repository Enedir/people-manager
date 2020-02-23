package sajadvpm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sajadvpm.feature.person.Person;
import sajadvpm.feature.person.PersonRepository;

import java.time.LocalDate;

@Component
public class DbSeeder implements CommandLineRunner {

    private PersonRepository personRepository;

    public DbSeeder(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Person person = new Person("Cladio","54235638017", "cladio@gmail.com", "", LocalDate.of(1990, 6, 28), Boolean.TRUE);
        person.setInsertDate(LocalDate.now());

        this.personRepository.save(person);
    }
}