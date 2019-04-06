package sms.entities.item.component.ingredient;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import sms.entities.category.Category;

public interface IIngredientRepository extends CrudRepository<Ingredient, Integer> {

	public Optional<Ingredient> findByName(String string);
	
	public List<Ingredient> findAllByCategory(Category category);
	
}