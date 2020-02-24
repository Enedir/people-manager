package sajadvpm.feature.person;

import org.springframework.stereotype.Service;
import sajadvpm.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService  {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Integer add(Person entity) {

        entity.newPerson();

        var newPerson = personRepository.save(entity);

        return newPerson.getId();
    }

    @Override
    public Boolean update(Person entity) {

        entity.updatePerson();

        var updatePerson = personRepository.save(entity);

        return updatePerson != null;
    }

    @Override
    public Person get(Integer id) throws NotFoundException {

        Optional<Person> entity = personRepository.findById(id);

        return entity.orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<Person> get() {

        return personRepository.findAll();
    }

    @Override
    public Boolean delete(Integer id) throws NotFoundException {

        var entity = get(id);

        entity.deactivate();

        var deletePerson = personRepository.save(entity);

        return deletePerson != null;
    }
}
