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
public class PetCategoryDTO {
    
    private Long id;
    private String name;

    public PetCategoryDTO() {
    }

    public PetCategoryDTO(Long id, String Name) {
        this.id = id;
        this.name = Name;
    }

    public PetCategoryDTO(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PetCategoryDTO{id=").append(id);
        sb.append(", Name=").append(name);
        sb.append('}');
        return sb.toString();
    }
    
    
}
