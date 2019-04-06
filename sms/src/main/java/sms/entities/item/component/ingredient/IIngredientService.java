package sms.entities.item.component.ingredient;

import java.util.List;
import java.util.Optional;

public interface IIngredientService {

	public Optional<Ingredient> findIngredientById(int ingredientId);
	
	public Optional<Ingredient> findIngredientByName(String name);

	public List<Ingredient> findAllIngredients();

	public void insertIngredient(Ingredient ingredient);

	public void updateIngredient(Ingredient ingredient);

	public void deleteIngredientById(int ingredientId);
}