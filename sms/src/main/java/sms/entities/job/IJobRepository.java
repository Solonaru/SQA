package sms.entities.job;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.entities.location.Location;
import sms.enums.Status;

public interface IJobRepository extends CrudRepository<Job, Integer> {

	public List<Job> findAllByStatus(Status status);

	public List<Job> findAllByStatusAndLocation(Status status, Location location);

}
