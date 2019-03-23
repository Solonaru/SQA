package sms.entities.z_lines_logic;

import sms.entities.catalogue.catalogue_item.CatalogueItem;

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
