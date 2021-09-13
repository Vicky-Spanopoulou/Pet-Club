package cb13.project.service.impl;

import cb13.project.dto.BusinessAvgRatingDTO;
import cb13.project.entities.Business;
import cb13.project.entities.User;
import cb13.project.repository.BusinessRepository;
import cb13.project.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    

    @Override
    public Business saveBusiness(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Business updateBusiness(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public void deleteBusinessById(Long businessId) {
        businessRepository.deleteById(businessId);
    }

    @Override
    public List<Business> findAllBusiness() {
        return businessRepository.findAll();
    }

    @Override
    public Business findById(Long id) {
        return businessRepository.getByIdWithAvg(id);
    }

    @Override
    public int findTotalNumberOfUnchecked() {
        return businessRepository.findNumberUncheckedBusiness();
    }

    @Override
    public List<Business> findAllBusinessAvgRating() {
        return businessRepository.findAllBusinessAvgRating();
    }

    @Override
    public List<Business> findAllBusinessesByStatus(String status) {
        return businessRepository.findBusinessByStatus(status);
    }

    
}
