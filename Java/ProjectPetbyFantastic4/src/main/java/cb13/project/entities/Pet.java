package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  //NEEDED -> SOMEHOW WE LOSE THE CONNECTION,AND WITH THIS ANNOTATION WE FIXED IT
@Table(name = "pet")
@Entity
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(max=45)
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String name;

    @Column(name = "breed")
    @Size(max = 45)
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String breed;


    @Column(name = "date_of_birth")
    private Date dob;

    @Size(max = 15,min = 15)
    @Column(name = "chip_number")
    private String chipNumber;

    @Size(max = 45)
    @Column(name = "color")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String color;

    @Size(max=500)
    @Column(name="behavior")
    private String behavior;

    @Size(max=45)
    @Column(name = "sex")
    @NotBlank(message = "Cannot be empty")
    private String sex;

    @Column(name = "image")
    private String image;


    @JsonBackReference(value="user-pet")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_categories_id")
    private PetCategory petCategory;
    

    @JsonIgnore
    @OneToMany(mappedBy="pet",fetch = FetchType.LAZY,
            cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    private List<ExaminationRecord> examinations;


    @JsonIgnore
    @ManyToMany(mappedBy = "petClients")
    private List<User> doctors ;

    public List<User> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<User> doctors) {
        this.doctors = doctors;
    }

    public Pet() {
    }

    //constructor with id and examinations
    public Pet(Long id, String name, String breed,
            Date dob, String chipNumber, String color,
            String behavior, String sex, String image,
            User user, PetCategory petCategory) {
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

//    constructor with id, but without examinations without user
    public Pet(Long id, String name, String breed, Date dob, String chipNumber, String color, String behavior, String sex, String image, PetCategory petCategory) {
        this.id = id;
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
    
    
    //for pet cration
    public Pet(String name, String breed, Date dob, String chipNumber, String color, String behavior, String sex, String image, User user, PetCategory petCategory) {
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

    public void addExam(ExaminationRecord exam){
        if(examinations == null){
            examinations = new ArrayList<>();
        }
        this.examinations.add(exam);
        exam.setPet(this);
    }
    
    public void removeExam(ExaminationRecord exam){
        this.examinations.remove(exam);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PetCategory getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(PetCategory petCategory) {
        this.petCategory = petCategory;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pet{id=").append(id);
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
//        sb.append(", examinations=").append(examinations);
        sb.append('}');
        return sb.toString();
    }
    
    
}