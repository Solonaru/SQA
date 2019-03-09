package sms.entities.item;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h")
@DiscriminatorValue("Hardware")
public class Hardware extends Component {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Hardware() {
		super();
	}

	public Hardware(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}
}
