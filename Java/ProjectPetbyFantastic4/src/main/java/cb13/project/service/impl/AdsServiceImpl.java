package cb13.project.service.impl;

import cb13.project.entities.Ads;
import cb13.project.repository.AdsRepository;
import cb13.project.service.AdsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class AdsServiceImpl implements AdsService {

    @Autowired
    private AdsRepository adsRepository;

    @Override
    public Ads saveAds(Ads ads) {
        return adsRepository.save(ads);
    }

    @Override
    public Ads updateAds(Ads ads) {
        return adsRepository.save(ads);
    }

    @Override
    public void deleteAdsById(Long adsId) {
        adsRepository.deleteById(adsId);
    }

    @Override
    public List<Ads> findAllAds() {
        return adsRepository.findAll();
    }

    @Override
    public Ads findById(Long id) {
        return adsRepository.getById(id);
    }
}
