/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.dto;

/**
 *
 * @author tsepe
 */
public class UpdatePassDTO {
    private String oldPass;
    private String username;
    private String newPass;

    public UpdatePassDTO() {
    }

    public UpdatePassDTO(String oldPass, String username, String newPass) {
        this.oldPass = oldPass;
        this.username = username;
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UpdatePass{oldPass=").append(oldPass);
        sb.append(", username=").append(username);
        sb.append(", newPass=").append(newPass);
        sb.append('}');
        return sb.toString();
    }
    
    
}
