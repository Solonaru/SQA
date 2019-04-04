package sms.entities.item.component;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.product.Product;
import sms.enums.item.MeasurementUnit;

@Entity
@NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")
public abstract class Component extends Product {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Component() {
		super();
	}

	public Component(String name, MeasurementUnit measurementUnit, Double stockQuantity, Double stockPrice, String description) {
		super(name, measurementUnit, stockQuantity, description);
		this.stockPrice = stockPrice;
	}
}
