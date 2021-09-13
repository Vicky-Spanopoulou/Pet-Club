/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.service;

import cb13.project.entities.BusinessCategory;
import java.util.List;

/**
 *
 * @author vicky
 */

public interface BusinessCategoryService {
    
   BusinessCategory findById(Long id);
   
   BusinessCategory findByName(String name);
   
   List<BusinessCategory> findAll();
   
   BusinessCategory save(BusinessCategory businessCategory);
   
   void deleteById(Long id);
    
}
