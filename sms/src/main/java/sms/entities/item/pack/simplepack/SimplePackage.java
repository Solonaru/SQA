package sms.entities.item.pack.simplepack;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import sms.entities.item.Item;
import sms.enums.Month;
import sms.enums.item.ItemType;

@Entity
@NamedQuery(name = "SimplePackage.findAll", query = "SELECT sp FROM SimplePackage sp")
@DiscriminatorValue("Simple package")
public class SimplePackage extends Item {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "simple_package_items", joinColumns = {
			@JoinColumn(name = "simple_package_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private List<Item> packageComponents = new ArrayList<>();

	// ------ Constructors -------
	public SimplePackage() {
		super();
		this.itemType = ItemType.SIMPLE_PACKAGE;
	}

	public SimplePackage(String name, Integer stockQuantity, Double stockPrice, String description) {
		super(name, stockQuantity, stockPrice, description);
		this.itemType = ItemType.SIMPLE_PACKAGE;
	}

	// ----- Getters and Setters -----
	public List<Item> getPackageComponents() {
		return packageComponents;
	}

	public void setPackageComponents(List<Item> packageComponents) {
		this.packageComponents = packageComponents;
	}

	// ----- Methods -----
	public void addComponent(Item component) {
		packageComponents.add(component);
	}

	public Double getPrice() {
		Double price = 0.0;

		for (Item component : packageComponents) {
			price += component.getPrice();
		}

		return (double) Math.round(price * 0.9);
	}

	public Double getPrice(Month month) {
		Double price = 0.0;

		for (Item component : packageComponents) {
			price += component.getPrice(month);
		}

		return (double) Math.round(price * 0.9);
	}
}
