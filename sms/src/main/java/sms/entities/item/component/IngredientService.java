package sms.entities.item.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService implements IIngredientService {

	@Autowired
	private IIngredientRepository ingredientRepository;

	public Optional<Ingredient> findIngredientById(int ingredientId) {
		return ingredientRepository.findById(ingredientId);
	}

	public List<Ingredient> findAllIngredients() {
		return (List<Ingredient>) ingredientRepository.findAll();
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