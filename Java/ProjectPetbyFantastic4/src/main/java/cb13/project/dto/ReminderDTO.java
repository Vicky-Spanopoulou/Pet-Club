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
public class ReminderDTO {
    
    private Long id;
    private String info;
    private boolean done;
    private Long userId;

    public ReminderDTO() {
    }

    public ReminderDTO(Long id, String info, boolean done, Long userId) {
        this.id = id;
        this.info = info;
        this.done = done;
        this.userId = userId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReminderDTO{id=").append(id);
        sb.append(", info=").append(info);
        sb.append(", done=").append(done);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
