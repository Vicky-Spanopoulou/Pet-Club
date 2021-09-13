/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.repository;

import cb13.project.entities.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vicky
 */
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory ,Long> {
    
    public BusinessCategory findByName(String name);
    
}
