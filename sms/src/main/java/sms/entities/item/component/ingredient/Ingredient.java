package sms.entities.item.component.ingredient;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.item.component.Component;
import sms.enums.item.ItemType;
import sms.enums.item.MeasurementUnit;

@Entity
@NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i")
@DiscriminatorValue("Ingredient")
public class Ingredient extends Component {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties(value = { "conflictIngredients", "category" })
	private Set<Ingredient> conflictIngredients;

	// -----Constructors-----
	public Ingredient() {
		super();
		this.itemType = ItemType.INGREDIENT;
	}

	public Ingredient(String name, MeasurementUnit measurementUnit, Double stockQuantity, Double stockPrice,
			String description) {
		super(name, measurementUnit, stockQuantity, stockPrice, description);
		this.itemType = ItemType.INGREDIENT;
	}

	public Set<Ingredient> getConflictIngredients() {
		return conflictIngredients;
	}

	public void setConflictIngredients(Set<Ingredient> conflictIngredients) {
		this.conflictIngredients = conflictIngredients;
	}

	public void addConflictIngredient(Ingredient ingredient) {
		this.addConflictIngredient(ingredient, true);
	}

	private void addConflictIngredient(Ingredient ingredient, Boolean recursive) {
		if (null == this.conflictIngredients) {
			this.conflictIngredients = new HashSet<Ingredient>();
		}

		this.conflictIngredients.add(ingredient);
		if(true == recursive) {
			ingredient.addConflictIngredient(this, false);
		}
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}