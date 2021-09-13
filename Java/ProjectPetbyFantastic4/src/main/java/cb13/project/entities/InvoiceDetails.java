package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice_details")
@Entity
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @NotBlank(message = "Cannot be empty")
    @Size(min=3, max=50)
    @Column(name = "company_name")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String companyName;

    @NotBlank(message = "Cannot be empty")
    @Size(min=3, max=50)
    @Column(name="company_email")
    @Email
    private String companyEmail;


    @Size(max = 50)
    @Column(name="financial_service")
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String financialService;

    @Size(max=50)
    @Column(name="vat_Number")
    @NotBlank(message = "Cannot be empty")
    private String vatNumber;

    @JsonBackReference(value="user-invoice")
    @OneToOne(mappedBy = "invoiceDetails")
    private User user;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name="phone")
    @NotBlank(message = "Cannot be empty")
    @Size(max=10,min=10)
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    private String phone;

    public InvoiceDetails() {
    }

    public InvoiceDetails(String companyName, String companyEmail, String financialService, String vatNumber, String phone) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.financialService = financialService;
        this.vatNumber = vatNumber;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getFinancialService() {
        return financialService;
    }

    public void setFinancialService(String financialService) {
        this.financialService = financialService;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvoiceDetails{id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", financialService=").append(financialService);
        sb.append(", vatNumber=").append(vatNumber);
        sb.append(", user=").append(user);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
    
    
}