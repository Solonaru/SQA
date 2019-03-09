package sms.entities.order;

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
@RequestMapping("/return")
@CrossOrigin(origins = "http://localhost:4200")
public class ReturnController {
	
	@Autowired
	private IReturnService returnService;
	
	@Autowired
	private DisplayData dataDisplay;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Return> findReturnById(@PathVariable("returnId") int returnId) {
		dataDisplay.printCrudInfo(returnId); 
		return returnService.findReturnById(returnId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Return> getReturns() {
		dataDisplay.printCrudInfo(); 
		return returnService.findAllReturns();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertReturn(@RequestBody Return returned) {
		dataDisplay.printCrudInfo(); 
		returnService.insertReturn(returned);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateReturn(@RequestBody Return returned) {
		dataDisplay.printCrudInfo(); 
		returnService.updateReturn(returned);
	}
	
	@RequestMapping(value = "/delete/{returnId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteReturn(@PathVariable("returnId") int returnId) {
		dataDisplay.printCrudInfo(returnId);
		returnService.deleteReturnById(returnId);
	}
}
