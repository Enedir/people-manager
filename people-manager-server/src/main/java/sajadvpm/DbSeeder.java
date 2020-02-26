package sajadvpm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sajadvpm.feature.person.Person;
import sajadvpm.feature.person.PersonRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private PersonRepository personRepository;

    public DbSeeder(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Jason Schultz","24946991000", "ut.mi.Duis@facilisis.ca", LocalDate.of(1994,10,29), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Reece Marsh","88037917010", "tincidunt.nibh.Phasellus@inmolestie.org", LocalDate.of(1995,12,15), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Lane Rush","15788818036", "amet.risus.Donec@orciinconsequat.com", LocalDate.of(1994,11,25), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Carter Britt","84993349033", "Nunc.ut.erat@rhoncusid.org", LocalDate.of(1993,07,11), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Giacomo Wong","71695562003", "arcu.imperdiet@utpellentesque.edu", LocalDate.of(1994,07,10), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Gannon Ellison","14387806052", "mi@aliquetnecimperdiet.com", LocalDate.of(2000,03,01), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Devin Mcgee","42274941092", "Aenean.eget.metus@infaucibus.com", LocalDate.of(2002,04,24), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Brody Pugh","65294310040", "Etiam@sapien.ca", LocalDate.of(1999,10,17), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Martin Colon","85118439060", "magna.Sed@FuscemollisDuis.net", LocalDate.of(1988,07,07), Boolean.TRUE, LocalDateTime.now()));
        persons.add(new Person("Dane Fox","56081220036", "semper.tellus@dis.ca", LocalDate.of(1990,07,16), Boolean.TRUE, LocalDateTime.now()));

        this.personRepository.saveAll(persons);
    }
}