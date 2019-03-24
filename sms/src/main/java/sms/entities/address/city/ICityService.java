package sms.entities.address.city;

import java.util.List;
import java.util.Optional;

public interface ICityService {

public Optional<City> findCityById(int cityId);
	
	public List<City> findAllCities();

	public void insertCity(City city);

	public void updateCity(City city);

	public void deleteCityById(int cityId);
}
