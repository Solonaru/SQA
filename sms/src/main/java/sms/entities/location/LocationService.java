package sms.entities.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.enums.Status;

@Service
public class LocationService implements ILocationService {

	@Autowired
	private ILocationRepository locationRepository;

	public Optional<Location> findLocationById(int locationId) {
		return locationRepository.findById(locationId);
	}

	public List<Location> findAllLocations() {
		return (List<Location>) locationRepository.findAll();
	}

	public List<Location> findAllActiveLocations() {
		return (List<Location>) locationRepository.findAllByStatus(Status.ACTIVE);
	}

	public void insertLocation(Location location) {
		locationRepository.save(location);
	}

	public void updateLocation(Location location) {
		locationRepository.save(location);
	}

	public void deleteLocationById(int locationId) {
		locationRepository.deleteById(locationId);
	}
}
