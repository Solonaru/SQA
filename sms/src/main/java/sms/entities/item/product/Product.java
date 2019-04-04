package sms.entities.item.product;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.Item;
import sms.enums.item.MeasurementUnit;

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public abstract class Product extends Item {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Product() {
		super();
	}

	public Product(String name, MeasurementUnit measurementUnit, Double stockQuantity, String description) {
		super(name, measurementUnit, stockQuantity, description);
	}

}
