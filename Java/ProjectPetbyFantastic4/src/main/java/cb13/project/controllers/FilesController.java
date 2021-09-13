package cb13.project.controllers;

import cb13.project.domain.FileInfo;
import cb13.project.domain.ResponseMessage;
import cb13.project.entities.Ads;
import cb13.project.entities.Business;
import cb13.project.entities.Pet;
import cb13.project.entities.User;
import cb13.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/image")
@CrossOrigin("http://localhost:8081")
public class FilesController {

    @Autowired
    AdsService adsService;

    @Autowired
    BusinessService businessService;

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;

    @Autowired
    FileStorageService storageService;

    //User Controller Image
    @PostMapping("/upload/user/{username}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable("username") String username,
                                                      @RequestParam("file") MultipartFile file , Principal principal) {
        User user = userService.findUserByUsername(username);
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(user.getUsername().equals(principal.getName()) || userPrincipal.getRole().equals("ROLE_ADMIN")){
        String fileName = file.getOriginalFilename();
        String[] a= fileName.split("[.]",0);
        String endingPath = a[a.length-1];
        String message="";
        try {
            storageService.save(file, user, "userImage" + user.getId()+"."+endingPath);
            user.setImg("http://localhost:8081/image/files/" + user.getUsername() + "/userImage" + user.getId()+"."+endingPath);
            userService.updateUser(user);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }  catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    return  null;
    }
    //Ads Controller Image
    @PostMapping("/upload/lf/{username}/{adId}")
    public ResponseEntity<ResponseMessage> uploadFileAds( @PathVariable("username") String username,
                                                       @PathVariable("adId") Long adId,@RequestParam("file") MultipartFile file ,Principal principal) {
        User user = userService.findUserByUsername(username);

        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(user.getUsername().equals(principal.getName()) || userPrincipal.getRole().equals("ROLE_ADMIN")){
        String fileName = file.getOriginalFilename();
        String[] a= fileName.split("[.]",0);
        String endingPath = a[a.length-1];
        String message="";


        Ads ad = adsService.findById(adId);
        if (user.getAds().contains(ad)) {
            try {
                storageService.save(file, user, "adImage" + ad.getId()+"."+endingPath);
                ad.setImage("http://localhost:8081/image/files/" + user.getUsername() + "/adImage" + ad.getId()+"."+endingPath);
                adsService.updateAds(ad);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }  catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));}
        return null;
    }

    
    //Business Controller Profile Image
    @PostMapping("/upload/businessProfile/{username}/{busId}")
    public ResponseEntity<ResponseMessage> uploadFileBusiness( @PathVariable("username") String username,
                                                          @PathVariable("busId") Long bussId,@RequestParam("file") MultipartFile file ,Principal principal) {

        User user = userService.findUserByUsername(username);

        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(user.getUsername().equals(principal.getName()) || userPrincipal.getRole().equals("ROLE_ADMIN")){
        String fileName = file.getOriginalFilename();
        String[] a= fileName.split("[.]",0);
        String endingPath = a[a.length-1];
        String message="";


        Business business = businessService.findById(bussId);
        if (user.getBusiness().getId()==business.getId()) {
            try {
                storageService.save(file, user, "busProfImage" + business.getId()+"."+endingPath);
                business.setImgLogo("http://localhost:8081/image/files/" + user.getUsername() + "/busProfImage" + business.getId()+"."+endingPath);
                businessService.updateBusiness(business);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }  catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));}
        return null;
    }

    //Business Controller Background Image
    @PostMapping("/upload/businessBackground/{username}/{busId}")
    public ResponseEntity<ResponseMessage> uploadFileBusinessBackground( @PathVariable("username") String username,
                                                               @PathVariable("busId") Long bussId,@RequestParam("file") MultipartFile file , Principal principal) {
        User user = userService.findUserByUsername(username);

        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(user.getUsername().equals(principal.getName()) || userPrincipal.getRole().equals("ROLE_ADMIN")){
        String fileName = file.getOriginalFilename();
        String[] a= fileName.split("[.]",0);
        String endingPath = a[a.length-1];
        String message="";


        Business business = businessService.findById(bussId);
        if (user.getBusiness().getId()==business.getId()) {
            try {
                storageService.save(file, user, "busBackImage" + business.getId()+"."+endingPath);
                business.setImgBackground("http://localhost:8081/image/files/" + user.getUsername() + "/busBackImage" + business.getId()+"."+endingPath);
                businessService.updateBusiness(business);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }  catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));}
        return null;
    }





    //Pet Controller Image
    @PostMapping("/upload/pet/{username}/{petId}")
    public ResponseEntity<ResponseMessage> uploadFile( @PathVariable("username") String username,
            @PathVariable("petId") Long petId,@RequestParam("file") MultipartFile file , Principal principal) {

        User user = userService.findUserByUsername(username);
        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(user.getUsername().equals(principal.getName()) || userPrincipal.getRole().equals("ROLE_ADMIN")){

            String fileName = file.getOriginalFilename();
            String[] a= fileName.split("[.]",0);
            String endingPath = a[a.length-1];
            String message="";

            Pet pet = petService.findById(petId);
            if (user.getPets().contains(pet)) {
                try {
                    storageService.save(file, user, "petImage" + pet.getId()+"."+endingPath);
                    pet.setImage("http://localhost:8081/image/files/" + user.getUsername() + "/petImage" + pet.getId()+"."+endingPath);
                    petService.savePet(pet);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                }  catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            }
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
        return null;
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{username}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename,@PathVariable("username") String username ) {
        Resource file = storageService.load(username,filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
