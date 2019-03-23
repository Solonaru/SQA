package sms.entities.item.component.consumable;

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
@RequestMapping("/consumable")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsumableController {
	
	@Autowired
	private IConsumableService consumableService;
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{consumableId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Consumable> findConsumableById(@PathVariable("consumableId") int consumableId) {
		dataDisplay.printCrudInfo(consumableId); 
		return consumableService.findConsumableById(consumableId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Consumable> getConsumables() {
		dataDisplay.printCrudInfo(); 
		return consumableService.findAllConsumables();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Consumable consumable) {
		dataDisplay.printCrudInfo(); 
		consumableService.insertConsumable(consumable);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateConsumable(@RequestBody Consumable consumable) {
		dataDisplay.printCrudInfo(); 
		consumableService.updateConsumable(consumable);
	}

	@RequestMapping(value = "/delete/{consumableId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteConsumable(@PathVariable("consumableId") int consumableId) {
		dataDisplay.printCrudInfo(consumableId); 
		consumableService.deleteConsumableById(consumableId);
	}
}
