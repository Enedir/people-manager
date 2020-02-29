package sajadvpm.feature.person;

import sajadvpm.exception.CpfValidationExeption;
import sajadvpm.exception.NotFoundException;

import java.util.List;

public interface IPersonService {

    Integer add(Person entity, Integer avatarId) throws CpfValidationExeption;

    Boolean update(Person entity, Integer avatarId) throws NotFoundException, CpfValidationExeption;

    List<Person> upsert(List<Person> entities);

    Person get(Integer id) throws NotFoundException;

    List<Person> get();

    List<Person> getByActive();

    Boolean delete(Integer id) throws NotFoundException;

    Boolean checkCpfIsRepeated(String cpf);
}
