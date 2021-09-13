package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.SpringApplication;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "subscriptions")
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Size(max = 45)
    @Column(name="name")
    @NotBlank(message = "Cannot be empty")
    private String name;

    @Column(name="price")
    @NotBlank(message = "Cannot be empty")
    private double price;

    @Min(1)
    @Max(12)
    @Column(name="duration")
    @NotBlank(message = "Cannot be empty")
    private int duration;

    @Column(name = "role")
    @NotBlank
    private String role;
    
    @Column(name = "queue")
    private int queue;
    
    @Column(name = "adv_for_ever")
    @NotBlank
    private boolean advForEver;

    public Subscription() {
    }

    public Subscription(Long id, String name, double price, int duration, String role, int queue, boolean advForEver) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.role = role;
        this.queue = queue;
        this.advForEver = advForEver;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public boolean isAdvForEver() {
        return advForEver;
    }

    public void setAdvForEver(boolean advertiseForEver) {
        this.advForEver = advertiseForEver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Subscription{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", duration=").append(duration);
        sb.append(", role=").append(role);
        sb.append(", queue=").append(queue);
        sb.append(", advertiseForEver=").append(advForEver);
        sb.append('}');
        return sb.toString();
    }
    
    

    
    
    
}