/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 *
 * @author tsepe
 */
public class PetDTO {
    private Long id;

    
    private String name;
    private String breed;
    private Date dob;
    private String chipNumber;
    private String color;
    private String behavior;
    private String sex;
    private String image;
    private Long user;
    private PetCategoryDTO petCategory;

    public PetDTO(Long id, String name, String breed, Date dob, String chipNumber, String color, String behavior, String sex, String image, Long user, PetCategoryDTO petCategory) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.dob = dob;
        this.chipNumber = chipNumber;
        this.color = color;
        this.behavior = behavior;
        this.sex = sex;
        this.image = image;
        this.user = user;
        this.petCategory = petCategory;
    }

    
    public PetDTO(String name, String breed, Date dob, String chipNumber, String color, String behavior, String sex, String image, PetCategoryDTO petCategory) {
        this.name = name;
        this.breed = breed;
        this.dob = dob;
        this.chipNumber = chipNumber;
        this.color = color;
        this.behavior = behavior;
        this.sex = sex;
        this.image = image;
        this.petCategory = petCategory;
    }
    
    

    public PetDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public PetCategoryDTO getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(PetCategoryDTO petCategory) {
        this.petCategory = petCategory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PetDTO{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", breed=").append(breed);
        sb.append(", dob=").append(dob);
        sb.append(", chipNumber=").append(chipNumber);
        sb.append(", color=").append(color);
        sb.append(", behavior=").append(behavior);
        sb.append(", sex=").append(sex);
        sb.append(", image=").append(image);
        sb.append(", user=").append(user);
        sb.append(", petCategory=").append(petCategory);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
