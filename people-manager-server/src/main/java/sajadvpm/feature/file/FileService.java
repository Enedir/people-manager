package sajadvpm.feature.file;

import org.springframework.stereotype.Service;
import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.person.Person;

import java.util.Optional;

@Service
public class FileService implements IFileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Integer add(String name, String path) {

        var avatar = new File(name, path);

        var newAvatar = fileRepository.save(avatar);

        return  newAvatar.getId();
    }

    @Override
    public File get(Integer id) throws NotFoundException {

        Optional<File> entity = fileRepository.findById(id);

        return entity.orElseThrow(() -> new NotFoundException(id));
    }
}
