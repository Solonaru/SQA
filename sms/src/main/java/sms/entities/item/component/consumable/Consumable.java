package sms.entities.item.component.consumable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.component.Component;
import sms.enums.item.ItemType;

@Entity
@NamedQuery(name = "Consumable.findAll", query = "SELECT h FROM Consumable h")
@DiscriminatorValue("Consumable")
public class Consumable extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Consumable() {
		super();
		this.itemType = ItemType.CONSUMABLE;
	}

	public Consumable(String name, Integer stockQuantity, Double stockPrice, String description) {
		super(name, stockQuantity, stockPrice, description);
		this.itemType = ItemType.CONSUMABLE;
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
