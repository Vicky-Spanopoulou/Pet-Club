package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "transactions")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="date")
    private Date date;

    @Size(max = 45)
    @Column(name="type")
    private String type;

    
    @Size(min=3,max=45)
    @Column(name="paypal_id")
    private String paypalId;
    
    @Column(name="paid")
    private boolean paid;
    
    @Column(name="user_username")
    private String username;
    
    @Column(name="sub_name")
    private String sub_name;
    
    @Column(name="sub_price")
    private String sub_price;

    @Column(name="sub_duration")
    private int sub_duration;
    
    @Column(name="sub_role")
    private String sub_role;
    
    @Column(name="adv_for_ever")
    private boolean advForEver;
    


    @JsonBackReference(value="user-transactions")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction() {
    }

    public Transaction(Date date, String type, boolean paid, String username, 
            String sub_name, String sub_price, int sub_duration, String sub_role, boolean advForEver) 
    {
        this.date = date;
        this.type = type;
        this.paid = paid;
        this.username = username;
        this.sub_name = sub_name;
        this.sub_price = sub_price;
        this.sub_duration = sub_duration;
        this.sub_role = sub_role;
        this.advForEver = advForEver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaypalId() {
        return paypalId;
    }

    public void setPaypalId(String paypalId) {
        this.paypalId = paypalId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSub_price() {
        return sub_price;
    }

    public void setSub_price(String sub_price) {
        this.sub_price = sub_price;
    }

    public int getSub_duration() {
        return sub_duration;
    }

    public void setSub_duration(int sub_duration) {
        this.sub_duration = sub_duration;
    }

    public String getSub_role() {
        return sub_role;
    }

    public void setSub_role(String sub_role) {
        this.sub_role = sub_role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAdvForEver() {
        return advForEver;
    }

    public void setAdvForEver(boolean advForEver) {
        this.advForEver = advForEver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction{id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", type=").append(type);
        sb.append(", paypalId=").append(paypalId);
        sb.append(", paid=").append(paid);
        sb.append(", username=").append(username);
        sb.append(", sub_name=").append(sub_name);
        sb.append(", sub_price=").append(sub_price);
        sb.append(", sub_duration=").append(sub_duration);
        sb.append(", sub_role=").append(sub_role);
        sb.append(", advForEver=").append(advForEver);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
    
    

    

    
}