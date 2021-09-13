package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Business implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    

    @Column(name = "name")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String name;
    

    @Email(message = "Put the right email")
    @Column(name = "email")
    private String email;
    

    @Column(name = "phone")
    @Pattern(regexp = "[0-9]+" ,message = "Only numbers")
    @Size(min = 10,max = 10)
    private String phone;
    
    @Column(name = "description")
    @Size(max=4000)
    private String description;
    

    @Size(max=400)
    @Column(name = "text")
    private String text;
    
    @Column(name = "image_Logo")
    private String imgLogo;
    @Column(name = "image_Background")
    
    private String imgBackground;
    @Column(name = "facebook")
    private String facebook;
    
    @Column(name = "instagram")
    private String instagram;
    
    @Column(name = "web_Page")
    private String pageUrl;
    
    @ManyToOne
    @JoinColumn(name = "business_category_id")
    private BusinessCategory businessCategory;

    

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "business")
    private List<Rating> rating;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    
    @NotBlank
    @Column(name = "status")
    private String status;



    @Column(nullable = true,columnDefinition = "int default 0")
    @Generated(GenerationTime.NEVER)
    private double avgRating;

    public Business() {
    }

    public Business(String status) {
        this.status = status;
    }

    public Business(String name, String email, String phone, String description,
            String text, String imgLogo, String imgBackgroung, String facebook,
            String instagram, String pageUrl, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.text = text;
        this.imgLogo = imgLogo;
        this.imgBackground = imgBackgroung;
        this.facebook = facebook;
        this.instagram = instagram;
        this.pageUrl = pageUrl;
        this.status = status;
    }



    public void addRating(Rating rate) {
        if (rating == null) {
            rating = new ArrayList<>();
        }
        this.rating.add(rate);
        rate.setBusiness(this);

    }

    public void removeRating(Rating rate) {
        this.rating.remove(rate);

    }


    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getImgBackground() {
        return imgBackground;
    }

    public void setImgBackground(String imgBackground) {
        this.imgBackground = imgBackground;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BusinessCategory getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(BusinessCategory businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Business{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", description=").append(description);
        sb.append(", text=").append(text);
        sb.append(", imgLogo=").append(imgLogo);
        sb.append(", imgBackgroung=").append(imgBackground);
        sb.append(", facebook=").append(facebook);
        sb.append(", instagram=").append(instagram);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", businessCategory=").append(businessCategory);
        sb.append(", rating=").append(rating);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

}
