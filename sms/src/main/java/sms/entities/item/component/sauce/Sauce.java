package sms.entities.item.component.sauce;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.component.Component;
import sms.enums.item.ItemType;
import sms.enums.item.MeasurementUnit;

@Entity
@NamedQuery(name = "Sauce.findAll", query = "SELECT h FROM Sauce h")
@DiscriminatorValue("Sauce")
public class Sauce extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Sauce() {
		super();
		this.itemType = ItemType.SAUCE;
	}

	public Sauce(String name, MeasurementUnit measurementUnit, Double stockQuantity, Double stockPrice, String description) {
		super(name, measurementUnit, stockQuantity, stockPrice, description);
		this.itemType = ItemType.SAUCE;
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
