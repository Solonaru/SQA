package sms.entities.item.component.beverage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.component.Component;

@Entity
@NamedQuery(name = "Beverage.findAll", query = "SELECT h FROM Beverage h")
@DiscriminatorValue("Beverage")
public class Beverage extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Beverage() {
		super();
	}

	public Beverage(String name, Integer stockQuantity, String description) {
		super(name, stockQuantity, description);
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
