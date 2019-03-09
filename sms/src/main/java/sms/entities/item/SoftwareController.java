package sms.entities.item;

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

import sms.utils.DisplayData;

@RestController
@RequestMapping("/software")
@CrossOrigin(origins = "http://localhost:4200")
public class SoftwareController {

	@Autowired
	private ISoftwareService softwareService;
	@Autowired
	private DisplayData dataDisplay;


	@RequestMapping(value = "/{softwareId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Software> findSoftwareById(@PathVariable("softwareId") int softwareId) {
		dataDisplay.printCrudInfo(softwareId); 
		return softwareService.findSoftwareById(softwareId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Software> getSoftwares() {
		dataDisplay.printCrudInfo(); 
		return softwareService.findAllSoftwares();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Software software) {
		dataDisplay.printCrudInfo(); 
		softwareService.insertSoftware(software);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSoftware(@RequestBody Software software) {
		dataDisplay.printCrudInfo(); 
		softwareService.updateSoftware(software);
	}

	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSoftware(@PathVariable("softwareId") int softwareId) {
		dataDisplay.printCrudInfo(softwareId); 
		softwareService.deleteSoftwareById(softwareId);
	}
}