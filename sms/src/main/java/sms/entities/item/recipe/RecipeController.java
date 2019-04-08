package sms.entities.item.recipe;

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

import sms.entities.item.recipe.line.IRecipeLineService;
import sms.entities.item.recipe.line.RecipeLine;
import sms.utils.DisplayData;

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

	@Autowired
	private IRecipeService recipeService;
	@Autowired
	private IRecipeLineService recipeLineService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{recipeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Recipe> findRecipeById(@PathVariable("recipeId") int recipeId) {
		dataDisplay.printCrudInfo(recipeId);
		return recipeService.findRecipeById(recipeId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Recipe> getRecipes() {
		dataDisplay.printCrudInfo();
		return recipeService.findAllRecipes();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertRecipe(@RequestBody Recipe recipe) {
		dataDisplay.printCrudInfo();
		System.out.println("Inserting recipe");
		recipeService.insertRecipe(recipe);

		for (RecipeLine recipeLine : recipe.getRecipeLines()) {
			System.out.println(recipeLine.getComponent().getName());
			recipeLine.setRecipe(recipe);
			recipeLineService.insertRecipeLine(recipeLine);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateRecipe(@RequestBody Recipe recipe) {
		dataDisplay.printCrudInfo();
		recipeService.updateRecipe(recipe);
	}

	@RequestMapping(value = "/delete/{recipeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRecipe(@PathVariable("recipeId") int recipeId) {
		dataDisplay.printCrudInfo(recipeId);
		recipeService.deleteRecipeById(recipeId);
	}

}
