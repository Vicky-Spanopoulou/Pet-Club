/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

import java.util.Date;

/**
 *
 * @author vicky
 */
public class AdsDTO {
    
    private Long id;
    private String petName;
    private String chipNumber;
    private String sex;
    private String someWords;
    private String descriptions;
    private Date postDate;
    private String image;
    private Long user;
    private String petCategory;
    private String adCategory;
    private Date lostDate;
    
    // Address
    private Long addressId;
    private String street;
    private double longitude;
    private double latitude;
    private String postalCode;
    private String city;

    public AdsDTO() {
    }

    public AdsDTO(Long id, String petName, String chipNumber, String sex, String someWords, String descriptions, Date postDate, String image, Long user, String petCategory, String adCategory, Date lostDate,Long addressId, String street, double longitude, double latitude, String postalCode, String city) {
        this.id = id;
        this.petName = petName;
        this.chipNumber = chipNumber;
        this.sex = sex;
        this.someWords = someWords;
        this.descriptions = descriptions;
        this.postDate = postDate;
        this.image = image;
        this.user = user;
        this.petCategory = petCategory;
        this.adCategory = adCategory;
        this.lostDate = lostDate;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "AdsDTO{" + "id=" + id + ", petName=" + petName + ", chipNumber=" + chipNumber + ", sex=" + sex + ", someWords=" + someWords + ", descriptions=" + descriptions + ", postDate=" + postDate + ", image=" + image + ", user=" + user + ", petCategory=" + petCategory + ", adCategory=" + adCategory + ", lostDate=" + lostDate + ", addressId=" + addressId + ", street=" + street + ", longitude=" + longitude + ", latitude=" + latitude + ", postalCode=" + postalCode + ", city=" + city + '}';
    }

    

    
    
    
}
