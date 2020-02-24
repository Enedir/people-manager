package sajadvpm.feature.person;

import sajadvpm.exception.NotFoundException;

import java.util.List;

public interface IPersonService {

    Integer add(Person entity);

    Boolean update(Person entity);

    Person get(Integer id) throws NotFoundException;

    List<Person> get();

    Boolean delete(Integer id) throws NotFoundException;
}
