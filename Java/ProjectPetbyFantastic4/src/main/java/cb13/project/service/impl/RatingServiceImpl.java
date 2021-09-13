package cb13.project.service.impl;

import cb13.project.entities.Business;
import cb13.project.entities.Rating;
import cb13.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import cb13.project.service.RatingService;
import cb13.project.repository.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.getById(id);
    }

    @Override
    public void deleteRatingById(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public float findBusinessAvgRating(Long id) {
        return ratingRepository.avgBusinessRating(id);
    }

    @Override
    public Rating findByUserAndBusiness(User user, Business business) {
        return ratingRepository.findByUserAndBusiness(user, business);
    }
}
