package cb13.project.service;

import cb13.project.entities.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init();

    public void save(MultipartFile file , User user,String name);

    public Resource load(String username,String filename);

    public void deleteAll(String username);

    public Stream<Path> loadAll();
}
