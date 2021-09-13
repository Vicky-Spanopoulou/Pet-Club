package cb13.project.service;

import cb13.project.entities.Rating;
import cb13.project.entities.User;
import cb13.project.entities.Business;

public interface RatingService {

    Rating saveRating(Rating rate);
    
    Rating findById(Long id);
    
    Rating findByUserAndBusiness(User user, Business business);
    
    void deleteRatingById(Long id);

    float findBusinessAvgRating(Long id);

}
