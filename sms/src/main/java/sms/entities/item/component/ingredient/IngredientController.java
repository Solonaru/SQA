package sms.entities.item.component.ingredient;

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
@RequestMapping("/ingredient")
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {

	@Autowired
	private IIngredientService ingredientService;
	@Autowired
	private DisplayData dataDisplay;


	@RequestMapping(value = "/{ingredientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Ingredient> findIngredientById(@PathVariable("ingredientId") int ingredientId) {
		dataDisplay.printCrudInfo(ingredientId); 
		return ingredientService.findIngredientById(ingredientId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ingredient> getIngredients() {
		dataDisplay.printCrudInfo(); 
		return ingredientService.findAllIngredients();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Ingredient ingredient) {
		dataDisplay.printCrudInfo(); 
		ingredientService.insertIngredient(ingredient);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateIngredient(@RequestBody Ingredient ingredient) {
		dataDisplay.printCrudInfo(); 
		ingredientService.updateIngredient(ingredient);
	}

	@RequestMapping(value = "/delete/{ingredientId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteIngredient(@PathVariable("ingredientId") int ingredientId) {
		dataDisplay.printCrudInfo(ingredientId); 
		ingredientService.deleteIngredientById(ingredientId);
	}
}