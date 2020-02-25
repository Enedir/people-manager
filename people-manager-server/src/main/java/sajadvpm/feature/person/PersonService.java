package sajadvpm.feature.person;

import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.stereotype.Service;
import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.file.File;
import sajadvpm.feature.file.FileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    private PersonRepository personRepository;
    private FileRepository fileRepository;

    public PersonService(PersonRepository personRepository, FileRepository fileRepository) {
        this.personRepository = personRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public Integer add(Person entity, Integer avatarId) {

        validate(entity.getCpf());

        File avatar = null;

        if (avatarId != null)
            avatar = fileRepository.findById(avatarId).get();

        entity.newPerson(avatar);

        var newPerson = personRepository.save(entity);

        return newPerson.getId();
    }

    @Override
    public Boolean update(Person entity, Integer avatarId) throws NotFoundException {

        validate(entity.getCpf());

        Person existing = get(entity.getId());

        File avatar = null;

        if (avatarId != null)
            avatar = fileRepository.findById(avatarId).get();

        entity.updatePerson(existing, avatar);

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
    public List<Person> getByActive() {
        return personRepository.findAllByActive();
    }

    @Override
    public Boolean delete(Integer id) throws NotFoundException {

        var entity = get(id);

        entity.deactivate();

        var deletePerson = personRepository.save(entity);

        return deletePerson != null;
    }


    private void validate(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.assertValid(cpf);
    }
}
