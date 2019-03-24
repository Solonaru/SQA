package sms.entities.address.city;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICityRepository extends CrudRepository<City, Integer> {
    Optional<City> findCityByName(String name);
}
