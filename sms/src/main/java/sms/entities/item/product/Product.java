package sms.entities.item.product;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import sms.entities.item.Item;

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public abstract class Product extends Item {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Product() {
		super();
	}

	public Product(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}

}
