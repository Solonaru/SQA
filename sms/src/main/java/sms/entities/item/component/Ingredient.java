package sms.entities.item.component;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.Component;

@Entity
@NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i")
@DiscriminatorValue("Ingredient")
public class Ingredient extends Component {
	private static final long serialVersionUID = 1L;

	// -----Constructors-----
	public Ingredient() {
		super();
	}

	public Ingredient(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}