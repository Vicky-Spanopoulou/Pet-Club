package cb13.project.service;

import cb13.project.entities.Ads;

import java.util.List;

public interface AdsService {

    Ads saveAds(Ads ads);

    
    Ads findById(Long id);

    Ads updateAds(Ads ads);

    void deleteAdsById(Long adsId);

    List<Ads> findAllAds();
}
