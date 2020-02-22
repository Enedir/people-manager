package sajadv.persistence.features.persons;

import org.springframework.data.jpa.repository.JpaRepository;
import sajadv.domain.features.persons.Person;

public interface PersonRepository  extends JpaRepository<Person, Long> {
}
