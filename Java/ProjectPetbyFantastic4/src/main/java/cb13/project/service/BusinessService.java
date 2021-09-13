package cb13.project.service;

import cb13.project.dto.BusinessAvgRatingDTO;
import cb13.project.entities.Business;

import java.util.List;

public interface BusinessService {

    Business saveBusiness(Business business);

    Business updateBusiness(Business business);

    void deleteBusinessById(Long businessId);
    
    List<Business> findAllBusiness();
    
    List<Business> findAllBusinessesByStatus(String status);
    
    Business findById(Long id);

    int findTotalNumberOfUnchecked();

    List<Business> findAllBusinessAvgRating();
}
