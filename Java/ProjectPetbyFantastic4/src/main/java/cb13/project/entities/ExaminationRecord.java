package cb13.project.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name ="examination_record")
@Entity
public class ExaminationRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "Cannot be empty")
    @Column(name = "exam_day")
    @PastOrPresent
    private Date examDay;

    @Column(name= "valid_until")
    @FutureOrPresent
    private Date validUntil;

    @Column(name = "diagnosis")
    private String diagnosis;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "exam_category")
    private String examCat;

    public ExaminationRecord() {
    }

    
    //without id (create a record with both dates)
    public ExaminationRecord(Date examDay, Date validUntil, String diagnosis, String examCat) {
        this.examDay = examDay;
        this.validUntil = validUntil;
        this.diagnosis = diagnosis;
        this.examCat = examCat;
    }

//    We use it in EDIT Controller
    public ExaminationRecord(Long id, Date examDay, Date validUntil, String diagnosis, String examCat) {
        this.id = id;
        this.examDay = examDay;
        this.validUntil = validUntil;
        this.diagnosis = diagnosis;
        this.examCat = examCat;
    }
    
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExamDay() {
        return examDay;
    }

    public void setExamDay(Date examDay) {
        this.examDay = examDay;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExamCat() {
        return examCat;
    }

    public void setExamCat(String examCat) {
        this.examCat = examCat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExaminationRecord{id=").append(id);
        sb.append(", examDay=").append(examDay);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", diagnosis=").append(diagnosis);
        sb.append(", examCat=").append(examCat);
        sb.append('}');
        return sb.toString();
    }

    
    
    
}
