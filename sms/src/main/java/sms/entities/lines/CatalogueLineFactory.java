package sms.entities.lines;

import sms.entities.catalogue.CatalogueItem;

public class CatalogueLineFactory implements ILineAbstractFactory {

	private Double price;

	// ----- Constructors -----
	public CatalogueLineFactory(Double price) {
		this.price = price;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new CatalogueItem(price);
	}

}
