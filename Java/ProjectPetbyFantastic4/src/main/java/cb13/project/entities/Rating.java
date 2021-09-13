package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "rating")
@Entity
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_number")
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp = "[0-9]+" ,message = "Only numbers")
    private int ratingNumber;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    @JsonBackReference(value="business-ratings")
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public Rating() {
    }

    public Rating(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rating{id=").append(id);
        sb.append(", ratingNumber=").append(ratingNumber);
        sb.append(", user=").append(user);
        sb.append(", business=").append(business);
        sb.append('}');
        return sb.toString();
    }


    
}