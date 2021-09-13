package cb13.project.service;

import cb13.project.entities.ExaminationRecord;
import cb13.project.entities.Pet;

import java.util.List;

public interface ExaminationRecordService {

    ExaminationRecord saveExaminationRecord(ExaminationRecord examinationRecord);


    ExaminationRecord updateExaminationRecord(ExaminationRecord examinationRecord);

    void deleteExaminationRecordById(Long examinationRecordId);

    List<ExaminationRecord> findAll();
    
    List<ExaminationRecord> findByPet(Pet pet);
    
    ExaminationRecord findExaminationRecordById(Long id);
    
    List<ExaminationRecord> findByUser(Long id);
}
