/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

import java.util.Date;

/**
 *
 * @author tsepe
 */
public class ExaminationRecordDTO {
    
    private Long id;
    private Date examDay;
    private Date validUntil;
    private String diagnosis;
    private Long pet;
    private Long user;
    private String examCat;

    
    public ExaminationRecordDTO() {
    }

    public ExaminationRecordDTO(Long id, Date examDay, Date validUntil, String diagnosis, String examCat) {
        this.id = id;
        this.examDay = examDay;
        this.validUntil = validUntil;
        this.diagnosis = diagnosis;
        this.examCat = examCat;
    }

    public ExaminationRecordDTO(Date examDay, Date validUntil, String diagnosis, String examCat) {
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

    public Long getPet() {
        return pet;
    }

    public void setPet(Long pet) {
        this.pet = pet;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
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
        sb.append("ExaminationRecordDTO{id=").append(id);
        sb.append(", examDay=").append(examDay);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", diagnosis=").append(diagnosis);
        sb.append(", pet=").append(pet);
        sb.append(", user=").append(user);
        sb.append(", examCat=").append(examCat);
        sb.append('}');
        return sb.toString();
    }
    
    
}
