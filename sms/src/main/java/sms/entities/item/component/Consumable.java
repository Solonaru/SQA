package sms.entities.item.component;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.Component;

@Entity
@NamedQuery(name = "Consumable.findAll", query = "SELECT h FROM Consumable h")
@DiscriminatorValue("Consumable")
public class Consumable extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Consumable() {
		super();
	}

	public Consumable(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}
	
	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
