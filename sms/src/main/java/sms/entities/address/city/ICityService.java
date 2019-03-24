package sms.entities.address.city;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    Optional<City> findCityById(int cityId);

    List<City> findAllCities();

    void insertCity(City city);

    void updateCity(City city);

    void deleteCityById(int cityId);

    Optional<City> findCityByName(String name);
}
