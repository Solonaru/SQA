package sms.entities.location;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sms.enums.Status;
import sms.utils.DisplayData;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

	@Autowired
	private ILocationService locationService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{locationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Location> findLocationById(@PathVariable("locationId") int locationId) {
		dataDisplay.printCrudInfo(locationId);
		return locationService.findLocationById(locationId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Location> getLocations() {
		dataDisplay.printCrudInfo();
		List<Location> locations = locationService.findAllLocations();
		Collections.sort(locations);
		return locations;
	}

	@RequestMapping(value = "/all/active", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Location> getActiveLocations() {
		dataDisplay.printCrudInfo();
		List<Location> locations = locationService.findAllActiveLocations();
		Collections.sort(locations);
		return locations;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertLocation(@RequestBody Location location) {
		dataDisplay.printCrudInfo();
		location.setStatus(Status.ACTIVE);
		location.setUpdateDate(new Date(System.currentTimeMillis()));
		locationService.insertLocation(location);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateLocation(@RequestBody Location location) {
		dataDisplay.printCrudInfo();
		location.setUpdateDate(new Date(System.currentTimeMillis()));
		locationService.updateLocation(location);
	}

	/* TODO: Optimise, receive a Location object, not an ID */
	@RequestMapping(value = "/delete/{locationId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteLocation(@PathVariable("locationId") int locationId) {
		dataDisplay.printCrudInfo(locationId);
		Location location = locationService.findLocationById(locationId).get();
		location.removeLocation();
		location.setUpdateDate(new Date(System.currentTimeMillis()));
		locationService.updateLocation(location);
	}
}