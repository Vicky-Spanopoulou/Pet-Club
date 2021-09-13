package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name="street")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String street;

    @Column(name="longitude")
    private double longitude;

    @Column(name="latitude")
    private double latitude;

    @Column(name="postal_code")
    @Pattern(regexp = "[0-9]+" ,message = "Only numbers")
    private String postalCode;
    
    
    @ManyToOne(fetch = FetchType.EAGER,
            cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "cities_id")
    private City city;

    public Address() {
    }

    public Address(String street, double longitude, double latitude, String postalCode) {
        this.street = street;
        this.longitude = longitude;
        this.latitude = latitude;
        this.postalCode = postalCode;
    }

    public Address(String street) {
        this.street = street;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{id=").append(id);
        sb.append(", street=").append(street);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }


    

}
