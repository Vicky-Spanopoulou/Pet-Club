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
public class CItyDTO {
    
    private String name;
    private Long id;
    private double latitude;
    private double longtitude;
    

    public CItyDTO() {
    }

    public CItyDTO(String name, Long id, double latitude, double longtitude) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CItyDTO{name=").append(name);
        sb.append(", id=").append(id);
        sb.append(", latitude=").append(latitude);
        sb.append(", longtitude=").append(longtitude);
        sb.append('}');
        return sb.toString();
    }
    
}
