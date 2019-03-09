package sms.entities.recipe;

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
@RequestMapping("/recipeLine")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeLineController {

	@Autowired
	private IRecipeLineService recipeLineService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{recipeLineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<RecipeLine> findRecipeLineById(@PathVariable("recipeLineId") int recipeLineId) {
		dataDisplay.printCrudInfo(recipeLineId); 
		return recipeLineService.findRecipeLineById(recipeLineId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RecipeLine> getRecipeLines() {
		dataDisplay.printCrudInfo(); 
		return recipeLineService.findAllRecipeLines();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertRecipeLine(@RequestBody RecipeLine recipeLine) {
		dataDisplay.printCrudInfo(); 
		recipeLineService.insertRecipeLine(recipeLine);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateRecipeLine(@RequestBody RecipeLine recipeLine) {
		dataDisplay.printCrudInfo(); 
		recipeLineService.updateRecipeLine(recipeLine);
	}

	@RequestMapping(value = "/delete/{recipeLineId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRecipeLine(@PathVariable("recipeLineId") int recipeLineId) {
		dataDisplay.printCrudInfo(recipeLineId); 
		recipeLineService.deleteRecipeLineById(recipeLineId);
	}

}
