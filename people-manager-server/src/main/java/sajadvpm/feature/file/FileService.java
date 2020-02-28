package sajadvpm.feature.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sajadvpm.feature.person.PersonService;

@Service
public class FileService implements IFileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Integer add(String name, String path) {

        var avatar = new File(name, path);

        logger.info("Persistindo um arquivo na base.");

        var newAvatar = fileRepository.save(avatar);

        return  newAvatar.getId();
    }

}
