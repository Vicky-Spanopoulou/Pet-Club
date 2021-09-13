package cb13.project.service.impl;

import cb13.project.entities.BusinessCategory;
import cb13.project.repository.BusinessCategoryRepository;
import cb13.project.service.BusinessCategoryService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicky
 */

@Service
@Transactional
public class BusinessCategoryServiceImpl implements BusinessCategoryService {
    
    @Autowired
    BusinessCategoryRepository businessCategoryRepository;

    @Override
    public BusinessCategory findById(Long id) {
        return businessCategoryRepository.getById(id);
    }

    @Override
    public BusinessCategory findByName(String name) {
        return businessCategoryRepository.findByName(name);
    }

    @Override
    public BusinessCategory save(BusinessCategory businessCategory) {
        return businessCategoryRepository.save(businessCategory);
    }

    @Override
    public void deleteById(Long id) {
        businessCategoryRepository.deleteById(id);
    }

    @Override
    public List<BusinessCategory> findAll() {
        return businessCategoryRepository.findAll();
    }
    
}
