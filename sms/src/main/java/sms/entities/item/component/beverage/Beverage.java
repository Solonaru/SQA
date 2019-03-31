package sms.entities.item.component.beverage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.component.Component;
import sms.enums.item.ItemType;

@Entity
@NamedQuery(name = "Beverage.findAll", query = "SELECT h FROM Beverage h")
@DiscriminatorValue("Beverage")
public class Beverage extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Beverage() {
		super();
		this.itemType = ItemType.BEVERAGE;
	}

	public Beverage(String name, Integer stockQuantity, Double stockPrice, String description) {
		super(name, stockQuantity, stockPrice, description);
		this.itemType = ItemType.BEVERAGE;
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
