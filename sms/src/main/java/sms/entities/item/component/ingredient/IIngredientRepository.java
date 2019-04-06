package sms.entities.item.component.ingredient;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IIngredientRepository extends CrudRepository<Ingredient, Integer> {

	public Optional<Ingredient> findByName(String string);
	
}