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
public class AddressDTO {
    
    private Long id;
    private String street;
    private double longitude;
    private double latitude;
    private String postalCode;
    private String city;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String street, double longitude, double latitude, String postalCode, String city) {
        this.id = id;
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
        sb.append("AddressDTO{id=").append(id);
        sb.append(", street=").append(street);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
