/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

import cb13.project.entities.Rating;
import java.util.List;

/**
 *
 * @author vicky
 */
public class BusinessDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private String text;
    private String imgLogo;
    private String imgBackground;
    private String facebook;
    private String instagram;
    private String pageUrl;
    private List<Rating> ratings;
    private String businessCategory;
    private String status;
    
    // Address
    private Long addressId;
    private String street;
    private double longitude;
    private double latitude;
    private String postalCode;
    private String city;

    public BusinessDTO() {
    }

    public BusinessDTO(Long id, String name, String email, String phone, String description, 
            String text, String imgLogo, String imgBackgroung, String facebook, String instagram, 
            String pageUrl, List<Rating> ratings, Long addressId, String street, double longitude, 
            double latitude, String postalCode, String city, String status) {
        this.id = id;
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
        this.ratings = ratings;
        this.addressId = addressId;
        this.street = street;
        this.longitude = longitude;
        this.latitude = latitude;
        this.postalCode = postalCode;
        this.city = city;
        this.status = status;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
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

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
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
        sb.append("BusinessDTO{id=").append(id);
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
        sb.append(", ratings=").append(ratings);
        sb.append(", businessCategory=").append(businessCategory);
        sb.append(", addressId=").append(addressId);
        sb.append(", street=").append(street);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", city=").append(city);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
    
}
