package cb13.project.service;

import cb13.project.entities.City;

import java.util.List;

public interface CityService {

    City saveCity(City city);

    void deleteCityById(Long cityId);


    City updateCity(City city);

    List<City> findAllCities();
    
    City findCityById(Long id);
    
    City findCityByName(String name);
}
