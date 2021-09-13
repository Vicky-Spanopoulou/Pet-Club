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
public class RatingDTO {
    
    private Long id;
    private int ratingNumber;
    private Long userId;
    private Long businessId;

    public RatingDTO() {
    }

    public RatingDTO(Long id, int rateNumber, Long userId, Long businessId) {
        this.id = id;
        this.ratingNumber = rateNumber;
        this.userId = userId;
        this.businessId = businessId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RatingDTO{id=").append(id);
        sb.append(", ratingNumber=").append(ratingNumber);
        sb.append(", userId=").append(userId);
        sb.append(", businessId=").append(businessId);
        sb.append('}');
        return sb.toString();
    }
    
}
