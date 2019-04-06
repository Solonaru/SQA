package sms.entities.item.component.ingredient;

import java.util.List;
import java.util.Optional;

import sms.entities.category.Category;

public interface IIngredientService {

	public Optional<Ingredient> findIngredientById(int ingredientId);
	
	public Optional<Ingredient> findIngredientByName(String name);

	public List<Ingredient> findAllIngredients();
	
	public List<Ingredient> findAllIngredientsByCategory(Category category);

	public void insertIngredient(Ingredient ingredient);

	public void updateIngredient(Ingredient ingredient);

	public void deleteIngredientById(int ingredientId);
}