/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

import java.util.Date;

/**
 *
 * @author tsepe
 */
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String img;
    private String city;
    private Long address;
    private String street;
    
//    for admin requests
    private boolean isNotLocked;
    private boolean verified;
    private String role;
    private Date sub_until;
    private boolean advForEver;
    private boolean active;


    public UserDTO() {
    }

    
    public UserDTO(Long id,String firstName, String lastName, String username, String email, String password, String phone, String image, String city,Long address, String street) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.img = image;
        this.city = city;
        this.street = street;
        this.address = address;
    }

    public UserDTO(String firstName, String lastName, String username, String email, String password, String phone, String image, String city, String street) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.img = image;
        this.city = city;
        this.street = street;
    }



    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isIsNotLocked() {
        return isNotLocked;
    }

    public void setIsNotLocked(boolean isNotLocked) {
        this.isNotLocked = isNotLocked;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
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

    public boolean isAdvForEver() {
        return advForEver;
    }

    public void setAdvForEver(boolean advForEver) {
        this.advForEver = advForEver;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserDTO{id=").append(id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", username=").append(username);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", image=").append(img);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", street=").append(street);
        sb.append(", isNotLocked=").append(isNotLocked);
        sb.append(", verifyCode=").append(verified);
        sb.append(", role=").append(role);
        sb.append(", sub_until=").append(sub_until);
        sb.append(", advForEver=").append(advForEver);
        sb.append('}');
        return sb.toString();
    }
    

    
}
