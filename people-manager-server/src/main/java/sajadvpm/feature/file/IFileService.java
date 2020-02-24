package sajadvpm.feature.file;

import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.person.Person;

public interface IFileService {

    Integer add(String name,  String path);

    File get(Integer id) throws NotFoundException;
}
