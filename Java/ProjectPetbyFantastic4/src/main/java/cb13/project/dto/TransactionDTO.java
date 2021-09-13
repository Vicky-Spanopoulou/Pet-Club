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
public class TransactionDTO {
    
    private Long id;
    private Date date;
    private String type;
    private String paypalId;
    private boolean paid;
    private String sub_name;
    private String sub_price;
    private int sub_duration;
    private String sub_role;
    private Long userId;
    private boolean advForEver;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, Date date, String type, String paypalId, 
            boolean paid, String username, String sub_name, String sub_price, 
            int sub_duration, String sub_role, Long userId, boolean advForEver) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.paypalId = paypalId;
        this.paid = paid;
        this.sub_name = sub_name;
        this.sub_price = sub_price;
        this.sub_duration = sub_duration;
        this.sub_role = sub_role;
        this.userId = userId;
        this.advForEver = advForEver;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public boolean isAdvForEver() {
        return advForEver;
    }

    public void setAdvForEver(boolean advForEver) {
        this.advForEver = advForEver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransactionDTO{id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", type=").append(type);
        sb.append(", paypalId=").append(paypalId);
        sb.append(", paid=").append(paid);
        sb.append(", sub_name=").append(sub_name);
        sb.append(", sub_price=").append(sub_price);
        sb.append(", sub_duration=").append(sub_duration);
        sb.append(", sub_role=").append(sub_role);
        sb.append(", userId=").append(userId);
        sb.append(", advForEver=").append(advForEver);
        sb.append('}');
        return sb.toString();
    }
    
    

     
    
}
