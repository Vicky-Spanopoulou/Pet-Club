package cb13.project.service.impl;

import cb13.project.entities.ExaminationRecord;
import cb13.project.entities.Pet;
import cb13.project.repository.ExaminationRecordRepository;
import cb13.project.service.ExaminationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExaminationRecordServiceImpl implements ExaminationRecordService {

    @Autowired
    private ExaminationRecordRepository examinationRecordRepository;

    @Override
    public ExaminationRecord saveExaminationRecord(ExaminationRecord examinationRecord) {
        return examinationRecordRepository.save(examinationRecord);
    }

    @Override
    public ExaminationRecord updateExaminationRecord(ExaminationRecord examinationRecord) {
        return examinationRecordRepository.save(examinationRecord);
    }

    @Override
    public void deleteExaminationRecordById(Long examinationRecordId) {
        examinationRecordRepository.deleteById(examinationRecordId);
    }

    @Override
    public List<ExaminationRecord> findAll() {
        return examinationRecordRepository.findAll();
    }

    @Override
    public ExaminationRecord findExaminationRecordById(Long id) {
        return examinationRecordRepository.getById(id);
    }

    @Override
    public List<ExaminationRecord> findByPet(Pet pet) {
        return examinationRecordRepository.findExaminationRecordByPet(pet);
    }

    @Override
    public List<ExaminationRecord> findByUser(Long id) {
        return examinationRecordRepository.findExaminationRecordByUserId(id);
    }
}
