package sajadvpm.feature.file;

import org.springframework.stereotype.Service;

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

}
