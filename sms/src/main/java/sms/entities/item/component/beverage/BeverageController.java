package sms.entities.item.component.beverage;

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
@RequestMapping("/beverage")
@CrossOrigin(origins = "http://localhost:4200")
public class BeverageController {
	
	@Autowired
	private IBeverageService beverageService;
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{beverageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Beverage> findBeverageById(@PathVariable("beverageId") int beverageId) {
		dataDisplay.printCrudInfo(beverageId); 
		return beverageService.findBeverageById(beverageId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Beverage> getBeverages() {
		dataDisplay.printCrudInfo(); 
		return beverageService.findAllBeverages();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Beverage beverage) {
		dataDisplay.printCrudInfo(); 
		beverageService.insertBeverage(beverage);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateBeverage(@RequestBody Beverage beverage) {
		dataDisplay.printCrudInfo(); 
		beverageService.updateBeverage(beverage);
	}

	@RequestMapping(value = "/delete/{beverageId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBeverage(@PathVariable("beverageId") int beverageId) {
		dataDisplay.printCrudInfo(beverageId); 
		beverageService.deleteBeverageById(beverageId);
	}
}
