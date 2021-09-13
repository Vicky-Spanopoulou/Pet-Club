/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

/**
 *
 * @author vicky
 */
public class InvoiceDetailsDTO {
    
    private Long id;
    private String companyName;
    private String companyEmail;
    private String financialService;
    private String vatNumber;
    private Long userId;
    private String phone;
    
    // Address
    private Long addressId;
    private String street;
    private double longitude;
    private double latitude;
    private String postalCode;
    private String city;

    public InvoiceDetailsDTO() {
    }

    public InvoiceDetailsDTO(Long id, String companyName, String companyEmail, String financialService, String vatNumber, Long userId, String phone, Long addressId, String street, double longitude, double latitude, String postalCode, String city) {
        this.id = id;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.financialService = financialService;
        this.vatNumber = vatNumber;
        this.userId = userId;
        this.phone = phone;
        this.addressId = addressId;
        this.street = street;
        this.longitude = longitude;
        this.latitude = latitude;
        this.postalCode = postalCode;
        this.city = city;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvoiceDetailsDTO{id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", financialService=").append(financialService);
        sb.append(", vatNumber=").append(vatNumber);
        sb.append(", userId=").append(userId);
        sb.append(", phone=").append(phone);
        sb.append(", addressId=").append(addressId);
        sb.append(", street=").append(street);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
