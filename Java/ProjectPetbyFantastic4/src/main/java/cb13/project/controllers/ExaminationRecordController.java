/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.ExaminationRecordDTO;
import cb13.project.entities.ExaminationRecord;
import cb13.project.entities.Pet;
import cb13.project.entities.User;
import cb13.project.service.ExaminationRecordService;
import cb13.project.service.PetService;
import cb13.project.service.UserService;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tsepe
 */
@RestController
@RequestMapping(path = "/exams")
public class ExaminationRecordController {
    
    @Autowired
    ExaminationRecordService examService;
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createExam(@Valid @RequestBody ExaminationRecordDTO examDTO , Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(!userPrincipal.getRole().equals("ROLE_DOCTOR")){return null;}

        User user = userService.findUserById(examDTO.getUser());

        Pet pet = petService.findById(examDTO.getPet());
        ExaminationRecord exam  = new ExaminationRecord(examDTO.getExamDay(),examDTO.getValidUntil(),examDTO.getDiagnosis(),examDTO.getExamCat());
        
        pet.addExam(exam);
        user.addExam(exam);
        
        ExaminationRecord newExam =  examService.saveExaminationRecord(exam);
        
        return new ResponseEntity<>(newExam,OK);
}
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllExams(){
//        List<ExaminationRecord> exams = examService.findAll();
//        System.out.println(exams);
//        return new ResponseEntity<>(exams,OK);
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getExam(@PathVariable("id") Long examId , Principal principal){
        User userPrincipal = userService.findUserByUsername(principal.getName());
        ExaminationRecord exam=examService.findExaminationRecordById(examId);
        if(userPrincipal.getId() == exam.getId() || userPrincipal.getRole().equals("ROLE_DOCTOR")){
        return new ResponseEntity<>(exam,OK);}
        return null;
    }
    
    
    @GetMapping("/getByPet/{id}")
    public ResponseEntity<?> getExamsByPet(@PathVariable("id") Long petId , Principal principal ){
        Pet pet = petService.findById(petId);
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getRole().equals("ROLE_DOCTOR") || userPrincipal.getId() == pet.getUser().getId()){
        List<ExaminationRecord> petExams = examService.findByPet(pet);
        return new ResponseEntity<>(petExams,OK);}
        return null;
    }


//    @GetMapping("/getByUser/{id}")
//    public ResponseEntity<?> getExamsByUser(@PathVariable("id") Long userId ){
//
//        List<ExaminationRecord> petExams = examService.findByUser(userId);
//        return new ResponseEntity<>(petExams,OK);
//    }
    
    
//    @PostMapping("/edit")
//    public ResponseEntity<?> editExam(@Valid @RequestBody ExaminationRecord editedExam , Principal principal){
//
//
//        ExaminationRecord exam = examService.findExaminationRecordById(editedExam.getId());
//
//
//        exam.setExamDay(editedExam.getExamDay());
//        exam.setValidUntil(editedExam.getValidUntil());
//        exam.setDiagnosis(editedExam.getDiagnosis());
//        exam.setExamCat(editedExam.getExamCat());
//
//
//
//        examService.saveExaminationRecord(exam);
//
//        return new ResponseEntity<>(null,OK);
//
//    }
    
//    @GetMapping("/delete/{id}")
//    public ResponseEntity<?> deleteExam(@PathVariable("id") Long petId){
//
//        examService.deleteExaminationRecordById(petId);
//        return new ResponseEntity<>("Examination DELETED",OK);    //Eipa na to dokimaso!!!
//    }
    
}