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
import sms.enums.item.ItemType;
import sms.enums.item.MeasurementUnit;

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

	public SimplePackage(String name, MeasurementUnit measurementUnit, Double stockQuantity, String description) {
		super(name, measurementUnit, stockQuantity, description);
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

	public Double getStockPrice() {
		Double price = 0.0;

		for (Item component : packageComponents) {
			price += component.getStockPrice();
		}

		return (double) Math.round(price * 0.95);
	}
	
}
