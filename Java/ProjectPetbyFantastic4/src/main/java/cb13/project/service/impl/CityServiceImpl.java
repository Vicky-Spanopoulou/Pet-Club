package cb13.project.service.impl;

import cb13.project.entities.City;
import cb13.project.repository.CityRepository;
import cb13.project.service.CityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCityById(Long cityId) {
        cityRepository.deleteById(cityId);
    }

    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }
    
    @Override
    public City findCityById(Long id) {
        return cityRepository.getById(id);
    }

    @Override
    public City findCityByName(String name) {
        return cityRepository.findByName(name);
    }
}
