package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ads")
@Entity
public class Ads implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_name")
    @Size(max = 45)
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String petName;

    @Column(name = "chip_number")
    @Size(max = 45)
    private String chipNumber;

    @Column(name = "pet_sex")
    @Size(max = 45)
    @NotBlank(message = "Cannot be empty")
    private String sex;

    @Column(name = "some_words")
    @Size(max = 500)
    @NotBlank(message = "Cannot be empty")
    private String someWords;

    @Column(name = "description")
    @Size(max = 1000)
    private String descriptions;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonBackReference(value="user-ads")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_categories_id")
    @NotBlank(message = "Cannot be empty")
    private PetCategory petCategory;
    
    
    @Column(name = "ad_category")
    @Size(max = 45)
    @NotBlank(message = "Cannot be empty")
    private String adCategory;
    
    @NotBlank(message = "Cannot be empty")
    @Column(name = "lost_date")
    private Date lostDate;

    public Ads() {
    }

    public Ads( String petName, String chipNumber, String sex, String someWords, String descriptions, Date postDate, String image,  String adCategory, Date lostDate) {
        
        this.petName = petName;
        this.chipNumber = chipNumber;
        this.sex = sex;
        this.someWords = someWords;
        this.descriptions = descriptions;
        this.postDate = postDate;
        this.image = image;
        this.adCategory = adCategory;
        this.lostDate = lostDate;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }
    

    public String getSomeWords() {
        return someWords;
    }

    public void setSomeWords(String someWords) {
        this.someWords = someWords;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ads{id=").append(id);
        sb.append(", petName=").append(petName);
        sb.append(", chipNumber=").append(chipNumber);
        sb.append(", sex=").append(sex);
        sb.append(", someWords=").append(someWords);
        sb.append(", descriptions=").append(descriptions);
        sb.append(", postDate=").append(postDate);
        sb.append(", image=").append(image);
        sb.append(", address=").append(address);
        sb.append(", user=").append(user);
        sb.append(", petCategory=").append(petCategory);
        sb.append(", adCategory=").append(adCategory);
        sb.append(", lostDate=").append(lostDate);
        sb.append('}');
        return sb.toString();
    }
    
    

    

}
