package cb13.project.entities;


import com.fasterxml.jackson.annotation.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "Cannot be empty")
    @Size(min=3,max = 20)
    @Column(name="first_name")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String firstName;

    @NotBlank(message = "Cannot be empty")
    @Size(min=3,max = 20)
    @Column(name="last_name")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String lastName;

    @NotBlank(message = "Cannot be empty")
    @Size(min=7,max = 50)
    @Email
    @Column(name="email")
    private String email;

    @NotBlank(message = "Cannot be empty")
    @Size(min=7,max = 30)
    @Column(name="password")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n",
            message = "Invalid Input.The password must contain at least :.\n " +
                    "1) One number \n" +
                    "2) One lower case letter \n" +
                    "3) One upper case letter \n"+
                    "4) One special character \n"+
                    "5) White space is now allowed \n"+
                    "6) Password must have at least length of 6 characters")
    private String password;

    @Size(min=10,max = 10)
    @Column(name="phone")
    @Pattern(regexp = "[0-9]+" ,message = "Only numbers")
    @Size(min = 10,max = 10)
    private String phone;


    @Column(name="verify_code")
    private String verifyCode;

    @Column(name="verify")
    private boolean verified;

    @Column(name="active")
    private boolean isActive;

    @Column(name="image")
    private String img;

    @Column(name="role")
    private String role;
    
    @Column (name="sub_until")
    private Date sub_until;
    
    @Column (name="adv_for_ever")
    private boolean advForEver;

    
    @Size(min=3,max = 30)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @JsonManagedReference(value="user-pet")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")
    private List<Pet> pets;



    @JsonManagedReference(value="user-invoice")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoiceDetails_id")
    private InvoiceDetails invoiceDetails;

    @JsonManagedReference(value="user-ads")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Ads> ads;

    @JsonManagedReference(value="user-reminders")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")
    private List<Reminder> reminders;

    @JsonIgnore
    @JsonManagedReference(value="user-transactions")
    @OneToMany(mappedBy="user",fetch = FetchType.LAZY,
            cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    private List<Transaction> transactions;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Rating> rates;

//    @JsonManagedReference(value="examinations-user")
    @JsonIgnore
    @OneToMany(mappedBy="user",fetch = FetchType.LAZY,
            cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    private List<ExaminationRecord> examinations;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="business_id")
    private Business business;
    private String username;
    private String[] authorities;
    private boolean isNotLocked;


    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name="examination_record",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = { @JoinColumn(name = "pet_id") }
    )
    private List<Pet> petClients ;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String phone, String verifyCode, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.verifyCode = verifyCode;
        this.username = username;
    }

    public List<Pet> getPetClients() {
        return petClients;
    }

    public void setPetClients(List<Pet> petClients) {
        this.petClients = petClients;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    

    public List<ExaminationRecord> getExaminations() {
        return examinations;
    }

    
    
    public void addExam(ExaminationRecord exam){
        if(examinations == null){
            examinations = new ArrayList<>();
        }
        this.examinations.add(exam);
        exam.setUser(this);
    }
    
    public void removeExam(ExaminationRecord exam){
        this.examinations.remove(exam);
    }
    
    public void addPet(Pet pet){
        if(pets == null){
            pets = new ArrayList<>();
        }
        this.pets.add(pet);
        pet.setUser(this);
    }
    
    public void removePet(Pet pet){
        this.pets.remove(pet);
    }
    
    public void addReminder(Reminder reminder){
        if(reminders == null){
            reminders = new ArrayList<>();
        }
        this.reminders.add(reminder);
        reminder.setUser(this);
    }
    
    public void removeReminder(Reminder reminder){
        this.reminders.remove(reminder);
    }
    
    public void addRate(Rating rate){
        if(rates == null){
            rates = new ArrayList<>();
        }
        this.rates.add(rate);
        rate.setUser(this);
    }
    
    public void removeRate(Rating rate){
        this.rates.remove(rate);
    }
    
    
    public void addTransaction(Transaction tran){
        if(transactions == null){
            transactions = new ArrayList<>();
        }
        this.transactions.add(tran);
        tran.setUser(this);
    }
    
    public void removeTransaction(Transaction tran){
        this.transactions.remove(tran);
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getSub_until() {
        return sub_until;
    }

    public void setSub_until(Date sub_until) {
        this.sub_until = sub_until;
    }

    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public InvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(InvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Rating> getRates() {
        return rates;
    }

    public void setRates(List<Rating> rates) {
        this.rates = rates;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
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
        sb.append("User{id=").append(id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", verifyCode=").append(verifyCode);
        sb.append(", verified=").append(verified);
        sb.append(", isActive=").append(isActive);
        sb.append(", img=").append(img);
        sb.append(", role=").append(role);
        sb.append(", sub_until=").append(sub_until);
        sb.append(", advForEver=").append(advForEver);
        sb.append(", address=").append(address);
        sb.append(", pets=").append(pets);
        sb.append(", invoiceDetails=").append(invoiceDetails);
        sb.append(", ads=").append(ads);
        sb.append(", reminders=").append(reminders);
        sb.append(", transactions=").append(transactions);
        sb.append(", rates=").append(rates);
        sb.append(", business=").append(business);
        sb.append(", username=").append(username);
        sb.append(", authorities=").append(authorities);
        sb.append(", isNotLocked=").append(isNotLocked);
        sb.append(", petClients=").append(petClients);
        sb.append('}');
        return sb.toString();
    }
    
    


    
}
