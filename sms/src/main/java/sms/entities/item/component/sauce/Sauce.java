package sms.entities.item.component.sauce;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.component.Component;

@Entity
@NamedQuery(name = "Sauce.findAll", query = "SELECT h FROM Sauce h")
@DiscriminatorValue("Sauce")
public class Sauce extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Sauce() {
		super();
	}

	public Sauce(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}
	
	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
