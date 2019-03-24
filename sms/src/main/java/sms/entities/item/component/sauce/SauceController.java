package sms.entities.item.component.sauce;

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
@RequestMapping("/sauce")
@CrossOrigin(origins = "http://localhost:4200")
public class SauceController {
	
	@Autowired
	private ISauceService sauceService;
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{sauceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Sauce> findSauceById(@PathVariable("sauceId") int sauceId) {
		dataDisplay.printCrudInfo(sauceId); 
		return sauceService.findSauceById(sauceId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Sauce> getSauces() {
		dataDisplay.printCrudInfo(); 
		return sauceService.findAllSauces();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Sauce sauce) {
		dataDisplay.printCrudInfo(); 
		sauceService.insertSauce(sauce);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSauce(@RequestBody Sauce sauce) {
		dataDisplay.printCrudInfo(); 
		sauceService.updateSauce(sauce);
	}

	@RequestMapping(value = "/delete/{sauceId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSauce(@PathVariable("sauceId") int sauceId) {
		dataDisplay.printCrudInfo(sauceId); 
		sauceService.deleteSauceById(sauceId);
	}
}
