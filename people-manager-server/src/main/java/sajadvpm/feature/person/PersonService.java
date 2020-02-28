package sajadvpm.feature.person;

import br.com.caelum.stella.validation.CPFValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sajadvpm.exception.CpfValidationExeption;
import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.file.File;
import sajadvpm.feature.file.FileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private int _isGreaterThan = 0;

    private PersonRepository personRepository;
    private FileRepository fileRepository;

    public PersonService(PersonRepository personRepository, FileRepository fileRepository) {
        this.personRepository = personRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public Integer add(Person entity, Integer avatarId) throws CpfValidationExeption {

        logger.info("Validando se o CPF é valido para persistir o registro na base.");

        validate(entity.getCpf());

        File avatar = null;

        if (avatarId != null)
            avatar = fileRepository.findById(avatarId).get();

        entity.newPerson(avatar);

        logger.info("Persistindo uma pessoa na base.");

        var newPerson = personRepository.save(entity);

        return newPerson.getId();
    }

    @Override
    public Boolean update(Person entity, Integer avatarId) throws NotFoundException, CpfValidationExeption {

        logger.info("Validando se o CPF é valido para a atualização do registro "  + entity.getId() + " na base.");

        validate(entity.getCpf());

        Person existing = get(entity.getId());

        File avatar = null;

        if (avatarId != null)
            avatar = fileRepository.findById(avatarId).get();

        entity.updatePerson(existing, avatar);

        logger.info("Atualizando a pessoa " + entity.getId() +" na base.");

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

        logger.info("Desativando a pessoa " + id +" na base.");

        entity.deactivate();

        var deletePerson = personRepository.save(entity);

        return deletePerson != null;
    }

    @Override
    public Boolean checkCpfIsRepeated(String cpf) {
        return  personRepository.getCpfCount(cpf) > _isGreaterThan;
    }


    private void validate(String cpf) throws CpfValidationExeption {

        CPFValidator cpfValidator = new CPFValidator();

        try {
            cpfValidator.assertValid(cpf);
        }catch (Exception ex)
        {
             throw new CpfValidationExeption();
        }
    }
}
