package cb13.project.service.impl;

import cb13.project.entities.User;
import cb13.project.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageImpl implements FileStorageService {
    private final Path root = Paths.get("uploads");


    @Override
    public void init() {
        try {
            if(!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, User user,String name) {
        File imageFile = new File(this.root+"/"+user.getUsername()+"/"+name);
        try {
            if(!Files.exists(Paths.get(root+ "/" + user.getUsername()+"/"))) {
                Files.createDirectory(Paths.get(root+ "/" + user.getUsername()+"/"));
            }
            if(imageFile.exists()){
                imageFile.delete();
            }
            //file.getOriginalFilename())
            Path path = Paths.get(this.root+"/"+user.getUsername()+"/");
            Files.copy(file.getInputStream(),path.resolve(name) );
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String username,String filename) {
        try {
            Path file = root.resolve(username+"/"+filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll(String username) {
        Path path=Paths.get("uploads/"+username);
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
