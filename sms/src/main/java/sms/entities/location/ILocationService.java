package sms.entities.location;

import java.util.List;
import java.util.Optional;

public interface ILocationService {
	
	public Optional<Location> findLocationById(int locationId);

	public List<Location> findAllLocations();
	
	public List<Location> findAllActiveLocations();

	public void insertLocation(Location location);

	public void updateLocation(Location location);

	public void deleteLocationById(int locationId);
}