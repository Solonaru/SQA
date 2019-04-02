package sms.entities.location;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.enums.Status;

public interface ILocationRepository extends CrudRepository<Location, Integer> {

	public List<Location> findAllByStatus(Status status);

}
