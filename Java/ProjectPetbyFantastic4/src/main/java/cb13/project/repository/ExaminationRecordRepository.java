package cb13.project.repository;

import cb13.project.entities.ExaminationRecord;
import cb13.project.entities.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExaminationRecordRepository extends JpaRepository<ExaminationRecord,Long> {
    
    public List<ExaminationRecord> findExaminationRecordByPet(Pet pet);
    
    public List<ExaminationRecord> findExaminationRecordByUserId(Long userId);
}
