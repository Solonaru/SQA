package sms.entities.item.component.ingredient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.entities.category.Category;
import sms.enums.Status;

@Service
public class IngredientService implements IIngredientService {

	@Autowired
	private IIngredientRepository ingredientRepository;

	public Optional<Ingredient> findIngredientById(int ingredientId) {
		return ingredientRepository.findById(ingredientId);
	}
	
	public Optional<Ingredient> findIngredientByName(String name) {
		return ingredientRepository.findByName(name);
	}

	public List<Ingredient> findAllIngredients() {
		return (List<Ingredient>) ingredientRepository.findAll();
	}
	
	public List<Ingredient> findAllIngredientsByCategory(Category category) {
		return (List<Ingredient>) ingredientRepository.findAllByStatusAndCategory(Status.ACTIVE, category);
	}

	public void insertIngredient(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}

	public void updateIngredient(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}

	public void deleteIngredientById(int ingredientId) {
		ingredientRepository.deleteById(ingredientId);
	}
}