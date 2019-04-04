package sms.entities.item.pack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.item.Item;
import sms.entities.item.pack.line.PackageLine;
import sms.entities.logic.ILine;
import sms.entities.logic.ILineIterator;
import sms.enums.item.ItemType;
import sms.enums.item.MeasurementUnit;

@Entity
@NamedQuery(name = "Package.findAll", query = "SELECT p FROM Package p")
@DiscriminatorValue("Package")
public class Package extends Item implements ILineIterator {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "pack")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = { "pack", "category" })
	private List<PackageLine> packageLines;

	// ------ Constructors -------
	public Package() {
		super();
		this.itemType = ItemType.PACKAGE;
	}

	public Package(String name, MeasurementUnit measurementUnit, Double stockQuantity, String description) {
		super(name, measurementUnit, stockQuantity, description);
		this.itemType = ItemType.PACKAGE;
	}

	// ------ Getters and Setters ------
	public List<PackageLine> getPackageLines() {
		return packageLines;
	}

	public void setPackageLines(List<PackageLine> packageLines) {
		this.packageLines = packageLines;
	}

	// ----- Methods -----
	public void addLine(PackageLine packageLine) {
		if (null == packageLines) {
			packageLines = new ArrayList<PackageLine>();
		}

		packageLines.add(packageLine);
		packageLine.setPack(this);
	}

	public Double getStockPrice() {
		Double price = 0.0;
		Iterator<? extends ILine> linesIterator = this.createLinesIterator();

		while (linesIterator.hasNext()) {
			PackageLine packageLine = (PackageLine) linesIterator.next();
			price += packageLine.getProduct().getStockPrice() * packageLine.getQuantity();
		}

		return (double) Math.round(price * 0.9);
	}

	public Iterator<? extends ILine> createLinesIterator() {
		return packageLines.iterator();
	}

}
