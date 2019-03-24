package sms.entities.item.component.beverage;

import java.sql.Date;

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

	public Beverage(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}
	
	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
