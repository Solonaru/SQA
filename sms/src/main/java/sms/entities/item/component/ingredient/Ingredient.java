package sms.entities.item.component.ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.component.Component;

@Entity
@NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i")
@DiscriminatorValue("Ingredient")
public class Ingredient extends Component {
	private static final long serialVersionUID = 1L;

	// -----Constructors-----
	public Ingredient() {
		super();
	}

	public Ingredient(String name, Integer stockQuantity, String description) {
		super(name, stockQuantity, description);
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}